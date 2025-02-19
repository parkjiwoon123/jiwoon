-----------프로젝트 전 필수사항---------------------------

데이터데이스를 사용하기 위해 phonebook 테이블 생성

drop table phonebook;
drop squence phonebook;
create table phonebook(
idx number primry key,
name varchar2(20),
hp varchar2(13),
memo varchar2(50));

시퀸스 생성

create sequence phonebook_idx_seq increment by 1 start width 1;

데이터베이스를 이클립스에서 사용하기 위해서는 WEB-INF/lib 폴더아래 ojdbc8.jar 추가

4)code 작성
스파게티코드1 (phonebook_spag1) : 순서에 따라 코드 작성
스파게티코드2 (phonebook_spag2) : 코드와 view를 분리

