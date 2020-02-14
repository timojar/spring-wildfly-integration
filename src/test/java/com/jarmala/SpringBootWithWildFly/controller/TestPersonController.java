package com.jarmala.SpringBootWithWildFly.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.InetAddress;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class TestPersonController {

    @Test
    public void testHello() throws Exception{
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
        ResponseEntity<String> response = testRestTemplate.
                getForEntity("http://"+inetAddress.getHostAddress()+System.getenv("PUB_PORT")+"/hello" , String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
