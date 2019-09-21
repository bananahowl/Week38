package rest;

import DTO.PersonDTO;
import DTO.PersonsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import exception.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);

    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
     public PersonResource() {
//         if(FACADE.getPerson(1) == null){
//             for(FACADE )
//         }
         if (FACADE.getPersonCount() == 0 ) {
            
            FACADE.addPerson("Kurt", "Wonnegut", "21212121");
            FACADE.addPerson("Hanne", "kNielsen", "88888888");
        }
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Main page for Person assignment\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        List<Person> personList = FACADE.getAllPersons();
        PersonsDTO DTOList = new PersonsDTO(personList);
        //System.out.println("--------------->"+count);
        return GSON.toJson(DTOList);
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonById(@PathParam("id") int id) throws PersonNotFoundException {
        Person p = FACADE.getPerson(id);
        PersonDTO DTOPerson = new PersonDTO(p);
        //System.out.println("--------------->"+count);
        if(p == null){
        throw new PersonNotFoundException("This value does not exitst");
        }
        return GSON.toJson(DTOPerson);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String removePerson(@PathParam("id") int id) {
        FACADE.deletePerson(id);
        return "{\" status \": \"OK\"}";
//        return gson.toJson("person har been removed");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editPerson(String personAsJson, @PathParam("id") int id) {
        Date date = new Date();

        Person pOrignal = FACADE.getPerson(id);
        Person personNew = GSON.fromJson(personAsJson, Person.class);
        pOrignal.setFirstName(personNew.getFirstName());
        pOrignal.setLastName(personNew.getLastName());
        pOrignal.setPhone(personNew.getPhone());
        pOrignal.setLastEdited(date);
        PersonDTO pDTO = new PersonDTO(pOrignal);
        // makes that the value return is on a good json format
        return GSON.toJson(pDTO);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String personAsJson) {
        Person personNew = GSON.fromJson(personAsJson, Person.class);
        FACADE.addPerson(personNew.getFirstName(), personNew.getLastName(), personNew.getPhone());
        System.out.println("val sent");
        return GSON.toJson(personNew);
    }
    
}
