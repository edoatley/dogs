package edo.play;

import edo.play.model.Dog;
import edo.play.repository.DogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(DogRepository dogRepository) {
        return (args) -> {
            dogRepository.save(Dog.builder().name("Hogan").breed("Doberman").age(5).build());
            dogRepository.save(Dog.builder().name("Elsa").breed("Doberman").age(10).build());
            dogRepository.save(Dog.builder().name("Sadie").breed("Dachsund").age(8).build());
            dogRepository.save(Dog.builder().name("Oliver").breed("Poodle").age(2).build());
            dogRepository.save(Dog.builder().name("Harry").breed("Labrador").age(7).build());
            dogRepository.save(Dog.builder().name("Joshua").breed("Great Dane").age(1).build());
            dogRepository.save(Dog.builder().name("Max").breed("Afghan Hound").age(1).build());
            dogRepository.save(Dog.builder().name("Tom").breed("Pit Bull").age(2).build());
            dogRepository.save(Dog.builder().name("Lucas").breed("Bulldog").age(8).build());
            dogRepository.save(Dog.builder().name("Arthur").breed("German Shepherd").age(4).build());
            dogRepository.save(Dog.builder().name("Isaac").breed("Japanese Akita").age(9).build());
        };
    }

}

