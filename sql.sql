--Database restdb

CREATE TABLE REST_COUPON(
  BASE_YMD      VARCHAR(8)  NOT NULL, -- 기준일자
  COUPON_NUMBER VARCHAR(25) NOT NULL, -- 쿠폰번호
  EXPIR_YMD     VARCHAR(8),           -- 만료일자
  USE_YN        VARCHAR(1),           -- 지급여부
  USE_YN_CK     VARCHAR(1),           -- 사용여부체크
  USE_YMD       VARCHAR(8),           -- 사용일자
  RESIDENTNO    VARCHAR(14),          -- 실명번호
  PHONNUMBER    VARCHAR(20)           -- 전화번호
);

-- 전체 조회용 쿼리
select count(*) as cnt
  from rest_coupon
  where BASE_YMD = '20210320'
;

-- 쿠폰 조회용 쿼리
select *
  from rest_coupon
 where coupon_number = '20210320IN8643ON3R023752' 
;


-- 기준일자 전체 데이터 삭제
delete from rest_coupon
where BASE_YMD = '20210320'
;

commit;

-- 과제1 등록 건수 조회용
select count(*) as cnt -- 등록건수
  from rest_coupon
  where base_ymd = '20210320' -- 등록일자

;


update rest_coupon
set USE_YN = 'Y'
 , USE_YN_CK = 'N'
 , RESIDENTNO = '222'
 , PHONNUMBER = '111'
  where base_ymd = '20210317' -- 등록일자
   and coupon_number = '20210320P385V1UF5RS070025'
  ;


 
  
select RESIDENTNO
    , PHONNUMBER
    , count(COUPON_NUMBER) AS cnt
 from rest_coupon
 where expir_ymd = '20210317'
   and USE_YN    = 'Y'
   and USE_YN_CK = 'N'
   group by RESIDENTNO
    , PHONNUMBER 

;

SHOW VARIABLES LIKE "secure_file_priv";

INTO OUTFILE '/tmp/orders.csv' →  INTO OUTFILE 'E:\입사 이력서 는곳\카카오페이_사전과제\\orders.csv' 
;

SHOW VARIABLES LIKE "secure_file_priv";



SELECT * FROM rest_coupon
INTO OUTFILE 'rest_coupon.csv'
CHARACTER SET euckr
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '\\'
LINES TERMINATED BY '\n'
;


mysql -p my_db -e "SELECT * FROM my_table" | sed 's/\t/","/g;s/^/"/;s/$/"/;' > my_table.csv