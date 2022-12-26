package consulting.sit.catenax.model.serialparttypization;

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
@Entity(name = LocalIdentifiersModel.ENTITY_NAME)
public class LocalIdentifiersModel implements ModelBaseInterface<Integer>  {

    public static final String ENTITY_NAME = "LocalIdentifiers";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "value")
    private String value;

    @Column(name = "key")
    private String key;

    @ManyToOne
    @JoinColumn(name = "serial_part_typization_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SerialPartTypizationModel serialPartTypization;
}
