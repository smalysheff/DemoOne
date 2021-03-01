package ru.sapteh;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.entity.User;
import ru.sapteh.service.ClientDaoImp;
import ru.sapteh.service.UserDaoImpl;

import javax.swing.event.ChangeListener;
import java.lang.reflect.Array;
import java.util.*;

public class ProgramConsole {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Client, Integer> clientDaoImpl = new ClientDaoImp(factory);
        DAO<User, Integer> userDaoImpl = new UserDaoImpl(factory);

        userDaoImpl.findByAll().forEach(System.out::println);




    }
}
