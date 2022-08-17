package com.kurly_hack_festa.server.common.response;

import lombok.*;


/**
 * <h1>Response</h1>
 * <p>HTTP 응답 객체</p>
 * @param <T> 본문에 들어갈 객체 타입 (json 으로 변환될 대상)
 */
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /**
     * 서버의 메시지
     */
    private String message;

    /**
     * 서버에서 제공할 데이터가 담길 필드
     */
    private T content;

}
