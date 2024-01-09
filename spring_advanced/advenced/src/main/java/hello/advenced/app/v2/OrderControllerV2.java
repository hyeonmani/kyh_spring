package hello.advenced.app.v2;

import hello.advenced.trace.TraceStatus;
import hello.advenced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //  @Controller + @ResponseBody
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 service;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            service.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok!!";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;    // 예외를 꼭 다시 던져주어야 한다.
        }

    }

}
