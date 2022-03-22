package app.jjunlog.spring.trace.log;

import app.jjunlog.spring.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class MyLogV1Test {
    @Test
    void begin_end_test() {
        MyLogV1 log = new MyLogV1();
        TraceStatus status1 = log.begin("hello1");
        TraceStatus status2 = log.beginSync(status1.getTraceId(), "hello2");
        log.end(status2);
        log.end(status1);
    }

    @Test
    void begin_exception_test() {
        MyLogV1 log = new MyLogV1();
        TraceStatus status1 = log.begin("hello1");
        TraceStatus status2 = log.beginSync(status1.getTraceId(), "hello2");
        log.exception(status2, new IllegalStateException());
        log.exception(status1, new IllegalStateException());
    }
}