package app.jjunlog.spring.trace.strategy;

import app.jjunlog.spring.trace.strategy.code.LogStrategy1;
import app.jjunlog.spring.trace.strategy.code.LogTracer;
import app.jjunlog.spring.trace.strategy.code.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StrategyTest {

    @Test
    public void strategyTest1() {
        LogStrategy1 strategy1 = new LogStrategy1();
        LogTracer context = new LogTracer(strategy1);
        context.execute();

        LogStrategy1 strategy2 = new LogStrategy1();
        LogTracer context2 = new LogTracer(strategy2);
        context2.execute();
    }

    @Test
    public void strategyTest2() {
        LogTracer context = new LogTracer(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context.execute();

        LogTracer context2 = new LogTracer(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
        context2.execute();
    }

    @Test
    public void strategyTest3() {
        LogTracer context = new LogTracer(() -> log.info("비지니스 로직1 실행"));
        context.execute();

        LogTracer context2 = new LogTracer(() -> log.info("비지니스 로직2 실행"));
        context2.execute();
    }
}
