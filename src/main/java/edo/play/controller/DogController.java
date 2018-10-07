package edo.play.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edo.play.model.Dog;
import edo.play.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    public @ResponseBody List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogRepository.findAll().forEach(dogs::add);
        return dogs;
    }

    @RequestMapping(value = "/dogs", params = "name", method = RequestMethod.GET)
    public @ResponseBody List<Dog> getAllDogs(@RequestParam("name") String name) {
        List<Dog> dogs = new ArrayList<>();
        dogRepository.findByName(name).forEach(dogs::add);
        return dogs;
    }

    @RequestMapping(value = "/dogs", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Dog getAllDogs(@RequestBody  Dog newDog) {
        Dog savedDog = dogRepository.save(newDog);
        return savedDog;
    }

    @RequestMapping(value="/dogs", method = RequestMethod.POST, consumes = "multipart/mixed")
    public List<String> bulkRequests(ArrayList<String> requests) throws IOException {
        List<String> response = new ArrayList<>();
        for (String request : requests) {
            Dog newDog = objectMapper.readValue(request, Dog.class);
            Dog savedDog = dogRepository.save(newDog);
            response.add(objectMapper.writeValueAsString(savedDog));
        }
        return response;
    }

}
