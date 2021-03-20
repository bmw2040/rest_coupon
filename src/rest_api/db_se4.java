package rest_api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_se4 {
	String base_ymd = null;

	db_se4() {
		base_ymd = "";
    }
	
	db_se4(String str1) {
        this.base_ymd = str1; // 
        
        int cnt = 0;
    
        if(str1 == "")
        {
           	System.out.println("�Է����� �� �����Դϴ�.");
            System.out.println("�������� =[" + base_ymd + "]");
        	System.exit(0);
        }
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        

        // �������ڷ� ��ȸ
        String sql = "select * from rest_coupon where base_ymd = ?";        	
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //������ ����
            pstmt.setString(1, base_ymd); // ��������
            
            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL ���� ���� ��, ����� row �� int type ����
            ResultSet rc = pstmt.executeQuery();

        	String filePath = "E:\\�Ի� �̷¼� �°�\\īī������_��������\\rest_coupon.csv";
        	
        	File file = null;
        	BufferedWriter bw = null;
        	String NEWLINE = System.lineSeparator(); // �ٹٲ�(\n)

        	file = new File(filePath);
        	bw = new BufferedWriter(new FileWriter(file));
 
            while (rc.next())
            {
            	String base_ymd      = rc.getString("base_ymd"     );
            	String coupon_number = rc.getString("coupon_number");
            	String expir_ymd     = rc.getString("expir_ymd"    );
            	String use_yn        = rc.getString("use_yn"       );
            	String use_yn_ck     = rc.getString("use_yn_ck"    );
            	String residentno    = rc.getString("residentno"   );
            	String phonnumber    = rc.getString("phonnumber"   );
            	
            	System.out.println("��������  =" + base_ymd     );
            	System.out.println("������ȣ  =" + coupon_number);
            	System.out.println("��������  =" + expir_ymd    );
            	System.out.println("����ڿ���=" + use_yn       );
            	System.out.println("���üũ  =" + use_yn_ck    );
            	System.out.println("�Ǹ��ȣ  =" + residentno   );
            	System.out.println("��ȭ��ȣ  =" + phonnumber   );
            	


            	
                try
                {
                	bw.write(base_ymd +","+ coupon_number +","+ expir_ymd +","+ use_yn +","+ use_yn_ck +","+ residentno +","+ phonnumber);
                	bw.write(NEWLINE);

                    
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            	cnt++;
            }
            

            bw.close();

            if(cnt == 0)
            {
            	System.out.println("��ȸ���� �����ϴ�." );
            }
        } catch(Exception e) {
            System.out.println("Connect Failed!");
            e.printStackTrace();
        } finally {
        	if (pstmt != null){
        		try { 
        			pstmt.close(); 
        			} catch (SQLException e) {
        				 e.printStackTrace(); 
        				 }
        		}
        		
        		if (con != null) {
        			try { 
        				con.close(); 
        				} catch (SQLException e) {
        				 e.printStackTrace(); 
        				 }
        			}
        }
	}
	
	void show() {
        System.out.println("��������=" + base_ymd );
    }
	
}
