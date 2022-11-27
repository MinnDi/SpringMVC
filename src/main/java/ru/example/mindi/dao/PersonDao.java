package ru.example.mindi.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.example.mindi.model.Person;

import java.util.List;

@Component
public class PersonDao {

    SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> getPeople() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("select p from Person p", Person.class).list();
        return people;
    }

    @Transactional(readOnly = true)
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional(readOnly = true)
    public Person getPerson(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p where p.email = :email", Person.class).setParameter("email", email).getSingleResult();

    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setName(updatePerson.getName());
        person.setAge(updatePerson.getAge());
        person.setEmail(updatePerson.getEmail());
        person.setAddress(updatePerson.getAddress());
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
