package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 해당 메서드를 통해서만 객체를 조회 가능하도록 한다.
    public static SingletonService getInstance() {
         return instance;
    }

    // 다른 클래스에서 new를 이용한 새로운 객체 생성을 하지 못한다.
    private SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
