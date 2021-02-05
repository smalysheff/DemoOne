package ru.sapteh.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientService")
public class ClientServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClientEntity client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private ServiceEntity service;

    @Column
    private Date startTime;

    @Column
    private String comment;


    @Override
    public String toString() {
        return "ClientService{" +
                "id=" + id +
                ", client=" + client +
                ", service=" + service +
                ", dateTime=" + startTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
