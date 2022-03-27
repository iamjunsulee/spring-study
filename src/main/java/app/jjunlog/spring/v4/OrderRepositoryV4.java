package app.jjunlog.spring.v4;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.templatemethod.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace logTrace;

    //저장 로직
    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(logTrace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepositoryV4.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
