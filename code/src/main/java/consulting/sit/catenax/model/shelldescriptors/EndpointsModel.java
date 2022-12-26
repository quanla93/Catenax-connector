package consulting.sit.catenax.model.shelldescriptors;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity(name = EndpointsModel.ENTITY_NAME)
public class EndpointsModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "Endpoints";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column
    private String interfaces;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_protocolInformation", referencedColumnName = "id")
    private ProtocolInformationModel protocolInformation;

    @ManyToOne
    @JoinColumn(name = "submodel_descriptors_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SubmodelDescriptorsModel submodelDescriptors;
}
