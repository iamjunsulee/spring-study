package app.jjunlog.spring.trace.threadlocal;

import app.jjunlog.spring.trace.TraceId;
import app.jjunlog.spring.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    public void fieldThreadTest() {
        log.info("main start");

        Runnable runnableA = () -> fieldService.logic("userA");
        Runnable runnableB = () -> fieldService.logic("userB");

        Thread threadA = new Thread(runnableA);
        threadA.setName("Thread-A");
        Thread threadB = new Thread(runnableB);
        threadB.setName("Thread-B");

        threadA.start();
        //sleep(1000);    //1초 정도 시간을 주면 동시성 문제 발생안함
        sleep(100);     //A가 끝나기 전에 B가 시작함 -> 동시성 문제 발생함.
        threadB.start();

        sleep(2000);
        log.info("main end");
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
