package consulting.sit.catenax.model.shelldescriptors;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = SpecificAssetIdsModel.ENTITY_NAME)
public class SpecificAssetIdsModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "SpecificAssetIds";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column
    private String key;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "shell_descriptors_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ShellDescriptorModel shellDescriptor;
}
