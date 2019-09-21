package facades;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import entities.IPersonFacade;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
        
        
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    @Override
    public Person addPerson(String fName, String lName, String phone) {

        Person person = new Person(fName, lName, phone);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select p from Person p", Person.class);
            return query.getResultList();
        } finally {
            em.close();
        }

    }

    @Override
    public Person deletePerson(long id) {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        try {
            em.getTransaction().begin();
            
            em.remove(p); // other way of doing it
            em.getTransaction().commit();

            return p;
        } finally {
            em.close();
        }

    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        Person nPerson = p;
        try {
            TypedQuery query
                    = em.createQuery("UPDATE Person p SET p.firstName :firstName  WHERE p.id = :id ", Person.class);
            em.setProperty("firstName", p.getFirstName());
            em.setProperty("id", p.getId());
            em.getTransaction().commit();
            return nPerson;
        } finally {
            em.close();
        }
        // ret so you can get more values
    }

    @Override
    public Person getPerson(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            // dont need this part if i jsut use find(). thou not sure if its the best option
//            TypedQuery query;
//            query = em.createQuery("SELECT p FROM Person p  WHERE p.id = :id ", Person.class).setParameter("id", id);
//            Person p = query.getSingleResult();
            
            Person p = em.find(Person.class, id);
            return p;
            //return query.getSingleResult();

        } finally {
            em.close();
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
