package app.jjunlog.spring.trace.templateMethod;

import app.jjunlog.spring.trace.templateMethod.code.AbstractTemplate;
import app.jjunlog.spring.trace.templateMethod.code.SubLogic1;
import app.jjunlog.spring.trace.templateMethod.code.SubLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class AbstractTemplateTest {

    @Test
    public void templateMethod_test() {
        AbstractTemplate template1 = new SubLogic1();
        AbstractTemplate template2 = new SubLogic2();
        template1.execute();
        template2.execute();
    }

    @Test
    public void templateMethod_test2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직 1 실행");
            }
        };
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직 2 실행");
            }
        };
        template2.execute();
    }
}