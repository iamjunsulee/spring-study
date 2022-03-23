package app.jjunlog.spring.trace.logtrace;

import app.jjunlog.spring.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
