package com.example.mockito.classTest;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table(name = "animal")
@Entity
@Data
@NoArgsConstructor
class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_id")
    private Owner owner;

    public Animal(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

}
