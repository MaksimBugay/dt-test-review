package com.dynatrace.recruitment;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class PersonRestResource {

    private static final int MAX = 100_000;
    private PersonsDao dao = new PersonsDao();

    @GET
    public List<PersonDto> getPersons(@QueryParam("surname") String surname) throws SQLException {
        List<PersonDto> persons = dao.findWithSql("SELECT * FROM person WHERE surname='" + surname + "'", MAX);
        return persons;
    }
}
