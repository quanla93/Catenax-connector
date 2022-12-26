package consulting.sit.catenax.model.shelldescriptors;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = ShellDescriptorModel.ENTITY_NAME)
public class ShellDescriptorModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "ShellDescriptor";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column
    private String idShort;

    @Column
    private String identification;

    @ElementCollection
    private List<String> globalAssetId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shellDescriptor")
    private Set<DescriptionShellDescriptorsModel> descriptors;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shellDescriptor")
    private Set<SpecificAssetIdsModel> specificAssetIds;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shellDescriptor")
    private Set<SubmodelDescriptorsModel> submodelDescriptors;

}
