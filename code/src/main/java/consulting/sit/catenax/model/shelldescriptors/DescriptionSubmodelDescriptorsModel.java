package consulting.sit.catenax.model.shelldescriptors;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity(name = DescriptionSubmodelDescriptorsModel.ENTITY_NAME)
public class DescriptionSubmodelDescriptorsModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "DescriptionSubmodelDescriptors";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column
    private String language;

    @Column
    private String text;

    @ManyToOne
    private SubmodelDescriptorsModel submodelDescriptors;

}
