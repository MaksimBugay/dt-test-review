package com.dynatrace.recruitment;

import static com.dynatrace.recruitment.CountryCode.AT;
import static com.dynatrace.recruitment.CountryCode.PL;
import static com.dynatrace.recruitment.CountryCode.US;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonsDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    private static CityCache cityCache = new CityCache();

    public List<PersonDto> findWithSql(String sql, int max) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<PersonDto> persons = new ArrayList<>();
        while (rs.next()) {
            PersonDto person = new PersonDto();
            person.setName(rs.getString("name"));
            person.setSurname(rs.getString("surname"));

            City city = getCity(rs.getString("city"));
            person.setCity(city);
            persons.add(person);
        }
        stmt.close();
        return persons.stream()
                .limit(max)
                .collect(Collectors.toList());
    }

    private City getCity(String city) throws SQLException {
        if (cityCache.getCity(city) == null) {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM city WHERE name='" + city + "'");
            String countryCodeStr = rs.getString("country_code");
            CountryCode countryCode;
            switch (countryCodeStr) {
                case "AT": countryCode = AT;
                case "PL": countryCode = PL;
                case "US": countryCode = US;
                default: countryCode = null;
            }
            cityCache.addCity(new City(rs.getString("name"), countryCode));
        }
        return cityCache.getCity(city);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
