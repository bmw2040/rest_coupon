package rest_api;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class db_insert {
    int cnt;
    
    db_insert() {
        cnt = 0;
    }
    
    db_insert(int radius) {
        this.cnt = radius;

        int max_cnt    = 100000; // db 저장 최대건수

        int coupon_cnt = 0;
        int db_in_cnt  = 0;
        int end_cnt    = 0;
        
        String[] temp = new String[max_cnt]; // DB insert 용 배열(중복제거)

        Connection con = null;
        PreparedStatement pstmt = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        
        
        String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user      = "root";
        String passwd    = "bmw!4015";
        
        String sql = "insert into rest_coupon(base_ymd, coupon_number, expir_ymd, use_yn, use_yn_ck, residentno, phonnumber ) values(?, ?, ?, ?, ?, ?, ?)";

        coupon_cnt = cnt;
        
        if(coupon_cnt > max_cnt)
        {
        	System.out.println("한번에 발행가능수량("+ max_cnt +")을 넘었습니다. 입력수량:" + coupon_cnt);
        	System.exit(0);
        }
        
        System.out.println("입력받은 발행수량 " + coupon_cnt + " 개 입니다.");
        
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
        Date ymd1 = new Date();
        String base_ymd = format1.format(ymd1);
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        String expir_ymd = format2.format(cal.getTime());
        
        //난수가 저장될 변수
        Random rand = new Random();
        StringBuffer buf = new StringBuffer();
        
        // 11자리 숫자 + 문자 조합 문자열 데이터 생성 25자리 쿠폰문자열데이터 생성
       	for(int cnt=0; cnt < coupon_cnt; cnt++)
       	{
       		for(int i = 0; i < 11; i++)
       		{
       			if(rand.nextBoolean())
       			{
       				buf.append((char)((int)(rand.nextInt(26))+65));
       			}else
       			{
       				buf.append((rand.nextInt(10)));
       			}
       		}
            
       		SimpleDateFormat format3 = new SimpleDateFormat ( "HHmmss");
       		Date ymd2 = new Date();
       		String time = format3.format(ymd2);
       		
            String coupon ="";
            
            // 생성일자 + 난수 + 발행시간
            coupon = base_ymd + buf + time;
            
            buf.delete(0, buf.length()); // 버퍼 초기화
       		
       		temp[cnt] = coupon; // 임시 배열에 저장

       		// 중복제거
       		for(int i=0; i<cnt; i++)
       		{
       			if(temp[cnt] == temp[i])
				{
       				cnt--;
				}       			
       		}
        }
       	
       	// db insert 처리전 배열갯수 조회
       	for(int i=0; i<temp.length; i++)
       	{
       		if( temp[i] != null )
       		{
       			db_in_cnt++;	
       		}else
       		{
       			break;
       		}
       	}

       	try {
                Class.forName(className);
                con = DriverManager.getConnection(url, user, passwd);
                System.out.println("Connect Success!");
                
                pstmt = con.prepareStatement(sql);

               	// 데이터 저장처리
                for(int i=0; i<db_in_cnt; i++)
                {
	                //데이터 셋팅
	                pstmt.setString(1, base_ymd); // 쿠폰발행일자
	                pstmt.setString(2, temp[i]); // 쿠폰번호 
	                pstmt.setString(3, expir_ymd); // 만기일자
	                pstmt.setString(4, "N"); // 사용여부
	                pstmt.setString(5, "N"); // 사용여부체크
	                pstmt.setString(6, ""); // 사용자실명번호
	                pstmt.setString(7, ""); // 사용자전화번호
	
	                // pstmt.excuteQuery() : select
	                // pstmt.excuteUpdate() : insert, update, delete
	                // SQL 문장 실행 후, 변경된 row 수 int type 리턴

	                
	                // addBatch에 담기
	                pstmt.addBatch();
	                 
	                // 파라미터 Clear
	                pstmt.clearParameters() ;

	                
	                
	                if( (i % 5000) == 0  )
	                {
	                    // Batch 실행
	                    pstmt.executeBatch() ;
	                    System.out.println("입력된 row : " + end_cnt);
	                    // Batch 초기화
	                    pstmt.clearBatch();

	                    // 커밋
	                    //con.commit() ;
	                }
	                
	                // 커밋되지 못한 나머지 구문에 대하여 커밋
	                pstmt.executeBatch() ;
	                //con.commit() ;
	                
	                end_cnt++;
                }
            } catch(Exception e) {
                System.out.println("Connect Failed!");
                e.printStackTrace();
            } finally {
            	if (pstmt != null)
            	{
            		try { 
            			pstmt.close(); 
            			}catch (SQLException e)
            			{
            				e.printStackTrace(); 
            			}
            	}
        		if (con != null)
        		{
        			try { 
        				con.close(); 
        				}catch (SQLException e)
        				{
        					e.printStackTrace(); 
        				}
        		}
            }
    }
    

    
	void show() {
        System.out.println("쿠폰발행수 " + cnt + "입니다.");
    }
}
