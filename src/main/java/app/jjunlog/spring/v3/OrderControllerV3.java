package app.jjunlog.spring.v3;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v3/request")
    public String request(String itemId) {
        //필드 동기화 방식 사용
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderControllerV3.request()");
            orderService.orderItem(itemId);
            logTrace.end(status);
            return "success";
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;    //다시 예외를 넘겨줘야 함.
        }
    }
}
