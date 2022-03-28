package app.jjunlog.spring.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogStrategy1 implements Strategy{
    @Override
    public void call() {
        log.info("비지니스 로직1 실행");
    }
}
