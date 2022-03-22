package app.jjunlog.spring.v2;

import app.jjunlog.spring.trace.TraceId;
import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.log.MyLogV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final MyLogV1 logger;

    //저장 로직
    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = logger.beginSync(traceId, "OrderRepositoryV2.save()");
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
