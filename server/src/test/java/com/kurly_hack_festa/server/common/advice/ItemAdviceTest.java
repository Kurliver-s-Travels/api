package com.kurly_hack_festa.server.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurly_hack_festa.server.common.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = ItemAdvice.class)
public class ItemAdviceTest {

    @Autowired
    private ItemAdvice itemAdvice;

    @DisplayName("NotFoundException 예외가 생겼을 때 올바른 응답 값이 응답되어야 한다.")
    @Test
    public void check_response_data_OnNotFoundException_Advice() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        //given
        String message = "해당 상품을 찾을 수 없습니다.";
        Object content = null;

        //when
        ResponseEntity actualResult = itemAdvice.handleUserNotFountException();
        Response actualResponse = objectMapper.convertValue(actualResult.getBody(), Response.class);
        //then
        Assertions.assertNotNull(actualResult);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
        Assertions.assertEquals(message, actualResponse.getMessage());
        Assertions.assertEquals(content, actualResponse.getContent());

    }

}
