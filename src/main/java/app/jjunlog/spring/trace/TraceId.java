package app.jjunlog.spring.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {
    private String id;  //트랜잭션 ID
    private int level;  //깊이

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // 앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // depth에 따라 id는 똑같고, 레벨은 증가해야하니까
    public TraceId createNextTraceId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousTraceId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
