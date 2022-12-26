package consulting.sit.catenax.model.materialforrecycling;

import consulting.sit.catenax.model.ModelBaseInterface;
import consulting.sit.catenax.model.materialforrecycling.enums.EAggregateState;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity(name = ComponentModel.ENTITY_NAME)
public class ComponentModel implements ModelBaseInterface<Integer> {

    public static final String ENTITY_NAME = "Component";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
    private Integer id;

    @Column(name = "materialName")
    private String materialName;

    @Column(name = "materialClass")
    private String materialClass;

    @Column(name = "recycledContent")
    private Integer recycledContent;

    @Column(name = "materialAbbreviation")
    private String materialAbbreviation;

    @Enumerated(EnumType.STRING)
    private EAggregateState aggregateState;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_quantity", referencedColumnName = "id")
    private QuantityModel quantityModel;

    @ManyToOne
    private MaterialModel materialModel;


}
