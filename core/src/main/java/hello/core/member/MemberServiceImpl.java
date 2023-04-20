package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 인터페이스만 의존한다. 생성자를 통해 구현된 객체를 주입받을 것이다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
