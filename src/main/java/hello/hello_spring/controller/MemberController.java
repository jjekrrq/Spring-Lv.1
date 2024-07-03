package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller 라는 어노테이션이 붙어 있으면 스프링 컨테이너에서 어노테이션이 붙은 클래스 객체를 관리함.
// 즉, 스프링 컨테이너에 @Controller가 붙은 클래스를 등록한다!
// 스프링이 생성한 객체를 빈(Bean)이라고 함.
// 스프링이 관리를 하게 된다면, 전부 스프링 컨테이너에 등록을 하고, 스프링 컨테이너로부터 받아서 쓰도록 설계해야 함.
// 스프링 컨테이너에 등록하면, 딱 하나만 등록하고 공유하여 사용할 수 있음.
// 아무 곳에서나 컴포넌트 스캔을 할 수 있는 것은 아니다. HelloSpringApplication 즉, @SpringBootApplication 어노테이션이
// 붙은 파일이 속한 패키지만 컴포넌트 스캔을 기본적으로 스프링이 진행한다.
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // @AutoWired 하면 스프링 컨테이너에 있는 memberService를 연결시켜줌.
    // DI (Dependency Injection) - 의존성 주입 : 의존성 주입에는 3가지 방법이 있다. 그 중 생성자 주입이 가장 선호되는 방식.
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

}
