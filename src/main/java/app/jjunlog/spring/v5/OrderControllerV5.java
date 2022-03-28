package app.jjunlog.spring.v5;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.strategy.LogContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v5/request")
    public String request(String itemId) {
        /*
         * strategy pattern 사용
         */
        LogContext<String> context = new LogContext<>(() -> {
            orderService.orderItem(itemId);
            return "success";
        }, logTrace);
        return context.execute("OrderControllerV4.request()");
    }
}
