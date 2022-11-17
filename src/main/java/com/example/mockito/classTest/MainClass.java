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

            Owner owner2 = new Owner();
            owner2.setName("alex");

            Animal animal = new Animal();
            animal.setName("sharik");
            animal.setOwner(owner);

            Animal animal2 = new Animal();
            animal2.setName("mosya");
            animal2.setOwner(owner2);

            currentSession.save(animal2);
            currentSession.save(animal);

            currentSession.getTransaction().commit();

        }


    }


}

