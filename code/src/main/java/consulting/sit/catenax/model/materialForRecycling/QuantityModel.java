package consulting.sit.catenax.model.materialforrecycling;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = QuantityModel.ENTITY_NAME)
public class QuantityModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "Quantity";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "value")
    private Double value;

    @Column(name = "unit")
    private String unit;

}
