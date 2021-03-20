package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_se1 {
	String residentno = null, phonnumber = null, coupon_number=null;

	db_se1() {
		residentno = "";
		phonnumber = "";
    }
	
	db_se1(String str1, String str2) {
        this.residentno = str1; // �Ǹ��ȣ
        this.phonnumber = str2; // ��ȭ��ȣ
    
        if(str1 == "" && str2 == "")
        {
           	System.out.println("�Է����� �� �����Դϴ�.");
            System.out.println("����� �Ǹ��ȣ=[" + residentno + "]");
            System.out.println("����� ��ȭ��ȣ=[" + phonnumber + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        

        // ����or���� �ֱ����ڿ� ����ڿ��� �Ҵ���� ���� ������ȣ�� max ���� ��ȸ
        String sql = "select * from rest_coupon where residentno like ? and phonnumber like ?";        	

        

        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //������ ����
            pstmt.setString(1, "%" + residentno + "%"); // �Ǹ��ȣ
            pstmt.setString(2, "%" + phonnumber + "%"); // ��ȭ��ȣ 
            

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL ���� ���� ��, ����� row �� int type ����
            ResultSet rc = pstmt.executeQuery();
            
            while (rc.next()) {
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
        System.out.println("����� �Ǹ��ȣ=" + residentno );
        System.out.println("����� ��ȭ��ȣ=" + phonnumber );
        System.out.println("����� ������ȣ=" + coupon_number );
    }
	
}
