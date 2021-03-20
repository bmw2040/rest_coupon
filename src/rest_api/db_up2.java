package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_up2 {
	String residentno = null, phonnumber = null, coupon_number=null;
	int cnt = 0;

	db_up2() {
		residentno    = "";
		phonnumber    = "";
		coupon_number = "";
    }
	
	db_up2(String str1, String str2, String str3) {
        this.residentno    = str1; // �Ǹ��ȣ
        this.phonnumber    = str2; // ��ȭ��ȣ
        this.coupon_number = str3; // ������ȣ
        
        if(residentno == "" || phonnumber == "" || coupon_number == "")
        {
           	System.out.println("�Է����� �� �����Դϴ�.");
            System.out.println("����� �Ǹ��ȣ=[" + residentno    + "]");
            System.out.println("����� ��ȭ��ȣ=[" + phonnumber    + "]");
            System.out.println("����� ������ȣ=[" + coupon_number + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        // ��������� �� ������ȣ�� ������ �Ǿ����� ����� �ȵȰ����� Ȯ��
        // �����̸� 1���� ���;� �Ѵ� 0�� or 1�� �̻��� ����
        String sql1 = "select count(*) as cnt from rest_coupon where coupon_number = ? and residentno = ? and phonnumber = ? and use_yn = 'Y' and use_yn_ck = 'N'";
        
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
            
            if( cnt == 0 )
            {
               	System.out.println("�̹� ���� ������ȣ �Դϴ�.");
                System.out.println("����� �Ǹ��ȣ=[" + residentno    + "]");
                System.out.println("����� ��ȭ��ȣ=[" + phonnumber    + "]");
                System.out.println("����� ������ȣ=[" + coupon_number + "]");
            	System.exit(0);            	
            }
            
            //System.out.println("����� row : " + r);

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
        
        // ���ݹ�ȣ�� ����� ���� ������Ʈ
        String sql2 = "update rest_coupon set use_yn=?, residentno=?, phonnumber=? where coupon_number=?";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql2);
            //������ ����
            pstmt.setString(1, "Y"); // ��뿩��
            pstmt.setString(2, residentno); // �Ǹ��ȣ 
            pstmt.setString(3, phonnumber); // ��ȭ��ȣ
            pstmt.setString(4, coupon_number); // ������ȣ


            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL ���� ���� ��, ����� row �� int type ����
            int rc= pstmt.executeUpdate();
            //System.out.println("����� row : " + rc);

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
