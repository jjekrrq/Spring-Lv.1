package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// 테스트는 서로 순서가 상관없이, 의존관계에 상관 없이 설계가 되어야 한다.
// 그러기 위해서는 하나의 메소드가 끝날 때마다 메모리를 비워줘야 함.
// TDD : 테스트를 먼저 만들고 그에 맞는 클래스, 메소드를 작성하는 것을 '테스트 주도 개발'이라 함.

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // @AfterEach : 테스트케이스 하나가 끝날때마다 아래 afterEach()메소드가 실행되는 콜백 메서드.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        // 빨간불
//        Assertions.assertEquals(member, null);
        // 파란불
//        Assertions.assertEquals(member, result);
        // member가 result와 같다면
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spirng2");
        memberRepository.save(member2);

        // 초록불
        Member result = memberRepository.findByName("spring1").get();

        // NoSuchElementException : 일치하는 값이 없는 것을 나타내는 예외
        // 빨간불
//        Member result = memberRepository.findByName("spring2").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("spring1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();

        Assertions.assertThat(members.size()).isEqualTo(2);
    }
}
