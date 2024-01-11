package hello.advenced.app.v5;

import hello.advenced.trace.callback.TraceCallback;
import hello.advenced.trace.callback.TraceTemplate;
import hello.advenced.trace.logTrace.LogTrace;
import hello.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 repository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 repository, LogTrace trace) {
        this.repository = repository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {

        template.excute("OrderService.orderItem()", () -> {
            repository.save(itemId);
            return null;
        });

    }
}
