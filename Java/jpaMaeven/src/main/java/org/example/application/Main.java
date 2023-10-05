package org.example.application;

import org.example.domain.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // INSTANCIANDO NO BANCO DE DADOS


        // ID nulo pois o banco de dados vai atribuir com o autoincremento o id delas
//        Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
//        Pessoa p2 = new Pessoa(null, "Joao da Silveira", "joao@gmail.com");
//        Pessoa p3 = new Pessoa(null, "Maria", "maria@gmail.com");

        // persisntenceUnitName = Nome que está no persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        //Sempre que sua operação não é uma simples consulta tem que iniciar uma transação
        em.getTransaction().begin();

//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);



        // BUSCAR NO BANCO DE DADOS
        Pessoa p = em.find(Pessoa.class, 2);
        System.out.println(p);





        // Fecha a transação
        em.getTransaction().commit();
        System.out.println("Pronto");
        // Fecha os Entity
        em.close();
        emf.close();
    }
}