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

### 참고
- controller 패키지 : 에러뜬 건 아직 컨트롤러 수정하기 전이라 에러 뜨는 게 맞음
- filter 패키지 : src/main/webapp 밑에 생성된 폴더 중 URL을 통해 직접 접근이 필요한 폴더는 반드시 ResourceFilter 클래스의 22~25행과 같이 추가해야 함 (그냥 냅두면 됨)
- model 패키지 : DTO 클래스?
- model.dao 패키지 : ConnectionManager, JDBCUtil 클래스 + 우리가 정의한 dao 클래스들

##### model.dao에 Test 클래스로 dao 작성 잘 됐는지 실행 (Test.java -> Run As -> Java Application으로 실행)
##### 다른 패키지에 있는 클래스 사용 시 (model.dao 패키지에서 model 패키지에 있는 클래스 호출할 때) import 필수
  (ex. RecommendDao에서 Alcohol 객체 생성하려면 import model.Alcohol 해줘야 함. 
  그냥 에러뜨면 마우스 갖다 대서 솔루션 뜨는 거 중에 import '어쩌구' (model) 이런 식으로 적혀 있는 거 선택)
