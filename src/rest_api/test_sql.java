package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class test_sql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        System.out.print("코드테스트 값 입력바랍니다.(1~7): ");
        int num = new Scanner(System.in).nextInt();
        
        
        if(num == 1)
        {
        	System.out.println("당일 쿠폰발행처리 결과 테스트확인");
        	String sql = "select * from rest_coupon where base_ymd = ?";
        	
            try {
                Class.forName(className);
                con = DriverManager.getConnection(url, user, passwd);
                System.out.println("Connect Success!");
                
                SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
                Date ymd1 = new Date();
                String today = format1.format(ymd1);
                
                pstmt = con.prepareStatement(sql);
                //데이터 셋팅
                pstmt.setString(1, today);

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
                	System.out.println("지급여부  =" + use_yn       );
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
        	
        	
        }else if(num == 2 || num == 4 || num == 5)
        {
        	System.out.println("쿠폰처리 결과 테스트확인");
        	String sql = "select * from rest_coupon where coupon_number = ?";
        	
            Scanner myInput2_1 = new Scanner (System.in);
            System.out.print("사용자쿠폰번호 : ");
            String coupon_number_2_1 = myInput2_1.nextLine();
        	
        	
            try {
                Class.forName(className);
                con = DriverManager.getConnection(url, user, passwd);
                System.out.println("Connect Success!");
                

                
                pstmt = con.prepareStatement(sql);
                //데이터 셋팅
                pstmt.setString(1, coupon_number_2_1);

                // pstmt.excuteQuery() : select
                // pstmt.excuteUpdate() : insert, update, delete
                // SQL 문장 실행 후, 변경된 row 수 int type 리턴
                ResultSet rc = pstmt.executeQuery();
                
                while (rc.next()) {
                	String base_ymd_2      = rc.getString("base_ymd"     );
                	String coupon_number_2 = rc.getString("coupon_number");
                	String expir_ymd_2     = rc.getString("expir_ymd"    );
                	String use_yn_2        = rc.getString("use_yn"       );
                	String use_yn_ck_2     = rc.getString("use_yn_ck"    );
                	String residentno_2    = rc.getString("residentno"   );
                	String phonnumber_2    = rc.getString("phonnumber"   );
                	
                	System.out.println("발행일자  =" + base_ymd_2     );
                	System.out.println("쿠폰번호  =" + coupon_number_2);
                	System.out.println("만기일자  =" + expir_ymd_2    );
                	System.out.println("지급여부  =" + use_yn_2       );
                	System.out.println("사용체크  =" + use_yn_ck_2    );
                	System.out.println("실명번호  =" + residentno_2   );
                	System.out.println("전화번호  =" + phonnumber_2   );
                
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
        	
        }else if(num == 3)
        {
        	System.out.println("조회서비스로 test.java 사용");
        	
        }else if(num == 6)
        {
        	System.out.println("조회서비스로 test.java 사용");
        }else if(num == 7)
        {
        	System.out.println("조회서비스로 test.java 사용");
        }
	}

}
