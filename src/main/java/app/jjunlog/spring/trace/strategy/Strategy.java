package app.jjunlog.spring.trace.strategy;

public interface Strategy<T> {
    T call();
}
