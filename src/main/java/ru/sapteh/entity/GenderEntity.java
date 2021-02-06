package ru.sapteh.entity;

//POJO

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gender")
public class GenderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private char Code;

    @Column
    private String Name;

    @Override
    public String toString() {
        return "Gender{" +
                "Code=" + Code +
                ", Name='" + Name + '\'' +
                '}';
    }
}
