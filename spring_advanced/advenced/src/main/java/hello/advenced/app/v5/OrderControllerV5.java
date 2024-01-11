package hello.advenced.app.v5;

import hello.advenced.trace.callback.TraceCallback;
import hello.advenced.trace.callback.TraceTemplate;
import hello.advenced.trace.logTrace.LogTrace;
import hello.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //  @Controller + @ResponseBody
public class OrderControllerV5 {

    private final OrderServiceV5 service;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 service, LogTrace logTrace) {
        this.service = service;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.excute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                service.orderItem(itemId);
                return "ok!!";
            }
        });
    }

}
