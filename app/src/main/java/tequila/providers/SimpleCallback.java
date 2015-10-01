package tequila.providers;

/**
 * Created by williamc1986 on 7/13/15.
 */
public interface SimpleCallback<T> {
    void send(T t);
}