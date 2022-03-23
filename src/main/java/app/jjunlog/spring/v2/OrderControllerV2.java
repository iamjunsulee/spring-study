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
        //TraceId를 동기화하기 위해 파라미터를 사용했는데,
        //이는 관련 메소드의 모든 파라미터를 다 수정해야하는 문제를 발생시킨다.
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
