package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 8080포트가 한군데라도 열려있으면 오류 발생. cmd에서 빌드했기 때문에 IDE에서 빌드하려 하면 오류 발생함.

@Controller
public class HelloController {

    // localhost:8080/hello 경로
    @GetMapping("hello")
    public String hello(Model model){
        // MVC의 M = Model
        model.addAttribute("data", "hello!!");
        // return에 문자열로 값을 주게 되면, templates에서 먼저 찾아서 html이든 무엇이든 있다면 해당 웹을 띄움.
        // 따라서 templates에 hello.html이라는 웹이 존재하기에 해당 웹을 띄운다.
        return "hello";
    }


    // @RequestParam 어노테이션을 통해 name이라는 키를 가진 밸류를 클라이언트에게 입력받고
    // 그 입력 받은 값을 모델에 저장하여 viewResolver가 템블릿 변환 후 반환.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    // @ResponseBody : Http 통신 프로토콜의 응답 바디 부분에 아래 데이터를 직접 넣어주겠다는 뜻
    @ResponseBody
    public String helloString(@RequestParam(name = "name") String name){
        return "hello " + name; // hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ // @RequestParam으로 name을 클라로부터 받아서
        // @ResponseBody에 담아 반환해준다는 뜻인 것 같다.
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        // 게터, 세터 자바 빈 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
