package hello.proxy.pureproxy.concreteproxy.code;

public class ConcreteClient {
    private ConcreteLogic concreteLogicl;

    public ConcreteClient(ConcreteLogic concreteLogicl) {
        this.concreteLogicl = concreteLogicl;
    }

    public void excute() {
        concreteLogicl.operation();
    }
}
