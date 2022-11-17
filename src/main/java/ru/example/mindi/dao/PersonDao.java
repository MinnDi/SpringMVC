package ru.example.mindi.dao;

import org.springframework.stereotype.Component;
import ru.example.mindi.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT = 0;
    List<Person> people = new ArrayList<>();

    {
        people.add(new Person(++PEOPLE_COUNT, "Dilyara", 23, "dilyara@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Farid", 27, "farid@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Rezeda", 59, "rezeda@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Ildar", 62, "ildar@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Name", 11, "name@mail.com"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = getPerson(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person-> person.getId() == id);
    }
}
