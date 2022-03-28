package app.jjunlog.spring.v5;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.strategy.LogContext;
import app.jjunlog.spring.trace.strategy.Strategy;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {
    private final LogContext logContext;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.logContext = new LogContext(logTrace);
    }

    //저장 로직
    public void save(String itemId) {
        logContext.execute("OrderRepositoryV5.save()", new Strategy<Void>() {
            @Override
            public Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
