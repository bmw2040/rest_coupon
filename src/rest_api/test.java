package rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class test {
	static String residentno    = null;
	static String phonnumber    = null;
	static String coupon_number = null;
	static String expir_ymd     = null;
	
	static String base_ymd     = null;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        int coupon_cnt = 0;
        
       //1.������ �ڵ��� ������ N�� �����Ͽ� �����ͺ��̽��� �����ϴ� API�� �����ϼ��� db_insert ó��
        Scanner myInput1_1 = new Scanner (System.in);
        System.out.print("������������ : ");
        coupon_cnt = myInput1_1.nextInt();
        
        long beforeTime = System.currentTimeMillis();
        db_insert db_in = new db_insert(coupon_cnt);
        db_in.show();
        long afterTime = System.currentTimeMillis(); 
        
        long secDiffTime = (afterTime - beforeTime)/1000; // ms �� �ʷ� ��ȯ
        System.out.println("���۽ð�:"+ afterTime+ "|| ����ð�:" +beforeTime +"(s : "+secDiffTime);
        long secDiffTime1 = (afterTime - beforeTime);
        System.out.println("���۽ð�:"+ afterTime+ "|| ����ð�:" +beforeTime +"(ms: "+secDiffTime1);
        
        

        // 2.������ ������ �ϳ��� ����ڿ��� �����ϴ� API�� �����ϼ��� db_up1 ó��
//        Scanner myInput2_1 = new Scanner (System.in);
//        System.out.print("����ڽǸ��ȣ : ");
//        residentno = myInput2_1.nextLine();
//
//        Scanner myInput2_2 = new Scanner (System.in);
//        System.out.print("�������ȭ��ȣ : ");
//        phonnumber = myInput2_2.nextLine();
//        
//        db_up1 db_up1 = new db_up1(residentno, phonnumber);
//        db_up1.show();



        // 3.����ڿ��� ���޵� ������ ��ȸ�ϴ� API�� �����ϼ���. db_se1 ó��
//        Scanner myInput3_1 = new Scanner (System.in);
//        System.out.print("����ڽǸ��ȣ : ");
//        residentno = myInput3_1.nextLine();
//
//        Scanner myInput3_2 = new Scanner (System.in);
//        System.out.print("�������ȭ��ȣ : ");
//        phonnumber = myInput3_2.nextLine();
//        
//        db_se1 db_se1 = new db_se1(residentno, phonnumber);
//        db_se1.show();

        
        
        // 4.���޵� ������ �ϳ��� ����ϴ� API�� �����ϼ���. (���� ������ �Ұ�) db_up2 ó��
//        Scanner myInput4_1 = new Scanner (System.in);
//        System.out.print("����ڽǸ��ȣ : ");
//        residentno = myInput4_1.nextLine();
//
//        Scanner myInput4_2 = new Scanner (System.in);
//        System.out.print("�������ȭ��ȣ : ");
//        phonnumber = myInput4_2.nextLine();
//
//        Scanner myInput4_3 = new Scanner (System.in);
//        System.out.print("�����������ȣ : ");
//        coupon_number = myInput4_3.nextLine();
//  
//        db_up2 db_up2 = new db_up2(residentno, phonnumber, coupon_number);
//        db_up2.show();
        
        // 5. ���޵� ������ �ϳ��� ��� ����ϴ� API�� �����ϼ���. (��ҵ� ���� ���� ����)
//        Scanner myInput5_1 = new Scanner (System.in);
//        System.out.print("�����������ȣ : ");
//        coupon_number = myInput5_1.nextLine();
//  
//        db_up3 db_up3 = new db_up3(coupon_number);
//        db_up3.show();
        
        
        
        // 6. �߱޵� ������ ���� ����� ��ü ���� ����� ��ȸ�ϴ� API�� �����ϼ���. �Է����� �������ڷ� ��ȸ
//        Scanner myInput6_1 = new Scanner (System.in);
//        System.out.print("�������� : ");
//        expir_ymd = myInput6_1.nextLine();
//  
//        db_se2 db_se2 = new db_se2(expir_ymd);
//        db_se2.show();

        

        // 7. �߱޵� ������ ���� 3���� ����ڿ��� �޼���(�������� 3�� �� ����˴ϴ�.��)�� �߼��ϴ� ����� �����Ͽ�.
        // (���� �޼����� �߼��ϴ°��� �ƴ� stdout ������ ����Ͻø� �˴ϴ�.)
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyyMMdd");
//
//        cal.add(Calendar.DATE, -3);
//        String expir_3df_ymd = df.format(cal.getTime());
//        
//        db_se3 db_se3 = new db_se3(expir_3df_ymd);
//        db_se3.show();
        
        // csv ���� ����
//        Scanner input_1 = new Scanner (System.in);
//        System.out.print("�������� : ");
//        base_ymd = input_1.nextLine();
//
//        db_se4 db_se4 = new db_se4(base_ymd);
//        db_se4.show();        
        
	}


	

}
