package consulting.sit.catenax.model.serialparttypization;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Data
@Entity(name = ManufacturingInformationModel.ENTITY_NAME)
public class ManufacturingInformationModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "ManufacturingInformation";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "country")
    private String country;

}
