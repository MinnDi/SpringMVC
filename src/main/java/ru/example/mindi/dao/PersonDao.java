package ru.example.mindi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.example.mindi.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("select * from person where id = ?", new PersonMapper(), id)
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getPerson(String email) {
        return jdbcTemplate.query("select * from person where email = ?", new PersonMapper(), email)
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, age, email, address) values (?, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("update person set name = ?, age = ?, email = ?, address = ? where id = ?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), updatePerson.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }
}
