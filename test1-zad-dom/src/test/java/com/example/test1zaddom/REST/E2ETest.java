package com.example.test1zaddom.REST;

import com.example.test1zaddom.Unit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class E2ETest {
    
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;
    
    @Test
    void shouldBeAbleToReceiveProperDataFromDBAtTheControllerCall(){
        //when
        ResponseEntity<Unit[]> actual = testRestTemplate.exchange("http://localhost:" + port + "/10500", HttpMethod.GET, HttpEntity.EMPTY, Unit[].class);

        //then
        assertThat(Objects.requireNonNull(actual.getBody()).length,is(3));

    }
}
