package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 테스트 자동 생성 단축기 : alt + enter
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // MemberService를 스프링이 생성을 할때, @Autowired를 통해 스프링 컨테이너에 저장된 memberRepository를 스프링이 연결시켜줌.
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member){

        checkIfMemberIsPresent(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

    // 같은 이름이 있는 중복 회원 x
//        if(memberRepository.findByName(member.getName()).isPresent()){
//            throw new IllegalArgumentException("다른 이름을 입력하시오.");
//        }
    // 과거에는 ifNull과 같은 방법으로 null을 확인했지만
    // 최근에는 Optional과 같은 방법으로 프로그래밍함.
    // 인텔리제이 메소드 추출 기능 : ctrl + alt + m
    private void checkIfMemberIsPresent(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(member1 -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }
}
