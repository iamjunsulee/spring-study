package app.jjunlog.spring.trace.templateMethod.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubLogic1 extends AbstractTemplate{

    @Override
    protected void call() {
        log.info("비지니스 로직 1 실행");
    }
}
