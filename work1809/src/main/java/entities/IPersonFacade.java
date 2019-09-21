/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import exception.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author ahmed
 */
public interface IPersonFacade {

    public Person addPerson(String fName, String lName, String phone);

    public Person deletePerson(long id) throws PersonNotFoundException;

    public Person getPerson(long id) throws PersonNotFoundException;

    public List<Person> getAllPersons();

    public Person editPerson(Person p)throws PersonNotFoundException;

}
