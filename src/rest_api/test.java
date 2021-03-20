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
        
       //1.랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현하세요 db_insert 처리
        Scanner myInput1_1 = new Scanner (System.in);
        System.out.print("발행쿠폰수량 : ");
        coupon_cnt = myInput1_1.nextInt();
        
        long beforeTime = System.currentTimeMillis();
        db_insert db_in = new db_insert(coupon_cnt);
        db_in.show();
        long afterTime = System.currentTimeMillis(); 
        
        long secDiffTime = (afterTime - beforeTime)/1000; // ms 를 초로 변환
        System.out.println("시작시간:"+ afterTime+ "|| 종료시간:" +beforeTime +"(s : "+secDiffTime);
        long secDiffTime1 = (afterTime - beforeTime);
        System.out.println("시작시간:"+ afterTime+ "|| 종료시간:" +beforeTime +"(ms: "+secDiffTime1);
        
        

        // 2.생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요 db_up1 처리
//        Scanner myInput2_1 = new Scanner (System.in);
//        System.out.print("사용자실명번호 : ");
//        residentno = myInput2_1.nextLine();
//
//        Scanner myInput2_2 = new Scanner (System.in);
//        System.out.print("사용자전화번호 : ");
//        phonnumber = myInput2_2.nextLine();
//        
//        db_up1 db_up1 = new db_up1(residentno, phonnumber);
//        db_up1.show();



        // 3.사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요. db_se1 처리
//        Scanner myInput3_1 = new Scanner (System.in);
//        System.out.print("사용자실명번호 : ");
//        residentno = myInput3_1.nextLine();
//
//        Scanner myInput3_2 = new Scanner (System.in);
//        System.out.print("사용자전화번호 : ");
//        phonnumber = myInput3_2.nextLine();
//        
//        db_se1 db_se1 = new db_se1(residentno, phonnumber);
//        db_se1.show();

        
        
        // 4.지급된 쿠폰중 하나를 사용하는 API를 구현하세요. (쿠폰 재사용은 불가) db_up2 처리
//        Scanner myInput4_1 = new Scanner (System.in);
//        System.out.print("사용자실명번호 : ");
//        residentno = myInput4_1.nextLine();
//
//        Scanner myInput4_2 = new Scanner (System.in);
//        System.out.print("사용자전화번호 : ");
//        phonnumber = myInput4_2.nextLine();
//
//        Scanner myInput4_3 = new Scanner (System.in);
//        System.out.print("사용자쿠폰번호 : ");
//        coupon_number = myInput4_3.nextLine();
//  
//        db_up2 db_up2 = new db_up2(residentno, phonnumber, coupon_number);
//        db_up2.show();
        
        // 5. 지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요. (취소된 쿠폰 재사용 가능)
//        Scanner myInput5_1 = new Scanner (System.in);
//        System.out.print("사용자쿠폰번호 : ");
//        coupon_number = myInput5_1.nextLine();
//  
//        db_up3 db_up3 = new db_up3(coupon_number);
//        db_up3.show();
        
        
        
        // 6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요. 입력일자 만료일자로 조회
//        Scanner myInput6_1 = new Scanner (System.in);
//        System.out.print("만료일자 : ");
//        expir_ymd = myInput6_1.nextLine();
//  
//        db_se2 db_se2 = new db_se2(expir_ymd);
//        db_se2.show();

        

        // 7. 발급된 쿠폰중 만료 3일전 사용자에게 메세지(“쿠폰이 3일 후 만료됩니다.”)를 발송하는 기능을 구현하요.
        // (실제 메세지를 발송하는것이 아닌 stdout 등으로 출력하시면 됩니다.)
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyyMMdd");
//
//        cal.add(Calendar.DATE, -3);
//        String expir_3df_ymd = df.format(cal.getTime());
//        
//        db_se3 db_se3 = new db_se3(expir_3df_ymd);
//        db_se3.show();
        
        // csv 파일 생성
//        Scanner input_1 = new Scanner (System.in);
//        System.out.print("발행일자 : ");
//        base_ymd = input_1.nextLine();
//
//        db_se4 db_se4 = new db_se4(base_ymd);
//        db_se4.show();        
        
	}


	

}
