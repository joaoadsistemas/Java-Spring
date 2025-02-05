package com.joaosilveira.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // select * from person
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    };

    // select * from person where id = ?
    public Person findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }

    // delete from person where id = ?
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id = ?", id);
    }

    // insert into person (id, name, location, birth_date) values (?, ?, ?, ?)
    public int insert(Person person) {
        return jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)",
                person.getId(), person.getName(), person.getLocation(), person.getBirthDate());
    }

    // update person set name = ?, location = ?, birth_date = ? where id = ?
    public int update(Person person) {
        return jdbcTemplate.update("update person set name = ?, location = ?, birth_date = ? where id = ?",
                person.getName(), person.getLocation(), person.getBirthDate(), person.getId());
    }

}
