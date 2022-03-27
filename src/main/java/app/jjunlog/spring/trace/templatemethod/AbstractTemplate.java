package app.jjunlog.spring.trace.templatemethod;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate<T> {
    private final LogTrace logTrace;

    protected AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public T execute(String message) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin(message);
            T result = call();
            logTrace.end(traceStatus);
            return result;
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }

    protected abstract T call();
}
