package edo.play.e2e;

import edo.play.Application;
import edo.play.model.Dog;
import edo.play.repository.DogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class
              , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class E2ETest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private DogRepository dogRepository;

    private static final ParameterizedTypeReference<List<Dog>> dogList = new ParameterizedTypeReference<List<Dog>>() {};
    private String dogEndpoint = "";
    private HttpEntity<Object> httpEntity = new HttpEntity<>(new HttpHeaders());

    @Before
    public void init() {
        dogRepository.deleteAll();

        dogRepository.save(Dog.builder().name("Hogan").breed("Doberman").age(5).build());
        dogRepository.save(Dog.builder().name("Elsa").breed("Doberman").age(10).build());
        dogRepository.save(Dog.builder().name("Sadie").breed("Dachsund").age(8).build());

        dogEndpoint = "http://localhost:" + port + "/dogs";
    }

    @Test
    public void getAllDogs() {

        ResponseEntity<List<Dog>> response = restTemplate.exchange(dogEndpoint, HttpMethod.GET, httpEntity, dogList);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isEqualTo(3);
    }

    @Test
    public void getDogByName() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(dogEndpoint)
                .queryParam("name","Elsa");
        ResponseEntity<List<Dog>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, dogList);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isEqualTo(1);
        assertThat(response.getBody().get(0).getName()).isEqualTo("Elsa");
    }

}
