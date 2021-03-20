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
        this.coupon_number = str1; // 쿠폰번호
        
        if(coupon_number == "")
        {
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("사용자 쿠폰번호=[" + coupon_number + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        // 쿠폰이 지급이 되었지만 사용이 안된건이 있는지 확인
        String sql1 = "select count(*) as cnt from rest_coupon where coupon_number = ? and use_yn = 'N' and use_yn_ck = 'Y'";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql1);
            
            //데이터 셋팅
            pstmt.setString(1, coupon_number); // 쿠폰번호

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            ResultSet rc = pstmt.executeQuery();
            
            if(rc.next()) {
            	cnt = rc.getInt("cnt");
            }
            
            if( cnt != 1 )
            {
            	System.out.println("이미 사용되었거나 없는 쿠폰번호 입니다.");
                System.out.println("사용자 쿠폰번호=[" + coupon_number + "]");
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
        
        // 쿠폰번호 지급처리 취소 처리
        String sql2 = "update rest_coupon set use_yn='N' residentno='', phonnumber='' where coupon_number=?";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql2);
            //데이터 셋팅
            pstmt.setString(1, coupon_number); // 쿠폰번호

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            int rc= pstmt.executeUpdate();
            System.out.println("변경된 row : " + rc);

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
        System.out.println("사용자 쿠폰번호=" + coupon_number );
    }
	
}
