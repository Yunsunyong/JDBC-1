-- bookManager_v5/dbscript/book.sql

DROP TABLE BOOK CASCADE CONSTRAINTS;

CREATE TABLE BOOK (
    BOOK_ID  NUMBER,
    TITLE   VARCHAR2(50) CONSTRAINT NN_TITLE NOT NULL,
    AUTHOR  VARCHAR2(20) CONSTRAINT NN_AUTHOR NOT NULL,
    PUBLISHER VARCHAR2(20) CONSTRAINT NN_PUBLISHER NOT NULL,
    PUBLISH_DATE  DATE CONSTRAINT NN_PUBLISHDATE NOT NULL,
    PRICE  NUMBER CONSTRAINT NN_PRICE NOT NULL,
    CONSTRAINT PK_BID PRIMARY KEY (BOOK_ID)
);

COMMENT ON COLUMN BOOK.BOOK_ID IS '도서번호';
COMMENT ON COLUMN BOOK.TITLE IS '책제목';
COMMENT ON COLUMN BOOK.AUTHOR IS '저자명';
COMMENT ON COLUMN BOOK.PUBLISHER IS '출판사';
COMMENT ON COLUMN BOOK.PUBLISH_DATE IS '출판날짜';
COMMENT ON COLUMN BOOK.PRICE IS '가격';

CREATE SEQUENCE SEQ_BID
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO BOOK VALUES (SEQ_BID.NEXTVAL, '자바자료구조', '홍길동', 
'한빛', TO_DATE('17/09/28', 'RR/MM/DD'), 25000);

INSERT INTO BOOK VALUES (SEQ_BID.NEXTVAL, '초보자를 위한 자바', '이순신', 
'정보문화사', TO_DATE('18/07/15', 'RR/MM/DD'), 25200);

INSERT INTO BOOK VALUES (SEQ_BID.NEXTVAL, '자바입문', '김유신', 
'프리렉', TO_DATE('12/02/10', 'RR/MM/DD'), 20000);

INSERT INTO BOOK VALUES (SEQ_BID.NEXTVAL, '자바디자인패턴', '김춘추', 
'프리렉', TO_DATE('11/09/01', 'RR/MM/DD'), 27000);

INSERT INTO BOOK VALUES (SEQ_BID.NEXTVAL, '자바시큐어코딩', '유관순', 
'인피니티', TO_DATE('17/06/25', 'RR/MM/DD'), 22500);

SELECT * FROM BOOK;

COMMIT;




