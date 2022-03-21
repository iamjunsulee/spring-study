package app.jjunlog.spring.trace.log;

import app.jjunlog.spring.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLogV0Test {
    @Test
    void begin_end_test() {
        MyLogV0 log = new MyLogV0();
        TraceStatus status = log.begin("hello");
        log.end(status);
    }

    @Test
    void begin_exception_test() {
        MyLogV0 log = new MyLogV0();
        TraceStatus status = log.begin("hello");
        log.exception(status, new IllegalStateException());
    }
}