package app.jjunlog.spring.v5;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.strategy.LogContext;
import app.jjunlog.spring.trace.strategy.Strategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogContext logContext;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.logContext = new LogContext(logTrace);
    }
    
    @GetMapping("/v5/request")
    public String request(String itemId) {
        /*
         * strategy pattern - 템플릿 콜백 패턴 사용
         */
        return logContext.execute("OrderControllerV5.request()", new Strategy<String>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "success";
            }
        });
    }
}
