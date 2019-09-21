/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

/**
 *
 * @author ahmed
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ElementCollection
    @CollectionTable(
            name = "Hobby",
            joinColumns = @JoinColumn(name = "CUSTOMER_ID")
    )
    @Column(name="hobbies")
    private List<String> hobbies = new ArrayList();

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    public String getHobby() {

        return String.join(",", hobbies);
    }
    
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "PHONE")
    @Column(name ="Description") 
    private Map<String, String> phones = new HashMap<>();

    public void addPhone(String phoneNo, String description) {
        phones.put(phoneNo, description);
    }   

    public String getPhoneDesc(String phoneNo) {
        return phones.get(phoneNo);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
