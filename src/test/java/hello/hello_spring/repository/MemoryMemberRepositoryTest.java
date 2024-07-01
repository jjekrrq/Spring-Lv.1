package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
    MemberRepository memberRepository = new MemoryMemberRepository();

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
}
