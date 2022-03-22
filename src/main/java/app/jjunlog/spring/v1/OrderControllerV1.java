package app.jjunlog.spring.v1;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.log.MyLogV0;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final MyLogV0 logger;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        //TO_DO : ID 동기화(현재는 begin 메소드가 호출될 때마다 생성중), 레벨 표시
        TraceStatus status = null;
        try {
            status = logger.begin("OrderControllerV1.request()");
            orderService.orderItem(itemId);
            logger.end(status);
            return "success";
        } catch (Exception e) {
            logger.exception(status, e);
            throw e;    //다시 예외를 넘겨줘야 함.
        }
    }
}
