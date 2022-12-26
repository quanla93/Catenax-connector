package consulting.sit.catenax.model.materialforrecycling;

import consulting.sit.catenax.model.ModelBaseInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = MaterialModel.ENTITY_NAME)
public class MaterialModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "Material";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "recycledContent")
    private Integer recycledContent;

    @Column(name = "materialName")
    private String materialName;

    @Column(name = "materialClass")
    private String materialClass;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "materialModel")
    private Set<ComponentModel> components;

}
