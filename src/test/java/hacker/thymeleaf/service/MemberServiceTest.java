package hacker.thymeleaf.service;

import hacker.thymeleaf.domain.Member;
import hacker.thymeleaf.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    hacker.thymeleaf.service.MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new hacker.thymeleaf.service.MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        final Member member = new Member();
        member.setName("hello");

        // when
        final Long saveId = memberService.join(member);

        // then 
        final Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        // given
        final Member member1 = new Member();
        member1.setName("spring");

        final Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        final IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        //        try {
        //            memberService.join(member2);
        //            fail("테스트 예외 발생");
        //        } catch (final IllegalStateException e) {
        //            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //        }

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}