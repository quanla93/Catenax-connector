package consulting.sit.catenax.model.shelldescriptors;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = SemanticIdModel.ENTITY_NAME)
public class SemanticIdModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "SemanticId";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;
//
//    @ElementCollection
//    private List<String> value;

}
