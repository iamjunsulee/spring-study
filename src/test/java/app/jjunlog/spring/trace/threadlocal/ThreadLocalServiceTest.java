package app.jjunlog.spring.trace.threadlocal;

import app.jjunlog.spring.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    public void threadLocalServiceTest() {
        log.info("main start");

        Runnable runnableA = () -> threadLocalService.logic("userA");
        Runnable runnableB = () -> threadLocalService.logic("userB");

        Thread threadA = new Thread(runnableA);
        threadA.setName("Thread-A");
        Thread threadB = new Thread(runnableB);
        threadB.setName("Thread-B");

        threadA.start();
        //sleep(1000);    //1초 정도 시간을 주면 동시성 문제 발생안함
        sleep(100);     //ThreadLocal을 사용해서 해당 쓰레드에 저장된 값을 정상적으로 불러옴
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
