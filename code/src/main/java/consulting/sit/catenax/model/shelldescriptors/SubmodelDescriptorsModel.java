package consulting.sit.catenax.model.shelldescriptors;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = SubmodelDescriptorsModel.ENTITY_NAME)
public class SubmodelDescriptorsModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "SubmodelDescriptors";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column
    private String idShort;

    @Column
    private String identification;

    @ElementCollection
    private List<String> semanticId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "submodelDescriptors")
    private Set<DescriptionSubmodelDescriptorsModel> descriptors;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "submodelDescriptors")
    private Set<EndpointsModel> endpoints;

    @ManyToOne
    @JoinColumn(name = "shell_descriptors_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ShellDescriptorModel shellDescriptor;
}
