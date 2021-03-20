package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_up1 {
	String residentno = null, phonnumber = null, coupon_number=null;

	db_up1() {
		residentno = "";
		phonnumber = "";
    }
	
	db_up1(String str1, String str2) {
        this.residentno = str1; // 실명번호
        this.phonnumber = str2; // 전화번호
        
        if(str1 == "" || str2 == "")
        {
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("사용자 실명번호=[" + str1 + "]");
            System.out.println("사용자 전화번호=[" + str2 + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        // 당일or가장 최근일자에 사용자에게 할당되지 않은 쿠폰번호중 max 값을 조회
        String sql1 = "select max(coupon_number) as coupon_number from rest_coupon where base_ymd = (select max(base_ymd) from rest_coupon) and USE_YN = 'N'";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql1);

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            ResultSet rc = pstmt.executeQuery();
            
            if(rc.next()) {
            	coupon_number = rc.getString("coupon_number");
            }
            //System.out.println("변경된 row : " + r);

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
        
        // 조회한 쿠펀번호에 사용자 정보 업데이트
        String sql2 = "update rest_coupon set use_yn=?, residentno=?, phonnumber=? where coupon_number=?";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql2);
            //데이터 셋팅
            pstmt.setString(1, "Y"); // 사용여부
            pstmt.setString(2, residentno); // 실명번호 
            pstmt.setString(3, phonnumber); // 전화번호
            pstmt.setString(4, coupon_number); // 쿠폰번호


            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            int rc= pstmt.executeUpdate();
            //System.out.println("변경된 row : " + rc);

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
        System.out.println("사용자 실명번호=" + residentno );
        System.out.println("사용자 전화번호=" + phonnumber );
        System.out.println("사용자 쿠폰번호=" + coupon_number );
    }
	
}
