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
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("만료일자 3일전=[" + expir_3df_ymd + "]");
        	System.exit(0);
        }
        
        System.out.println("만료일자 3일전=[" + expir_3df_ymd + "]");
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        

        // 발급된 쿠폰중 만료 3일전 사용자에게 메세지(“쿠폰이 3일 후 만료됩니다.”)를 발송하는 기능을 구현하세요.
        String sql = "select residentno , phonnumber , coupon_number from rest_coupon where expir_ymd = ? and use_yn = 'Y' and use_yn_ck = 'N'";        	

        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //데이터 셋팅
            pstmt.setString(1, expir_3df_ymd); // 만료일자
            
            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            ResultSet rc = pstmt.executeQuery();

 
            while (rc.next())
            {
            	String residentno    = rc.getString("residentno"    );
            	String phonnumber    = rc.getString("phonnumber"    );
            	String coupon_number = rc.getString("coupon_number" );
            	
            	System.out.println("실명번호  =" + residentno   );
            	System.out.println("전화번호  =" + phonnumber   );
            	System.out.println("쿠폰번호  =" + coupon_number);
            	System.out.println("3일후 해당 쿠폰번호가 만료됩니다.");
            	
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
        System.out.println("만료일자3일전=" + expir_3df_ymd );
    }
	
}
