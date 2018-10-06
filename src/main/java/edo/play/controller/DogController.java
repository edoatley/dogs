package edo.play.controller;

import edo.play.model.Dog;
import edo.play.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogRepository dogRepository;

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
}
