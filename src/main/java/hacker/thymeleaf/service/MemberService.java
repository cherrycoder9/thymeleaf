package hacker.thymeleaf.service;

import hacker.thymeleaf.domain.Member;
import hacker.thymeleaf.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(final Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(final Member member) {
        memberRepository.findByName(member.getName())
                // ifPresent: Java 8부터 도입된 Optional 클래스의 메서드
                // Optional 값이 존재할 때만 특정 동작을 수행하도록 하는데 사용함
                // 주로 null 체크를 간단하게 할 수 있도록 도와줌
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(final Long memberId) {
        return memberRepository.findById(memberId);
    }

}
