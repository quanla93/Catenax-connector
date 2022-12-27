package consulting.sit.catenax.model.serialparttypization;

import consulting.sit.catenax.model.ModelBaseInterface;
import consulting.sit.catenax.model.serialparttypization.enums.EClassification;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = PartTypeInformationModel.ENTITY_NAME)
public class PartTypeInformationModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "PartTypeInformation";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "manufacturerPartId")
    private String manufacturerPartId;

    @Column(name = "customerPartId")
    private String customerPartId;

    @Column(name = "nameAtManufacturer")
    private String nameAtManufacturer;

    @Column(name = "nameAtCustomer")
    private String nameAtCustomer;

    @Enumerated(EnumType.STRING)
    private EClassification classification;

}
