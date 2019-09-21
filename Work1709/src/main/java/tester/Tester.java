/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.Customer;

/**
 *
 * @author ahmed
 */
public class Tester {

    public static void main(String[] args) {
        
        Customer cust1 = new Customer("bob", "nielson");
        cust1.addHobby("Dabbing");
        cust1.addHobby("smach");
        cust1.addHobby("Eat mcd");
        cust1.addPhone("21212121", "make it eazy to move");
        cust1.addPhone("24242424", "a legit move company");

        /*
        Customer cust2 = new Customer("gustav", "børg");
        cust2.addHobby("dacing the moonlight");
        cust2.addHobby("living it");
        cust2.addHobby("watching smartTV");*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU2");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust1);
            // em.persist(cust2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        em = emf.createEntityManager();
        Customer found = em.find(Customer.class, cust1.getId());
        System.out.println("Hobbies : " + found.getHobby());
        // to test the map part is working 
        System.out.println("Phone : " + found.getPhoneDesc("21212121"));
        
    }
    
}
