/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author ahmed
 */
import entites.Customer;
import entites.Order;
import entites.ItemType;
import entites.OrderLine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class facadeBig {

    private static facadeBig instance;
    private static EntityManagerFactory emf;

    private facadeBig() {

    }

    public static facadeBig getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new facadeBig();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
// the first one
    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    
    public Customer findCustomer(long id) {
        Customer customer ;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            customer = em.find(Customer.class, id);
           // em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    public List <Customer> getAllCustomers() {
        
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public ItemType createItemType(String name, String decsription, int price) {
        ItemType iT1 = new ItemType(name, decsription, price);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(iT1);
            em.getTransaction().commit();
            return iT1;
        } finally {
            em.close();
        }
    }
    public ItemType findItemType(long id) {
        ItemType itemT1;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            itemT1 = em.find(ItemType.class, id);
           // em.persist(customer);
            em.getTransaction().commit();
            return itemT1;
        } finally {
            em.close();
        }
    }
    
    public Order createOrder(int id) {
        
        Order order = new Order(id);
        Customer customer = order.getCustomer();
        
        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            customer.addOrder(order);
            //customer.setOrders(orders);
            em.persist(order);
            em.persist(customer);
            em.getTransaction().commit();
            return order;
        } finally {
            em.close();
        }
    }
    
    
//    public OrderLine createOrderLine(int id) {
//        OrderLine orderL = 
//        Order order = new Order(id);
//        
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(customer);
//            em.getTransaction().commit();
//            return customer;
//        } finally {
//            em.close();
//        }
//    }

//    Create an Order and Add it to a Customer
//      Create an OrderLine for a specific ItemType, and add it to an Order
//      Find all Orders, for a specific Customer
//      Find the total price of an order ….
    

}
