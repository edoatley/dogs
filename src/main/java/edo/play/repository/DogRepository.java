package edo.play.repository;

import edo.play.model.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long>{

    List<Dog> findByName(String name);
}