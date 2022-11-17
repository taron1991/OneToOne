package com.example.mockito.classTest;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "owner")
@Entity
@Data
@NoArgsConstructor
class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Animal> animal = new ArrayList<>();

    public void addAnimalToOwner(Animal animal) {
        this.animal.add(animal);
        animal.setOwner(this);
    }
}