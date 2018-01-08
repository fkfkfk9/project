실행방법

1. mobilesoftware 이름으로 다이나믹 웹 프로젝트를 생성
2. WebContent, src내용 복사
3. WEB-INF폴더의 lib와 web.xml 꼭 같아야함
4. Server.xml의 Context태그중 mobilesoftware로 되어있는것 아래 내용 추가 
   본인 오라클 계정 정보 추가한다.
<Resource auth="Container" 
	driverClassName="oracle.jdbc.driver.OracleDriver" 
	maxWait="5000" 
	name="jdbc/homepage" 
	password="오라클 계정 암호" 
	type="javax.sql.DataSource" 
	url="jdbc:oracle:thin:@localhost:1521/xe" 
	username="오라클 계정명"/>
5. sql파일의 내용을 해당 오라클 계정에서 실행하여 테이블 추가
6. enc폴더 cryptProcess를 실행하여 admin계정의 암호를 암호화 해준다. 
   관리자 계정은 계정명 : admin , 암호 : 1111
7. 로컬주소에 /mobilesoftware/index.do 입력하여 실행
------------------------------------------------------------------------------------
프로젝트 파일 정보 
---------------------------------------------------------------------------------------
WebContent폴더
*******************************************************************************************
index.jsp : 다른 부분들은 고정되어 있고 section 부분만 
		    매번 컨트롤러가 보내는 페이지를 뿌려줌

sql/mobilesoftware.sql : DB에 사용되는 테이블들의 모든 sql문이 들어가있음

propert/commandmapping.propert : .do로 사용되는 명령어들의 키와 값이 저장되어있다.
								  controller.java파일에서 참조
								  
enc/cryptProcess.jsp : 맨처음 관리자 계정을 등록 해놓고 암호화를 하지 못하기 때문에 그 하나를 암호화 해주는 페이지
******************************************************************************************* 				  
WebContent/lib폴더 : 처음 메인페이지를 구성하는대 section부를 제외한 나머지 부분들을 구현

side_menu : 처음 들어가는 index.do에서 section부테 들어가는 내용.
bottom.jsp : 모든 화면에서 맨 아래 들어가는 내용 index.jsp에서 불러온다.
top_login1.jsp : 학교 로고와 로그인 부분이 들아간다. index.jsp에서 불러옴
				 로그인 여부, 사용자 레벨에 따라 달라짐 세멘틱에서 헤더부분
top_menu1.jsp : 세멘틱 태그에서 nav부분으로 메뉴들을 드랍다운으로 처리 index.jsp에서
			    불러와 어느페이지를 가도 매번 보여준다.
main.jsp : 맨처음 index.do를 실행할때 보여주는 페이지로 학교 사진다 위에 side_menu로 구성
*******************************************************************************************
WebContent/login 폴더 : 로그인, 회원가입, 로그아웃등 member DB와 연관된 작업들을 모음

login_form.jsp : 로그인을 하기위한 입력을 받는다.
join_form.jsp : 회원가입흘 하기 위한 입력을 받는다. 관리자만 회원가입 권한이 있다.
update_form.jsp : 회원정보를 뿌려주고 그 내용을 수정하도록 입력받는다.

*******************************************************************************************
majorInfo : 학과 소개텝의 파일들을 모아놓은 폴더 menu01

majorinfo.jsp : 학과소개 페이지를 작성한다.
deanGreeting.jsp : 학과장 인사말을 작성한다.
professorInfo.jsp : 교수소개 페이지를 작성한다.
menu01_leftmenu.jsp : majorInfo폴더안의 모든 파일들이 참조하는 왼쪽 메뉴

*******************************************************************************************
WebContent/js폴더 : 모든 js파일들을 모아놓은 폴더

lib/topLogin.js : 헤더부분의 검색기능 버튼의 클릭이벤트를 위해 생성
login/login_form.js : 로그인창에서 입력여부 체크하고 버튼 클릭시 ajax를 이용하여 DB의 회원정보와 비교
login/join_form.js : 회원가입창에서 입력여부, 아이디 중복, 회원가입까지 처리해준다.

