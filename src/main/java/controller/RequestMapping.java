package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.diary.CreateDiaryController;
import controller.diary.ListDiaryController;
import controller.diary.UpdateDiaryController;
import controller.diary.ViewDiaryController;
import controller.recommend.RecommendController;
import controller.recommend.TestController;
import controller.user.ListUserController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.RegisterUserController;
import controller.user.UpdateUserController;
import controller.user.ViewUserController;



public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/home/home", new HomeController());
        
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());

        // 회원가입
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update", new UpdateUserController());

        
       // 추천 관련
       mappings.put("/recommend/list", new RecommendController());
       mappings.put("/recommend/test", new TestController());
        
       // 시뮬레이터
       mappings.put("/simulate", new SimulateController());
       mappings.put("simulate/result", new SimulateController());
       
       
       // 음주 기록
       mappings.put("/diary/list", new ListDiaryController());
       mappings.put("/diary/view", new ViewDiaryController());
       mappings.put("/diary/register/form", new ForwardController("/diary/registerForm.jsp"));
       mappings.put("/diary/create", new CreateDiaryController());
       mappings.put("/diary/update", new UpdateDiaryController());
       
       logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}