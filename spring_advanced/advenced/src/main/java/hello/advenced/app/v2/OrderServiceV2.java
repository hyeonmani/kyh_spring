package hello.advenced.app.v2;

import hello.advenced.trace.TraceId;
import hello.advenced.trace.TraceStatus;
import hello.advenced.trace.hellotrace.HelloTraceV1;
import hello.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 repository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            repository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;    // 예외를 꼭 다시 던져주어야 한다.
        }
    }
}
