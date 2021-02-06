package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.ClientServiceEntity;

import java.util.List;

public class ClientServiceService implements DAO<ClientServiceEntity, Integer> {
    private final SessionFactory factory;

    public ClientServiceService(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void creat(ClientServiceEntity clientServiceEntity) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(clientServiceEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public ClientServiceEntity read(Integer key) {
        try(Session session = factory.openSession()){
            return session.get(ClientServiceEntity.class, key);
        }
    }

    @Override
    public List<ClientServiceEntity> readByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM ClientServiceEntity";
            Query<ClientServiceEntity> query = session.createQuery(hql);
            List<ClientServiceEntity> clients = query.list();
            return clients;
        }
    }

    @Override
    public void update(ClientServiceEntity client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ClientServiceEntity client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        }
    }
}
