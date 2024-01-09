package hello.advenced.app.v1;

import hello.advenced.trace.TraceStatus;
import hello.advenced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //  @Controller + @ResponseBody
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 service;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            service.orderItem(itemId);
            trace.end(status);
            return "ok!!";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;    // 예외를 꼭 다시 던져주어야 한다.
        }

    }

}
