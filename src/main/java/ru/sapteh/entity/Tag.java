package ru.sapteh.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private String title;

    @Column
    @NonNull
    private String color;

    @ManyToMany
    @JoinTable(
            name = "tagofclient",
            joinColumns = @JoinColumn(name = "TagID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ClientID", referencedColumnName = "ID")
    )
    Set<Client> clients;

}
