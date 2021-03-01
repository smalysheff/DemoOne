package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.User;

import java.util.List;

public class UserDaoImpl implements DAO<User, Integer> {

    private final SessionFactory factory;

    public UserDaoImpl(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public User findById(Integer id) {
        try(Session session = factory.openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> findByAll() {
        try(Session session = factory.openSession()){
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void create(User user) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
