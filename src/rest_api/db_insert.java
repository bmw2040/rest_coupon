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

        int max_cnt    = 100000; // db ���� �ִ�Ǽ�

        int coupon_cnt = 0;
        int db_in_cnt  = 0;
        int end_cnt    = 0;
        
        String[] temp = new String[max_cnt]; // DB insert �� �迭(�ߺ�����)

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
        	System.out.println("�ѹ��� ���డ�ɼ���("+ max_cnt +")�� �Ѿ����ϴ�. �Է¼���:" + coupon_cnt);
        	System.exit(0);
        }
        
        System.out.println("�Է¹��� ������� " + coupon_cnt + " �� �Դϴ�.");
        
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
        Date ymd1 = new Date();
        String base_ymd = format1.format(ymd1);
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        String expir_ymd = format2.format(cal.getTime());
        
        //������ ����� ����
        Random rand = new Random();
        StringBuffer buf = new StringBuffer();
        
        // 11�ڸ� ���� + ���� ���� ���ڿ� ������ ���� 25�ڸ� �������ڿ������� ����
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
            
            // �������� + ���� + ����ð�
            coupon = base_ymd + buf + time;
            
            buf.delete(0, buf.length()); // ���� �ʱ�ȭ
       		
       		temp[cnt] = coupon; // �ӽ� �迭�� ����

       		// �ߺ�����
       		for(int i=0; i<cnt; i++)
       		{
       			if(temp[cnt] == temp[i])
				{
       				cnt--;
				}       			
       		}
        }
       	
       	// db insert ó���� �迭���� ��ȸ
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

               	// ������ ����ó��
                for(int i=0; i<db_in_cnt; i++)
                {
	                //������ ����
	                pstmt.setString(1, base_ymd); // ������������
	                pstmt.setString(2, temp[i]); // ������ȣ 
	                pstmt.setString(3, expir_ymd); // ��������
	                pstmt.setString(4, "N"); // ��뿩��
	                pstmt.setString(5, "N"); // ��뿩��üũ
	                pstmt.setString(6, ""); // ����ڽǸ��ȣ
	                pstmt.setString(7, ""); // �������ȭ��ȣ
	
	                // pstmt.excuteQuery() : select
	                // pstmt.excuteUpdate() : insert, update, delete
	                // SQL ���� ���� ��, ����� row �� int type ����

	                
	                // addBatch�� ���
	                pstmt.addBatch();
	                 
	                // �Ķ���� Clear
	                pstmt.clearParameters() ;

	                
	                
	                if( (i % 5000) == 0  )
	                {
	                    // Batch ����
	                    pstmt.executeBatch() ;
	                    System.out.println("�Էµ� row : " + end_cnt);
	                    // Batch �ʱ�ȭ
	                    pstmt.clearBatch();

	                    // Ŀ��
	                    //con.commit() ;
	                }
	                
	                // Ŀ�Ե��� ���� ������ ������ ���Ͽ� Ŀ��
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
        System.out.println("��������� " + cnt + "�Դϴ�.");
    }
}
