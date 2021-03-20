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
        this.residentno = str1; // 실명번호
        this.phonnumber = str2; // 전화번호
    
        if(str1 == "" && str2 == "")
        {
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("사용자 실명번호=[" + residentno + "]");
            System.out.println("사용자 전화번호=[" + phonnumber + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        

        // 당일or가장 최근일자에 사용자에게 할당되지 않은 쿠폰번호중 max 값을 조회
        String sql = "select * from rest_coupon where residentno like ? and phonnumber like ?";        	

        

        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //데이터 셋팅
            pstmt.setString(1, "%" + residentno + "%"); // 실명번호
            pstmt.setString(2, "%" + phonnumber + "%"); // 전화번호 
            

            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            ResultSet rc = pstmt.executeQuery();
            
            while (rc.next()) {
            	String base_ymd      = rc.getString("base_ymd"     );
            	String coupon_number = rc.getString("coupon_number");
            	String expir_ymd     = rc.getString("expir_ymd"    );
            	String use_yn        = rc.getString("use_yn"       );
            	String use_yn_ck     = rc.getString("use_yn_ck"    );
            	String residentno    = rc.getString("residentno"   );
            	String phonnumber    = rc.getString("phonnumber"   );
            	
            	System.out.println("발행일자  =" + base_ymd     );
            	System.out.println("쿠폰번호  =" + coupon_number);
            	System.out.println("만기일자  =" + expir_ymd    );
            	System.out.println("사용자여부=" + use_yn       );
            	System.out.println("사용체크  =" + use_yn_ck    );
            	System.out.println("실명번호  =" + residentno   );
            	System.out.println("전화번호  =" + phonnumber   );
            
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
        System.out.println("사용자 실명번호=" + residentno );
        System.out.println("사용자 전화번호=" + phonnumber );
        System.out.println("사용자 쿠폰번호=" + coupon_number );
    }
	
}
