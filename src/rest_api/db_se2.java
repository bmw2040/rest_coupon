package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_se2 {
	String expir_ymd = null;

	db_se2() {
		expir_ymd = "";
    }
	
	db_se2(String str1) {
        this.expir_ymd = str1; // 
        
        int cnt = 0;
    
        if(str1 == "")
        {
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("만료일자=[" + expir_ymd + "]");
        	System.exit(0);
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";

        String sql = "select * from rest_coupon where expir_ymd = ?";        	
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            //System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //데이터 셋팅
            pstmt.setString(1, expir_ymd); // 만료일자
            
            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            ResultSet rc = pstmt.executeQuery();
 
            while (rc.next())
            {
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
            	
            	cnt++;
            }
            
            if(cnt == 0)
            {
            	System.out.println("조회값이 없습니다." );
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
        System.out.println("만료일자=" + expir_ymd );
    }
	
}
