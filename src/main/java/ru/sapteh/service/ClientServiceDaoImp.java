package ru.sapteh.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.ClientService;

import java.util.List;

public class ClientServiceDaoImp implements DAO<ClientService, Integer> {
    private final SessionFactory factory;

    public ClientServiceDaoImp(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void creat(ClientService clientService) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(clientService);
            session.getTransaction().commit();
        }
    }

    @Override
    public ClientService read(Integer key) {
        try(Session session = factory.openSession()){
            ClientService clientService = session.get(ClientService.class, key);
            return clientService;
        }
    }

    @Override
    public List<ClientService> readByAll() {
        try(Session session = factory.openSession()) {
            String hql = "FROM ClientService";
            Query<ClientService> query = session.createQuery(hql);
            List<ClientService> clients = query.list();
            return clients;
        }
    }

    @Override
    public void update(ClientService client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ClientService client) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        }
    }
}
