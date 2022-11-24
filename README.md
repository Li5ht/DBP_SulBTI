# DBP-Project-AlcholSom-

### Eclipse에서 local repository를 생성하고 project를 import하는 방법
( <> Code 눌러서 링크 복사)

1. File 메뉴 > Import... > Git | Projects from Git (with smart import)
2. Clone URI
3. fork된 URI 입력(예: https://github.com/Li5ht/DBP-Project-AlcholSom-.git), user & password 값 입력
4. master branch 선택 (또는 모든 branch 선택)
5. 생성할 local repository 경로 확인 (예: C:\Users\사용자\git\UserMan2)
6. 다음 화면(Imports project ...)에서 Finish
7. 프로젝트가 import되었는지 확인하고 프로젝트가 빌드 완료될 때까지 기다림
8. 오류가 표시되어 있으면 Maven > Update project.. 실행 (Alt+F5)
<br>

### 참고
- controller 패키지 : 에러뜬 건 아직 컨트롤러 수정하기 전이라 에러 뜨는 게 맞음
- filter 패키지 : src/main/webapp 밑에 생성된 폴더 중 URL을 통해 직접 접근이 필요한 폴더는 반드시 ResourceFilter 클래스의 22~25행과 같이 추가해야 함 (그냥 냅두면 됨)
- model 패키지 : DTO 클래스?
- model.dao 패키지 : ConnectionManager, JDBCUtil 클래스 + 우리가 정의한 dao 클래스들

##### model.dao에 Test 클래스로 dao 작성 잘 됐는지 실행 (Test.java -> Run As -> Java Application으로 실행)
##### 다른 패키지에 있는 클래스 사용 시 (model.dao 패키지에서 model 패키지에 있는 클래스 호출할 때) import 필수
  (ex. RecommendDao에서 Alcohol 객체 생성하려면 import model.Alcohol 해줘야 함. 
  그냥 에러뜨면 마우스 갖다 대서 솔루션 뜨는 거 중에 import '어쩌구' (model) 이런 식으로 적혀 있는 거 선택)

<br><br>
#### 22/11/22 수정사항 (큰 틀만)
- 코드 합침
- 읽다가 오타 보인 것들은 수정
- Review DTO : preference_id를 빼고 Member member, Alcohol alcohol, float rate로 대체 (쉽게 사용하기 위함)
- Review DTO : taste, flavor, corps 타입을 String -> int (원래 우리 거에 맞게 변경)
- DiaryDAO : createDiary, updateDiary, removeDiary에 preference 테이블 관리하는 부분이 빠진 거 같아서 추가 (혹시 있는데 내가 못본거라면 말해주기)
- DiaryDAO : removeDiary에서 파라미터를 diaryId에서 Diary diary로 변경 (preference 테이블에서 변경해줘야 하므로 memberId, drinkingList가 필요함)
- MemberDao : insertMember에서 member 테이블 식별자인 id를 id 시퀀스를 사용하게 변경 (id_seq.nextval로)
- AlcoholDAO : 술 필터링 기능 추가해놨으나 MyBatis로 변경 예정이라고 함
- AlcoholDAO : 술 디테일 + 리뷰 (read) 부분에서 "해당 술"의 디테일 + "해당 술"의 리뷰 리스트가 필요하길래 어쩌다보니 복잡한 쿼리문이 작성됨.. 편하게 하기 위해 어쩔 수 없었다....... 사실 쿼리문이 복잡하진 않은데 컬럼 이름 별칭 지정이랑 join이 대부분임. 이 부분 한번 보고 아닌 거 같다 싶으면 말해주기! Alcohol 객체랑 List<Review>를 한번에 전달해주기 위해 HashMap이라는 컬렉션 사용함 
- AlcoholDAO : insertReview에서 쿼리문 살짝 수정 (preference_id를 시퀀스 사용이 아닌, 해당 테이블이 있는지 확인하고 preference 테이블 관련 DB 관리는 RecommendDao에서 하도록 같이 수정) 
- AlcoholDAO : updateReview에서 update를 해주고 delete를 하는 이유를 모르겠어서, delete를 빼고 preference 테이블에 별점도 같이 수정하는 거 추가
- AlcoholDAO : deleteReview 불필요한 부분 삭제랑 약간의 수정
