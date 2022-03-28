package app.jjunlog.spring.v4;

import app.jjunlog.spring.trace.logtrace.LogTrace;
import app.jjunlog.spring.trace.templatemethod.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v4/request")
    public String request(String itemId) {
        /*
         * template method pattern 사용
         * 익명 내부 클래스를 사용해서 자식 클래스는 따로 작성안함.
         * 상속을 이용하다보니 상속의 단점은 그대로 있음 => 부모 클래스를 수정하면 자식 클래스에 다 영향을 미침.
         */
        AbstractTemplate<String> template = new AbstractTemplate<String>(logTrace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "success";
            }
        };
        return template.execute("OrderControllerV4.request()");
    }
}
