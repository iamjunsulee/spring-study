package app.jjunlog.spring.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTracer {
    private final Strategy strategy;

    public LogTracer(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        strategy.call();
        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);
    }
}
