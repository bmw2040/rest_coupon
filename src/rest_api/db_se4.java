package rest_api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db_se4 {
	String base_ymd = null;

	db_se4() {
		base_ymd = "";
    }
	
	db_se4(String str1) {
        this.base_ymd = str1; // 
        
        int cnt = 0;
    
        if(str1 == "")
        {
           	System.out.println("입력정보 빈값 에러입니다.");
            System.out.println("발행일자 =[" + base_ymd + "]");
        	System.exit(0);
        }
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        

        // 발행일자로 조회
        String sql = "select * from rest_coupon where base_ymd = ?";        	
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connect Success!");
            
            pstmt = con.prepareStatement(sql);
            
            //데이터 셋팅
            pstmt.setString(1, base_ymd); // 발행일자
            
            // pstmt.excuteQuery() : select
            // pstmt.excuteUpdate() : insert, update, delete
            // SQL 문장 실행 후, 변경된 row 수 int type 리턴
            ResultSet rc = pstmt.executeQuery();

        	String filePath = "E:\\입사 이력서 는곳\\카카오페이_사전과제\\rest_coupon.csv";
        	
        	File file = null;
        	BufferedWriter bw = null;
        	String NEWLINE = System.lineSeparator(); // 줄바꿈(\n)

        	file = new File(filePath);
        	bw = new BufferedWriter(new FileWriter(file));
 
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
            	


            	
                try
                {
                	bw.write(base_ymd +","+ coupon_number +","+ expir_ymd +","+ use_yn +","+ use_yn_ck +","+ residentno +","+ phonnumber);
                	bw.write(NEWLINE);

                    
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            	cnt++;
            }
            

            bw.close();

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
        System.out.println("발행일자=" + base_ymd );
    }
	
}
