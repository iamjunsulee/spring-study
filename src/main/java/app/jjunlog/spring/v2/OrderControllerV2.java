package app.jjunlog.spring.v2;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.log.MyLogV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final MyLogV1 logger;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        //TO_DO : ID 동기화(현재는 begin 메소드가 호출될 때마다 생성중), 레벨 표시
        TraceStatus status = null;
        try {
            status = logger.begin("OrderControllerV2.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            logger.end(status);
            return "success";
        } catch (Exception e) {
            logger.exception(status, e);
            throw e;    //다시 예외를 넘겨줘야 함.
        }
    }
}
