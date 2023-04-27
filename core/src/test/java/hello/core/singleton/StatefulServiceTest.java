package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

//        //TreadA : A사용자가 10000원을 주문
//        bean1.order("userA", 10000);
//
//        //TreadA : B사용자가 20000원을 주문
//        bean2.order("userB", 20000);

        //TreadA : A사용자가 10000원을 주문
        int userPrice1 = bean1.order("userA", 10000);

        //TreadA : B사용자가 20000원을 주문
        int userPrice2 = bean2.order("userB", 20000);

        //TreadA : 사용자A 주문 금액 조회
//        int price = bean1.getPrice();
        System.out.println("price = " + userPrice1);

//        Assertions.assertThat(bean1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}