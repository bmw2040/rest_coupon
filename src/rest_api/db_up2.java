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
        this.residentno    = str1; // 실명번호
        this.phonnumber    = str2; // 전화번호
        this.coupon_number = str3; // 쿠폰번호
        
        if(residentno == "" || phonnumber == "" || coupon_number == "")
        {
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("사용자 실명번호=[" + residentno    + "]");
            System.out.println("사용자 전화번호=[" + phonnumber    + "]");
            System.out.println("사용자 쿠폰번호=[" + coupon_number + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        // 사용자정보 및 쿠폰번호가 지급이 되어지고 사용이 안된것인지 확인
        // 정상이면 1건이 나와야 한다 0건 or 1건 이상은 오류
        String sql1 = "select count(*) as cnt from rest_coupon where coupon_number = ? and residentno = ? and phonnumber = ? and use_yn = 'Y' and use_yn_ck = 'N'";
        
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
            
            if( cnt == 0 )
            {
               	System.out.println("이미 사용된 쿠폰번호 입니다.");
                System.out.println("사용자 실명번호=[" + residentno    + "]");
                System.out.println("사용자 전화번호=[" + phonnumber    + "]");
                System.out.println("사용자 쿠폰번호=[" + coupon_number + "]");
            	System.exit(0);            	
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
        
        // 쿠펀번호에 사용자 정보 업데이트
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
