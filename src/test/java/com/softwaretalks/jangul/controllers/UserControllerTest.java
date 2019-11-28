package com.softwaretalks.jangul.controllers;

import com.softwaretalks.jangul.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String username = "email@gmail.com";
    private final String password = "password00000";


    @Test
    public void postUser_shouldReturn400ForNullPassword() {

        final String token = generateToken();

        User user = new User("test@test.com",null);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> endpointResponseEntity = restTemplate.exchange("/users", HttpMethod.POST, requestEntity, User.class);

        Assertions.assertEquals(400, endpointResponseEntity.getStatusCodeValue());

    }

    @Test
    public void postUser_shouldReturn400ForPasswordLengthLessThan5() {

        final String token = generateToken();

        User user = new User("test@test.com","132");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> endpointResponseEntity = restTemplate.exchange("/users", HttpMethod.POST, requestEntity, User.class);

        Assertions.assertEquals(400, endpointResponseEntity.getStatusCodeValue());

    }

    @Test
    public void postUser_shouldReturn400ForNullEmail() {

        final String token = generateToken();

        User user = new User(null,"password");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> endpointResponseEntity = restTemplate.exchange("/users", HttpMethod.POST, requestEntity, User.class);

        Assertions.assertEquals(400, endpointResponseEntity.getStatusCodeValue());

    }

    @Test
    public void postUser_shouldReturn400ForInvalidEmailAddress() {

        final String token = generateToken();

        User user = new User("invalid_email_address","password");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> endpointResponseEntity = restTemplate.exchange("/users", HttpMethod.POST, requestEntity, User.class);

        Assertions.assertEquals(400, endpointResponseEntity.getStatusCodeValue());

    }

    @Test
    public void postUser_shouldReturn200ForValidUser() {

        final String token = generateToken();

        User user = new User("test@test.test","password");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> endpointResponseEntity = restTemplate.exchange("/users", HttpMethod.POST, requestEntity, User.class);

        Assertions.assertEquals(200, endpointResponseEntity.getStatusCodeValue());

    }


    private String generateToken() {
        restTemplate.postForObject("/users", Map.of("email", username, "password", password), Object.class);
        final Map<String, String> tokenMap = restTemplate.postForObject("/tokens", Map.of("username", username, "password", password), Map.class);
        return tokenMap.get("token");
    }

}
