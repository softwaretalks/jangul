package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HttpHealthCheckerTest {
    @Autowired
    private HttpHealthChecker healthChecker;
    private final String username = "email@gmail.com";
    private final String password = "password";
    private final User userTest = new User(username,password);
    @Test
    public void check_shouldReturnDownOn404Responses() throws UnsuccessfulCheckException {
        final var healthcheck = healthChecker.healthcheck(Endpoint.httpFrom("https://httpstat.us/404",userTest));
        assertThat(healthcheck.isUp()).isFalse();
    }

    @Test
    public void check_shouldReturnDownOn502Responses() throws UnsuccessfulCheckException {
        final var healthcheck = healthChecker.healthcheck(Endpoint.httpFrom("https://httpstat.us/502",userTest));
        assertThat(healthcheck.isUp()).isFalse();
    }

    @Test
    public void check_shouldReturnUpOnResponse200() throws UnsuccessfulCheckException {
        final var healthcheck = healthChecker.healthcheck(Endpoint.httpFrom("https://httpstat.us/200",userTest));
        assertThat(healthcheck.isUp()).isTrue();
    }

    @Test
    public void check_shouldThrowExceptionOnInvalidEndpoints() {
        assertThrows(UnsuccessfulCheckException.class, () ->
                healthChecker.healthcheck(Endpoint.httpFrom("https://this-site-cant-exist.blabla",userTest)));
    }
}
