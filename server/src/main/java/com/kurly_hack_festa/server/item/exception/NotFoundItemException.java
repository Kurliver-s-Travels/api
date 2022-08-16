package com.kurly_hack_festa.server.item.exception;

/**
 * <h1>NotFoundItemException</h1>
 * <p>
 *     Item Entity 를 찾지 못하였을 떄, 발생하는 예외
 * </p>
 *
 * @author younghoCha
 */
public class NotFoundItemException extends RuntimeException{

    public NotFoundItemException(String msg){
        super(msg);
    }
}
