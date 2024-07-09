package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @Autowired // @Autowired 하면 스프링 컨테이너에 있는 memberService를 연결시켜줌.
    // DI (Dependency Injection) - 의존성 주입 : 의존성 주입에는 3가지 방법이 있다. 그 중 생성자 주입이 가장 선호되는 방식.
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 회원가입
    // url은 같지만 매핑 방식에 따라 다른 url을 호출한다.
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    // <form>태그로부터 얻은 값을 MemberForm클래스에 저장하고
    // 저장한 값을 Member를 저장한다.
    // 현재, 메모리에 값을 저장하기 때문에, 스프링을 실행했다가 끄면, 값이 전부 사라짐.
    // 따라서, 데이터베이스를 사용해야 함. 값을 갖고있기 위해서는.
    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/getAllMembersList";
    }
}
