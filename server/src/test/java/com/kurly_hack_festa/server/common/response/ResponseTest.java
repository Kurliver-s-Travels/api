package com.kurly_hack_festa.server.common.response;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponseTest {

    @DisplayName("Response 객체가 정상적으로 생성되어야 한다.")
    @Test
    public void check_response_Object() throws Exception{

        //given & when
        Response sample1 = Response.builder().message("테스트 메세지1").content(1).build();
        Response sample2 = Response.builder().message("테스트 메세지2").content("1").build();
        Response sample3 = Response.builder().message("테스트 메세지3").content(1L).build();
        Response sample4 = Response.builder().message("테스트 메세지4").content(new String("!")).build();

        //then
        Assertions.assertEquals("테스트 메세지1", sample1.getMessage());
        Assertions.assertEquals("테스트 메세지2", sample2.getMessage());
        Assertions.assertEquals("테스트 메세지3", sample3.getMessage());
        Assertions.assertEquals("테스트 메세지4", sample4.getMessage());
        Assertions.assertEquals(1, sample1.getContent());
        Assertions.assertEquals("1", sample2.getContent());
        Assertions.assertEquals(1L, sample3.getContent());
        Assertions.assertEquals(new String("!"), sample4.getContent());
    }

}
