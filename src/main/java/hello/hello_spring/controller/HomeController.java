package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 자동 컴포넌트 스캔
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
