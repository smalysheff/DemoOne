package ru.sapteh.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

//POJO

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String FirstName;

    @Column
    private String LastName;

    @Column
    private String Patronymic;

    @Column
    private Date Birthday;

    @Column
    private Date RegistrationDate;

    @Column
    private String Email;

    @Column
    private String Phone;

    @Column
    private String PhotoPath;

    @ManyToOne
    @JoinColumn(name = "GenderCode")
    private GenderEntity GenderEntity;

    @OneToMany(mappedBy = "client")
//    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<ClientServiceEntity> clientServiceEntities;


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Patronymic='" + Patronymic + '\'' +
                ", Birthday=" + Birthday +
                ", RegistrationDate=" + RegistrationDate +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", PhotoPath='" + PhotoPath + '\'' +
                ", Gender=" + GenderEntity +
                '}';
    }
}
