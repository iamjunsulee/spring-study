package app.jjunlog.spring.v5;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.strategy.LogContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final LogTrace logTrace;

    public void orderItem(String itemId) {
        LogContext<Void> context = new LogContext<Void>(() -> {
            orderRepository.save(itemId);
            return null;
        }, logTrace);
        context.execute("OrderServiceV4.orderItem()");
    }
}
