package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LogRepository logRepository;


    /**
     * memberService    -   Transavtional: OFF
     * memberRepository    -   Transavtional: ON
     * logRepository    -   Transavtional: ON
     */
    @Test
    void outerTxOff_success() {
        String username = "outerTxOff_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }


    /**
     * memberService    -   Transavtional: OFF
     * memberRepository    -   Transavtional: ON
     * logRepository    -   Transavtional: ON Exception
     */
    @Test
    void outerTxOff_exception() {
        String username = "로그예외_outerTxOff_exception";

        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }


    /**
     * memberService    -   Transavtional: ON
     * memberRepository
     * logRepository
     */
    @Test
    void singleTransaction() {
        String username = "singleTransaction";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService    -   Transavtional: ON
     * memberRepository    -   Transavtional: ON
     * logRepository    -   Transavtional: ON
     */
    @Test
    void outerTxOn_success() {
        String username = "outerTxOn_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }


    /**
     * memberService    -   Transavtional: ON
     * memberRepository    -   Transavtional: ON
     * logRepository    -   Transavtional: ON Exception
     */
    @Test
    void outerTxOn_exception() {
        String username = "로그예외_outerTxOn_exception";

        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }


    /**
     * memberService    -   Transavtional: ON
     * memberRepository    -   Transavtional: ON
     * logRepository    -   Transavtional: ON Exception
     */
    @Test
    void recoverException_fail() {
        String username = "로그예외_recoverException_fail";

        assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }


    /**
     * memberService    -   Transavtional: ON
     * memberRepository    -   Transavtional: ON
     * logRepository    -   Transavtional: ON(REQUIRES_NEW) Exception
     */
    @Test
    void recoverException_success() {
        String username = "로그예외_recoverException_success";

        memberService.joinV2(username);

        // member save, log rollback
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

}