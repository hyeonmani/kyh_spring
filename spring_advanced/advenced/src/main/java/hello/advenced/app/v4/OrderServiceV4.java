package hello.advenced.app.v4;

import hello.advenced.trace.TraceStatus;
import hello.advenced.trace.logTrace.LogTrace;
import hello.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 repository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                repository.save(itemId);
                return null;
            }
        };
        template.excute("OrderService.orderItem()");

    }
}
