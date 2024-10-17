package hacker.thymeleaf.repository;

import hacker.thymeleaf.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL: select m from Member m where m.name = ?
    // m.name은 타입과 변수에 따라 달라짐
    @Override
    Optional<Member> findByName(String name);
}
