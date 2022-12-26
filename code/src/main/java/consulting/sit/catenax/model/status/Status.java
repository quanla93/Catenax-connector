//package consulting.sit.catenax.model.status;
//
//import consulting.sit.catenax.data.DirectionEnum;
//import consulting.sit.catenax.data.StatusEnum;
//import consulting.sit.catenax.model.ModelBaseInterface;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Data
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//@Table(schema = "status", name = "status")
//@SequenceGenerator(schema = "glossary", name = "activity_area_seq_gen", sequenceName = "activity_area_id_seq")
//public class Status implements ModelBaseInterface<Integer> {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_area_seq_gen")
//    private Integer id;
//
//    @Column(name = "name")
//    private String name;
//
//    @CreatedDate
//    @Column(name = "date_of_request")
//    private LocalDateTime dateOfRequest;
//
//    @Column(name = "date_of_feedback")
//    private LocalDateTime dateOfFeedback;
//
//    @Column(name = "status")
//    @Enumerated(EnumType.STRING)
//    private StatusEnum statusEnum;
//
//    @Column(name = "direction")
//    @Enumerated(EnumType.STRING)
//    private DirectionEnum directionEnum;
//}