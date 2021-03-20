package rest_api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test2 {
	 File file;
	 FileReader fr;
	 BufferedReader in;
	 String[] attrName;
	 String[] columnsContent;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		test2 test2 = new test2();
		
		String Location = "E:\\입사 이력서 는곳\\카카오페이_사전과제\\rest_coupon.csv";
		
		test2.loadFile(Location);
		test2.getConn();
		test2.excute(args);
        
	}

	public void loadFile(String Location) throws IOException {
		  file = new File(Location);
		  fr = new FileReader(file);
		  in = new BufferedReader(fr);
		  // TXT 파일을 불러와서 FileReader 객체에 넣은 후 버퍼에 넣는다.
	}
	
	public Connection getConn() {
		  Connection conn = null;

		  String url       = "jdbc:mysql://localhost:3306/restdb?useSSL=false&useUnicode=true&characterEncoding=euckr";
		  String user      = "root";
		  String passwd    = "bmw!4015";
		  
		  try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		  } catch (ClassNotFoundException e) {
		   e.getMessage();
		   System.out.println("연결되지 않았습니다.");
		  } // 드라이버 연결
		  
		  try {
		   conn = DriverManager.getConnection(url, user, passwd);
		  } catch (SQLException e){
		   e.printStackTrace();
		  }
		  return conn;
	}
	
	private void excute(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Connection  conn  = null;
		PreparedStatement pstmt = null;

		StringTokenizer st = null;
	  
		String sql = "insert into rest_coupon(base_ymd, coupon_number, expir_ymd, use_yn, use_yn_ck, residentno, phonnumber ) values(?, ?, ?, ?, ?, ?, ?)";
		 
		int countToken = 0;
		int index = 0;
		
		try {
			   conn = getConn();
			   
			   String temp = "";
			   String residentno = "";
			   String phonnumber = "";
			   
			   temp = in.readLine();
			   
			   System.out.println("temp  =" + temp     );
			   
			   pstmt = conn.prepareStatement(sql);
			   
			   if (temp != null)
			   {

				   
				   // 2. 선택된 속성을 INSERT 한다.
				   while ((temp = in.readLine()) != null)
				   {
					   index++;
					   String[] columnsContent = temp.split(",");
					   System.out.println("columnsContent[0]  =" + columnsContent[0]     );
					   System.out.println("columnsContent[1]  =" + columnsContent[1]     );
					   System.out.println("columnsContent[2]  =" + columnsContent[2]     );
					   System.out.println("columnsContent[3]  =" + columnsContent[3]     );
					   System.out.println("columnsContent[4]  =" + columnsContent[4]     );

					   
					   pstmt.setString(1, columnsContent[0].trim()); // 쿠폰발행일자
					   pstmt.setString(2, columnsContent[1].trim()); // 쿠폰번호 
					   pstmt.setString(3, columnsContent[2].trim()); // 만기일자
					   pstmt.setString(4, columnsContent[3].trim()); // 사용여부
					   pstmt.setString(5, columnsContent[4].trim()); // 사용여부체크
					   pstmt.setString(6, residentno); // 사용자실명번호
					   pstmt.setString(7, phonnumber); // 사용자전화번호
			   
						   
						   // addBatch에 담기
			               pstmt.addBatch();
			                 
			               // 파라미터 Clear
			               pstmt.clearParameters() ;

			               if( (index % 10000) == 0  )
			               {
			            	   // Batch 실행
			                   pstmt.executeBatch() ;

			                   // Batch 초기화
			                   pstmt.clearBatch();

			                   // 커밋
			                   //con.commit() ;
			               }
			                
			               // 커밋되지 못한 나머지 구문에 대하여 커밋
			               pstmt.executeBatch() ;
						   
						   

				   }
			    }
			   
			  } catch (SQLException e)
				{
				  e.printStackTrace();
				} finally
				{
					if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
					if(conn  != null) try { conn.close();  } catch (SQLException e) {}	
				} // DB 연결에 사용한 객체와 Query수행을 위해 사용한 객체를 닫는다.
	}

		
		 
}


		 
