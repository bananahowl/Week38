/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Semester;
import entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author ahmed
 */
public class FacadeAll {

    private static FacadeAll instance;
    private static EntityManagerFactory emf;

    private FacadeAll() {

    }

    public static FacadeAll getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeAll();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public <List> Student findAllStudents() {
//        Student stud;
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            stud = em.find(ItemType.class, id);
//           // em.persist(customer);
//            em.getTransaction().commit();
//            return stud;
//        } finally {
//            em.close();
//        }
//    }
    public List<Student> getAllStudents() {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Student.findAll", Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudentsWithName(String name) {

        EntityManager em = emf.createEntityManager();
        List<Student> listStudent;

        try {
            TypedQuery query
                    = em.createQuery("Student.findByFirstname", Student.class).setParameter("firstname", name);
            listStudent = query.getResultList();
            return listStudent;
        } finally {
            em.close();
        }
    }

    public void createStudent(String firstname, String lastname) {
        Student stud = new Student(firstname, lastname);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(stud);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public void assignStudent(Long studID, Long semId) {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student stud = em.createNamedQuery("Student.findById", Student.class).setParameter("id", studID).getSingleResult();
            Semester semester = em.createNamedQuery("Semester.findById", Semester.class).setParameter("id", semId).getSingleResult();
            semester.addStudent(stud);
            stud.setSemester(semester);
            em.persist(semester);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
    public Student findStudentByLastName(String lastname) {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Student.findByLastname", Student.class).setParameter("lastname", lastname);
             Student stud = (Student) query.getSingleResult();
             return stud;
        } finally {
            em.close();
        }
    }
    
    public int countAllStudents() {
        int count;
        EntityManager em = emf.createEntityManager();
        try {           
            TypedQuery query
                    = em.createQuery("SELECT COUNT(s) FROM Student s", Student.class);
            count = (int) query.getSingleResult();
            return count;
                    
        } finally {
            em.close();
        }
    }
    
    public int countAllStudentsBySemesterName(String name) {
        int count;
        EntityManager em = emf.createEntityManager();
        try {           
            TypedQuery query
                    = em.createQuery("SELECT COUNT(s) FROM Student s inner join Semester sm where sm.name = :name", Student.class)
                    .setParameter("name", name);
            count = (int) query.getSingleResult();
            return count;
                    
        } finally {
            em.close();
        }
    }
    
    public int countAllStudentsInAllSemesters(String name) {
        int count;
        EntityManager em = emf.createEntityManager();
        try {           
            TypedQuery query
                    = em.createQuery("SELECT COUNT(s) FROM Student s inner join Semester sm", Student.class);
            count = (int) query.getSingleResult();
            return count;
                    
        } finally {
            em.close();
        }
    }
    
    
    
    

}
