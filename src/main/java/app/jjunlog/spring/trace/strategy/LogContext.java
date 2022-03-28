package app.jjunlog.spring.trace.strategy;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.logtrace.LogTrace;

public class LogContext<T> {
    private final Strategy<T> strategy;
    private final LogTrace logTrace;

    public LogContext(Strategy<T> strategy, LogTrace logTrace) {
        this.strategy = strategy;
        this.logTrace = logTrace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T result = strategy.call();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
