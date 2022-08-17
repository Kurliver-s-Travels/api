package com.kurly_hack_festa.server.common.advice;

import com.kurly_hack_festa.server.common.response.Response;
import com.kurly_hack_festa.server.item.exception.NotFoundItemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemAdvice {

    /**
     * DB에서 item 상품을 찾을 수 없을 때 발생하는 예외(NotFountItemException.class)에 대한 응답 생성 메소드
     * @return 예외 이유, content
     */
    @ExceptionHandler(NotFoundItemException.class)
    public ResponseEntity<Response> handleUserNotFountException() {

        Response response = Response.builder()
                .message("해당 상품을 찾을 수 없습니다.")
                .content(null)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
