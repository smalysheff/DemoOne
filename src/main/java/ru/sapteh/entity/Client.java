package ru.sapteh.entity;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

//POJO

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
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
    private Gender Gender;

    @OneToMany(mappedBy = "client")
    private Set<ClientService> clientServiceSet;


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
                ", Gender=" + Gender +
                '}';
    }
}
