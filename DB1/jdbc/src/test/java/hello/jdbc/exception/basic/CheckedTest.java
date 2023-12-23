package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
public class CheckedTest {

    // Exception을 상속받은 예외는 체크 예외가 된다.
    static class MyCheckedException extends Exception {
        public MyCheckedException(String msg){
            super(msg);
        }
    }

    static class Service {
        Repository repository = new Repository();

        // 예외를 잡아서 처리하는 ㅋㅎ드
        public void callCatch() throws MyCheckedException {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외처리, msg={}", e.getMessage(), e);
            }
        }

    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

}
