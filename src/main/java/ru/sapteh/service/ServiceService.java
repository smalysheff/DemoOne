package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.ServiceEntity;

import java.util.List;

public class ServiceService implements DAO<ServiceEntity, Integer> {
    private final SessionFactory factory;

    public ServiceService(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void creat(ServiceEntity serviceEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(serviceEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public ServiceEntity read(Integer key) {
        try(Session session = factory.openSession()){
            return session.get(ServiceEntity.class, key);
        }
    }

    @Override
    public List<ServiceEntity> readByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM ServiceEntity ";
            Query<ServiceEntity> query = session.createQuery(hql);
            List<ServiceEntity> serviceEntities = query.list();
            return serviceEntities;
        }
    }

    @Override
    public void update(ServiceEntity serviceEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(serviceEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ServiceEntity serviceEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(serviceEntity);
            session.getTransaction().commit();
        }
    }
}
