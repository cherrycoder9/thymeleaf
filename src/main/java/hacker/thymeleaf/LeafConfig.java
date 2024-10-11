package hacker.thymeleaf;

import hacker.thymeleaf.repository.MemberRepository;
import hacker.thymeleaf.repository.MemoryMemberRepository;
import hacker.thymeleaf.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 빈 등록
@Configuration
public class LeafConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
