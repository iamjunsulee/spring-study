package app.jjunlog.spring.v5;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.strategy.LogContext;
import app.jjunlog.spring.trace.strategy.Strategy;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final LogContext logContext;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace) {
        this.orderRepository = orderRepository;
        this.logContext = new LogContext(logTrace);
    }

    public void orderItem(String itemId) {
        logContext.execute("OrderServiceV5.orderItem()", new Strategy<Void>() {
            @Override
            public Void call() {
                orderRepository.save(itemId);
                return null;
            }
        });
    }
}
