package ru.sapteh.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Service;

import java.util.List;

public class ServiceDaoImp implements DAO<Service, Integer> {
    private final SessionFactory factory;

    public ServiceDaoImp(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void creat(Service service) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public Service read(Integer key) {
        try(Session session = factory.openSession()){
            Service service = session.get(Service.class, key);
            return service;
        }
    }

    @Override
    public List<Service> readByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM Service ";
            Query<Service> query = session.createQuery(hql);
            List<Service> serviceEntities = query.list();
            return serviceEntities;
        }
    }

    @Override
    public void update(Service service) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(service);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Service service) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(service);
            session.getTransaction().commit();
        }
    }
}
