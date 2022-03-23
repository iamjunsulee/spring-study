package app.jjunlog.spring;

import app.jjunlog.spring.trace.logtrace.FieldLogTrace;
import app.jjunlog.spring.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
