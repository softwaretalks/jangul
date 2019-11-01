package com.softwaretalks.jangul.services;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;
import com.softwaretalks.jangul.models.User;
import com.softwaretalks.jangul.repositories.EndpointRepository;
import com.softwaretalks.jangul.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EndpointServiceTest {

    @Autowired
    private EndpointService endpointService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EndpointRepository endpointRepository;

    private final String email = "test@gmail.com";
    private final String password = "testPassword";

    private final String softwaretalkAddress = "www.softwaretalk.ir";
    private final String googleAddress = "www.google.com";
    private final String bingAddress = "www.bing.com";

    @BeforeEach
    public void setup() {

        User marsUser = new User("mars@gmail.com", "marsPassword");
        User eliUser = new User("eli@gmail.com", "eliPassword");

        Endpoint bingEndpoint = new Endpoint(bingAddress, EndpointProtocol.HTTP, marsUser.getId());
        Endpoint googleEndpoint = new Endpoint(googleAddress, EndpointProtocol.HTTP, marsUser.getId());

        Endpoint softwareTalkEndpoint = new Endpoint(softwaretalkAddress, EndpointProtocol.HTTP, eliUser.getId());

        userRepository.save(eliUser);
        userRepository.save(marsUser);
        endpointRepository.save(bingEndpoint);
        endpointRepository.save(googleEndpoint);
        endpointRepository.save(softwareTalkEndpoint);

    }

    @AfterEach
    @Transactional()
    public void tearDown(){

    }


    @Test
    @WithMockUser(username = "eli@gmail.com")
    public void getEndpoints_whenEliUserThenReturnOneEndpoint() {

        List<Endpoint> endpointList = endpointService.getEndpoints();
        assertThat(endpointList.size()).isEqualTo(1);
        assertThat(endpointList.get(0).getAddress()).isEqualTo(softwaretalkAddress);
    }

    @Test
    @WithMockUser(username = "mars@gmail.com")
    public void getEndpoints_whenMarsUserThenReturnOneEndpoint() {

        List<Endpoint> endpointList = endpointService.getEndpoints();
        assertThat(endpointList.size()).isEqualTo(2);
        assertThat(endpointList.get(0).getAddress()).isEqualTo(bingAddress);
        assertThat(endpointList.get(1).getAddress()).isEqualTo(googleAddress);
    }

    @Test
    @WithMockUser(username = "testi@gmail.com")
    public void getEndpoints_whenInvalidUserThenThrowNotFoundUserException() {

        //// TODO: 11/1/2019 change to check Exception
        List<Endpoint> endpointList = endpointService.getEndpoints();
        assertThat(endpointList.size()).isEqualTo(0);
    }
}
