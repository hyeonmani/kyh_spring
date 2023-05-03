package hello.core.lifeSycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(lifeCycleTest.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class lifeCycleTest{

//        @Bean
//        public NetworkClient networkClient() {
//            NetworkClient networkClient = new NetworkClient();
//            networkClient.setUrl("http://naverr.com");
//
//            return networkClient;
//        }
        @Bean//(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://naverr.com");

            return networkClient;
        }
    }
}
