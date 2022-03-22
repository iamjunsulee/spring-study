package app.jjunlog.spring.v2;

import app.jjunlog.spring.trace.TraceId;
import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.log.MyLogV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final MyLogV1 logger;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = logger.beginSync(traceId, "OrderServiceV2.orderItem()");
            // ID는 유지한채, level만 증가시킨 새로운 TraceId를 넘겨준다.
            orderRepository.save(status.getTraceId(), itemId);
            logger.end(status);
        } catch (Exception e) {
            logger.exception(status, e);
            throw e;
        }
    }
}
