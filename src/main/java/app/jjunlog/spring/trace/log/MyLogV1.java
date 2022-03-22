package app.jjunlog.spring.trace.log;

import app.jjunlog.spring.trace.TraceId;
import app.jjunlog.spring.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyLogV1 {

    private static final String START_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<X-";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    // ID는 유지하면서 level은 증가시킨다.
    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId nextTraceId = beforeTraceId.createNextTraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", nextTraceId.getId(), addSpace(START_PREFIX, nextTraceId.getLevel()), message);
        return new TraceStatus(nextTraceId, startTimeMs, message);
    }

    // level 0
    // level 1 |-->
    // level 2 |    |-->
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < level;i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        Long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(END_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }
}
