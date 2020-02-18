package com.jarmala.SpringBootWithWildFly.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOError;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPersonController {

    @Value("${host_ip_url}")
    private String host;

    @Value("${host_port}")
    private String port;


    @Order(1)
    @Test
    public void testHello() throws Exception {
        int attempts = -1;
        boolean ok = false;
        System.out.println(host + " host ip");
        ResponseEntity<String> response = new ResponseEntity<>(
                "Custom header set", HttpStatus.NOT_FOUND);
        while (!ok) {
            try {
                response = ping();
                ok = response.getStatusCode().equals(HttpStatus.OK);
            } catch (Exception e) {
            }
            if (attempts > 0) {
                TimeUnit.SECONDS.sleep(30);
                System.out.println(attempts + ". attempt" + response.getStatusCode());
            }
            attempts++;
            if (attempts > 7) {
                break;
            }
        }
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }


    @Order(2)
    @Test
    public void testGetAllPersons() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(host + ":" + port + "/all", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }


    private ResponseEntity<String> ping() throws IOError, IOError {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(host + ":" + port + "/hello", String.class);


        return response;

    }

}
