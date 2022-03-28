package app.jjunlog.spring.trace.strategy;

import app.jjunlog.spring.trace.TraceStatus;
import app.jjunlog.spring.trace.logtrace.LogTrace;

public class LogContext {
    private final LogTrace logTrace;

    public LogContext(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T execute(String message, Strategy<T> strategy) {
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
