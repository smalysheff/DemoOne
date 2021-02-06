package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.ClientEntity;

import java.util.List;

public class ClientService implements DAO<ClientEntity, Integer> {
    private final SessionFactory factory;

    public ClientService(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void creat(ClientEntity clientEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(clientEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public ClientEntity read(Integer key) {
        try(Session session = factory.openSession()){
            return session.get(ClientEntity.class, key);
        }
    }

    @Override
    public List<ClientEntity> readByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM ClientEntity";
            Query<ClientEntity> query = session.createQuery(hql);
            List<ClientEntity> clientEntities = query.list();
            return clientEntities;
        }
    }

    @Override
    public void update(ClientEntity clientEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(clientEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ClientEntity clientEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(clientEntity);
            session.getTransaction().commit();
        }
    }
}
