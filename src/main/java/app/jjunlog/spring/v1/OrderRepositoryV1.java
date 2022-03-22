package app.jjunlog.spring.v1;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.log.MyLogV0;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final MyLogV0 logger;

    //저장 로직
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logger.begin("OrderRepositoryV1.save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            logger.end(status);
        } catch (Exception e) {
            logger.exception(status, e);
            throw e;    //다시 예외를 넘겨줘야 함.
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
