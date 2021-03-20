package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_up3 {
	String coupon_number=null;
	int cnt = 0;

	db_up3() {
		coupon_number = "";
    }
	
	db_up3(String str1) {
        this.coupon_number = str1; // ������ȣ
        
        if(coupon_number == "")
        {
           	System.out.println("�Է����� �� �����Դϴ�.");
            System.out.println("����� ������ȣ=[" + coupon_number + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        // ������ ������ �Ǿ����� ����� �ȵȰ��� �ִ��� Ȯ��
        String sql1 = "select count(*) as cnt from rest_coupon where coupon_number = ? and use_yn = 'N' and use_yn_ck = 'Y'";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql1);
            
            //������ ����
            pstmt.setString(1, coupon_number); // ������ȣ

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL ���� ���� ��, ����� row �� int type ����
            ResultSet rc = pstmt.executeQuery();
            
            if(rc.next()) {
            	cnt = rc.getInt("cnt");
            }
            
            if( cnt != 1 )
            {
            	System.out.println("�̹� ���Ǿ��ų� ���� ������ȣ �Դϴ�.");
                System.out.println("����� ������ȣ=[" + coupon_number + "]");
            	System.exit(0);            	
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
        
        // ������ȣ ����ó�� ��� ó��
        String sql2 = "update rest_coupon set use_yn='N' residentno='', phonnumber='' where coupon_number=?";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql2);
            //������ ����
            pstmt.setString(1, coupon_number); // ������ȣ

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL ���� ���� ��, ����� row �� int type ����
            int rc= pstmt.executeUpdate();
            System.out.println("����� row : " + rc);

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
        System.out.println("����� ������ȣ=" + coupon_number );
    }
	
}
