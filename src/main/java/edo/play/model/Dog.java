package edo.play.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Dog {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String breed;
    private int age;

    @Tolerate
    Dog() {}
}
