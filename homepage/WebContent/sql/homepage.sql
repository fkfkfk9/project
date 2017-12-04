create table qt_1 (
   num NUMBER not null,
   id varchar2(15) not null,
   name  varchar2(10) not null,
   title varchar2(100) not null,
   content varchar2(4000) not null,
   path varchar2(100) not null,
   day varchar2(20),
   hit NUMBER,
   primary key(num)
);

CREATE SEQUENCE sequ_qt_1_num;

create table qt_2 (
   num NUMBER not null,
   id varchar2(15) not null,
   s_id varchar2(15) not null,
   name  varchar2(10) not null,
   title varchar2(100) not null,
   content varchar2(4000) not null,
   path varchar2(100) not null,
   e_check varchar2(1) not null,
   day varchar2(20),
   hit NUMBER,
   primary key(num)
);

CREATE SEQUENCE sequ_qt_2_num;

create table menu05 (
   num NUMBER not null,
   id varchar2(15) not null,
   name  varchar2(10) not null,
   content varchar2(4000) not null,
   regist_day varchar2(20),
   primary key(num)
);

CREATE SEQUENCE sequ_menu05_num;

create table menu05_ripple (
   num NUMBER not null,
   parent NUMBER not null,
   id varchar2(15) not null,
   name  varchar2(10) not null,
   content varchar2(4000) not null,
   regist_day varchar2(20),
   primary key(num)
);

CREATE SEQUENCE sequ_menu05_ripple_num;

create table galley (
  num    varchar2(15) not null,
  id  varchar2(15) not null,
  name varchar2(15) not null,
  path  varchar2(100) not null,
  title    varchar2(20)  not null,
  content varchar2(80),
  day varchar2(20),
  hit NUMBER,
  primary key(num)
)

create table menu02 (
   num NUMBER not null,
   id varchar2(15) not null,
   name  varchar2(10) not null,
   subject varchar2(100) not null,
   content varchar2(4000) not null,
   regist_day varchar2(20),
   class varchar2(20),
   hit NUMBER,
   primary key(num)
);

CREATE SEQUENCE sequ_menu02_num;

  create table member (
  id    varchar2(15) not null,
  pass  varchar2(15) not null,
  name  varchar2(10) not null,
  hp    varchar2(20)  not null,
  email varchar2(80),
  regist_day varchar2(20),
  level NUMBER,
  primary key(id)
  );