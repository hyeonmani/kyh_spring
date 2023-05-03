package hello.core.lifeSycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시  호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String msg) {
        System.out.println("call : " + url + ", msg : " + msg);
    }

    public void disconnect() {
        System.out.println("close " + url);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        // 의존관계 주입이 끝나면 호출하겠다~
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        // bean 종료 시에 호출
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    @PostConstruct
    public void init() {
        // 의존관계 주입이 끝나면 호출하겠다~
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        // bean 종료 시에 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
