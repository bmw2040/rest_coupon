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
		
		String Location = "E:\\�Ի� �̷¼� �°�\\īī������_��������\\rest_coupon.csv";
		
		test2.loadFile(Location);
		test2.getConn();
		test2.excute(args);
        
	}

	public void loadFile(String Location) throws IOException {
		  file = new File(Location);
		  fr = new FileReader(file);
		  in = new BufferedReader(fr);
		  // TXT ������ �ҷ��ͼ� FileReader ��ü�� ���� �� ���ۿ� �ִ´�.
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
		   System.out.println("������� �ʾҽ��ϴ�.");
		  } // ����̹� ����
		  
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

				   
				   // 2. ���õ� �Ӽ��� INSERT �Ѵ�.
				   while ((temp = in.readLine()) != null)
				   {
					   index++;
					   String[] columnsContent = temp.split(",");
					   System.out.println("columnsContent[0]  =" + columnsContent[0]     );
					   System.out.println("columnsContent[1]  =" + columnsContent[1]     );
					   System.out.println("columnsContent[2]  =" + columnsContent[2]     );
					   System.out.println("columnsContent[3]  =" + columnsContent[3]     );
					   System.out.println("columnsContent[4]  =" + columnsContent[4]     );

					   
					   pstmt.setString(1, columnsContent[0].trim()); // ������������
					   pstmt.setString(2, columnsContent[1].trim()); // ������ȣ 
					   pstmt.setString(3, columnsContent[2].trim()); // ��������
					   pstmt.setString(4, columnsContent[3].trim()); // ��뿩��
					   pstmt.setString(5, columnsContent[4].trim()); // ��뿩��üũ
					   pstmt.setString(6, residentno); // ����ڽǸ��ȣ
					   pstmt.setString(7, phonnumber); // �������ȭ��ȣ
			   
						   
						   // addBatch�� ���
			               pstmt.addBatch();
			                 
			               // �Ķ���� Clear
			               pstmt.clearParameters() ;

			               if( (index % 10000) == 0  )
			               {
			            	   // Batch ����
			                   pstmt.executeBatch() ;

			                   // Batch �ʱ�ȭ
			                   pstmt.clearBatch();

			                   // Ŀ��
			                   //con.commit() ;
			               }
			                
			               // Ŀ�Ե��� ���� ������ ������ ���Ͽ� Ŀ��
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
				} // DB ���ῡ ����� ��ü�� Query������ ���� ����� ��ü�� �ݴ´�.
	}

		
		 
}


		 
