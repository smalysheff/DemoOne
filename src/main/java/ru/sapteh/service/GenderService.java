package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.GenderEntity;

import java.util.List;

public class GenderService {
    private final SessionFactory factory;

    public GenderService (SessionFactory factory) {
        this.factory = factory;
    }



    public GenderEntity read(char code) {
        try(Session session = factory.openSession()){
            GenderEntity entity = session.get(GenderEntity.class, code);
            return entity;
        }
    }


}
