package hacker.thymeleaf.service;

import hacker.thymeleaf.domain.Member;
import hacker.thymeleaf.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
        //    @Commit
    void 회원가입() {
        // given
        final Member member = new Member();
        member.setName("spring4");

        // when
        final Long saveId = memberService.join(member);

        // then
        final Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}
