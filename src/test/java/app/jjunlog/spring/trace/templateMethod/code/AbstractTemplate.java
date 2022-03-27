package app.jjunlog.spring.trace.templateMethod.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTimeMillis = System.currentTimeMillis();
        call();
        long endTimeMillis = System.currentTimeMillis();
        log.info("resultTime = {}", endTimeMillis - startTimeMillis);
    }

    protected abstract void call();
}
