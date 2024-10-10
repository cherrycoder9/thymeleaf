package hacker.thymeleaf.repository;

import hacker.thymeleaf.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // JUnit 5에서 사용되는 애너테이션
    // 각 테스트 메서드가 수행된 후에 실행될 코드를 정의
    // 주로 테스트 상태를 초기화하거나 리소스를 정리하는 역할을 위해 씀
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        final Member member = new Member();
        member.setName("spring");

        repository.save(member);

        final Member result = repository.findById(member.getId()).get();
        // System.out.println("(result == member) = " + (result == member));
        // Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        final Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        final Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        final Member result = repository.findByName("spring1").get();
        // Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        final Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        final Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        final List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
        // assertThat(result.size()).isEqualTo(3);
    }

}

