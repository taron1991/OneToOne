package com.example.mockito.classTest;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class MainClass {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Animal.class)
                .addAnnotatedClass(Owner.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();


            Owner owner = new Owner();
            owner.setName("patrick");

            Animal animal = new Animal();
            animal.setName("sharik");

            Animal animal2 = new Animal();
            animal2.setName("moska");

            owner.addAnimalToOwner(animal);
            owner.addAnimalToOwner(animal2);

            currentSession.save(owner);

            currentSession.getTransaction().commit();

        }


    }


}

