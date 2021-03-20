package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_se3 {
	String expir_3df_ymd = null;

	db_se3() {
		expir_3df_ymd = "";
    }
	
	db_se3(String str1) {
        this.expir_3df_ymd = str1; // 
        
        int cnt = 0;
    
        if(str1 == "")
        {
           	System.out.println("�Է����� �� �����Դϴ�.");
            System.out.println("�������� 3����=[" + expir_3df_ymd + "]");
        	System.exit(0);
        }
        
        System.out.println("�������� 3����=[" + expir_3df_ymd + "]");
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        

        // �߱޵� ������ ���� 3���� ����ڿ��� �޼���(�������� 3�� �� ����˴ϴ�.��)�� �߼��ϴ� ����� �����ϼ���.
        String sql = "select residentno , phonnumber , coupon_number from rest_coupon where expir_ymd = ? and use_yn = 'Y' and use_yn_ck = 'N'";        	

        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //������ ����
            pstmt.setString(1, expir_3df_ymd); // ��������
            
            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL ���� ���� ��, ����� row �� int type ����
            ResultSet rc = pstmt.executeQuery();

 
            while (rc.next())
            {
            	String residentno    = rc.getString("residentno"    );
            	String phonnumber    = rc.getString("phonnumber"    );
            	String coupon_number = rc.getString("coupon_number" );
            	
            	System.out.println("�Ǹ��ȣ  =" + residentno   );
            	System.out.println("��ȭ��ȣ  =" + phonnumber   );
            	System.out.println("������ȣ  =" + coupon_number);
            	System.out.println("3���� �ش� ������ȣ�� ����˴ϴ�.");
            	
            	cnt++;
            }
            
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
        System.out.println("��������3����=" + expir_3df_ymd );
    }
	
}
