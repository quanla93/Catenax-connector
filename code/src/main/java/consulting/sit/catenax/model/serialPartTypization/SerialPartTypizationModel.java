package consulting.sit.catenax.model.serialparttypization;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Data
@Entity(name = SerialPartTypizationModel.ENTITY_NAME)
public class SerialPartTypizationModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "SerialPartTypization";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "catenaXId")
    private String catenaXId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serialPartTypization")
    private Set<LocalIdentifiersModel> localIdentifiersModels;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_manufacturingInformation", referencedColumnName = "id")
    private ManufacturingInformationModel manufacturingInformation;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_partTypeInformation", referencedColumnName = "id")
    private PartTypeInformationModel partTypeInformation;

}
