package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long seqence = 0L;

    public Member save(Member member) {
        member.setId(++seqence);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member x : all) {
//            if (x.getLoginId().equals(loginId)) {
//                return Optional.of(x);
//            }
//        }
//        return Optional.empty();
        return findAll().stream()
                .filter(x -> x.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStroe() {
        store.clear();
    }

}
