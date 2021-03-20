--Database restdb

CREATE TABLE REST_COUPON(
  BASE_YMD      VARCHAR(8)  NOT NULL, -- ��������
  COUPON_NUMBER VARCHAR(25) NOT NULL, -- ������ȣ
  EXPIR_YMD     VARCHAR(8),           -- ��������
  USE_YN        VARCHAR(1),           -- ���޿���
  USE_YN_CK     VARCHAR(1),           -- ��뿩��üũ
  USE_YMD       VARCHAR(8),           -- �������
  RESIDENTNO    VARCHAR(14),          -- �Ǹ��ȣ
  PHONNUMBER    VARCHAR(20)           -- ��ȭ��ȣ
);

-- ��ü ��ȸ�� ����
select count(*) as cnt
  from rest_coupon
  where BASE_YMD = '20210320'
;

-- ���� ��ȸ�� ����
select *
  from rest_coupon
 where coupon_number = '20210320IN8643ON3R023752' 
;


-- �������� ��ü ������ ����
delete from rest_coupon
where BASE_YMD = '20210320'
;

commit;

-- ����1 ��� �Ǽ� ��ȸ��
select count(*) as cnt -- ��ϰǼ�
  from rest_coupon
  where base_ymd = '20210320' -- �������

;


update rest_coupon
set USE_YN = 'Y'
 , USE_YN_CK = 'N'
 , RESIDENTNO = '222'
 , PHONNUMBER = '111'
  where base_ymd = '20210317' -- �������
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

INTO OUTFILE '/tmp/orders.csv' ��  INTO OUTFILE 'E:\�Ի� �̷¼� �°�\īī������_��������\\orders.csv' 
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