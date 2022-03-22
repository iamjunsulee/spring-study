package app.jjunlog.spring.v1;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.log.MyLogV0;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final MyLogV0 logger;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logger.begin("OrderServiceV1.orderItem()");
            orderRepository.save(itemId);
            logger.end(status);
        } catch (Exception e) {
            logger.exception(status, e);
            throw e;
        }
    }
}
