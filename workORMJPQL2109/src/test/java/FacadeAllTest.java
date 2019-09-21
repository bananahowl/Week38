
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed
 */
public class FacadeAllTest {
    
    /*
    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = PersonFacade.getFacadeExample(emf);
    }

//      **** HINT **** 
//        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
//        ONE COMMON place accessible from anywhere.
//        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
//        See below for how to use these files. This is our RECOMENDED strategy
    
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Some txt", "More text","21212121");
        p2 = new Person("aaa", "bbb","42424242");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
    */
    
}
