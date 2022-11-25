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
        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
        return people;
    }

    @Transactional(readOnly = true)
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p where id = :id", Person.class).setParameter("id", id).getSingleResult();
    }

    @Transactional(readOnly = true)
    public Person getPerson(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p where email = :email", Person.class).setParameter("email", email).getSingleResult();

    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Person set name = :name, age = :age, email = :email, address = :address where id = :id", Person.class)
                .setParameter(":name", updatePerson.getName())
                .setParameter(":age", updatePerson.getAge())
                .setParameter(":email", updatePerson.getEmail())
                .setParameter(":address", updatePerson.getAddress())
                .executeUpdate();
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
