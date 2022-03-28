package app.jjunlog.spring.v5;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.strategy.LogContext;
import app.jjunlog.spring.trace.strategy.Strategy;
import app.jjunlog.spring.trace.templatemethod.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {
    private final LogTrace logTrace;

    //저장 로직
    public void save(String itemId) {
        LogContext<Void> context = new LogContext<Void>(new Strategy<Void>() {
            @Override
            public Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        }, logTrace);
        context.execute("OrderRepositoryV4.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