*******************************************************************************************
WebContent/css폴더 : 모든 css파일을 모아놓은 폴더

common.css : 메인 페이지에 관련된 css들을 모아놓았다 섹션을 제외하곤 모든 디자인이 같기 때문에 모든곳에서 사용됨
login.css : 로그인 창에서만 사용되는 css파일
member.css : 회원가입창 css파일
	
----------------------------------------------------------------------------------------
src 폴더

homepage.controller.Controller.java
 모든 작동은 Controller.java파일을 통해 작동 .do명령어를 입력하면 
 Controller가 해당 명령어에 해당하는 객체를 실행 후 index.jsp에 뿌려준다.
 
 admin.enc/PassCrypt : member테이블의 모든 데이터의 암호를 암호화 해주는 기능으로 맨처음 한번만 작동
 
 enumfile/ReturnInt : 메소드들이 int로 값을 리턴할 때 자주사용되는 1,0,-1을 상수로 지정해놓았다.
 
 work.crypt : 암호를 암호화 해주는 클래스 파일들
 
****************************************************************************************** 

bean.member : 회원 정보 DB BEAN이 들어있고 그와 관련된 회원가입이나, 로그인, 회원정보 수정등의 메소드 존재
bean.menu02 : 학과 게시판 DB BEAN이 들어있다.
bean.menu03 : 공지사항 DB BEAN이 들어있다.
bean.menu04 : 갤러리 DB BEAN이 들어있다.
bean.menu05 : 낙서장 DB BEAN이 들어있다.
bean.menu06 : qna, 1:1답변 DB BEAN이 들어있다.
menu02~menu06까지는 모두 게시판 관련 DB들이다.

******************************************************************************************

command : propert파일에 들어있는 커맨드들 .do에 의해 실행된다.
CommandAction.java : 인터페이스 모든 커멘드들은 이 인터페이스를 상속받는다.

******************************************************************************************

homepage.command/MainAction.java : index.do로 실행되는 부분으로 메인페이지를 불러온다.

******************************************************************************************
homepage.command.login : 로그인에 관련된 커맨드를 모아놓은 패키지

LoginProAction.java : member bean을 이용하여 로그인 작업을 한다.
IdCheckAction.java : member bean을 이용하여 테이블에 중복되는 아이디가 있는지 찾는다.
JoinProAction.java : member bean을 이용하여 회원가입 작업을 한다.
PassCheckAction.java : 회원정보 수정시 입력된 암호가 id에 해당하는 암호가 맞는지 member bean을 통해 확인
UpdateProAction.java : 회원정보 수정폼에서 입력된 내용을 DB에서 update해서 반영

******************************************************************************************
homepage.command.toplogin : 페이지의 맨위에 존재하는 로그인, 로그아웃, 회원가입, 검색에 관련된 커맨드

AllSearchAction.java : 검색어의 내용을 모든 게시판 테이블에서 검색해본다.
JoinFormAction.java : 회원가입 폼을 불러온다.
LoginFormAction.java : 로그인 폼을 불러온다.
LogoutAction.java : 로그아웃 작업을 하고 메인페이지로 돌아간다.
updateFormAction.jva : 해당 id에 맞는 정보를 가져와서 회원정보수정 폼을 불러온다.

********************************************************************************************
homepage.command.majorinfo : 학과소개(menu01)에 해당되는 학과소개관련 커멘드 모음

MajorInfoAction.java : 학과 소개페이지를 불러온다.
DeanGreetingAction.java : 학과장 인사 페이지를 불러온다.
professorInfoAction.java : 교수소개 페이지를 불러온다.

*********************************************************************************************
homepage.command.majorboard : 학과 게시판(menu02)에 관련된 커멘드 모음

MajorBoardAction.java : 메뉴2의 메인페이지 각각의 페이지로 가는 안내페이지를 불러온다.
MajorBoardListAction.java : 게시판을 조회해서 보여주는 페이지 학년과 과목을 get으로 받아 다르게 출력해준다.





