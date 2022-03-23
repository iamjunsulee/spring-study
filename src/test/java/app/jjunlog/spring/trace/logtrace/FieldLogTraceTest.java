package app.jjunlog.spring.trace.logtrace;

import app.jjunlog.spring.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {
    FieldLogTrace fieldLogTrace = new FieldLogTrace();

    @Test
    public void begin_end_test() {
        TraceStatus status1 = fieldLogTrace.begin("안녕");
        TraceStatus status2 = fieldLogTrace.begin("만나서 반가워");
        fieldLogTrace.end(status2);
        fieldLogTrace.end(status1);
    }
}