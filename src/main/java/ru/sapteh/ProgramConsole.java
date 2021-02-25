package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.service.ClientDaoImp;

import java.lang.reflect.Array;
import java.util.*;

public class ProgramConsole {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Client, Integer> clientDaoImpl = new ClientDaoImp(factory);

        List<Client> clients = clientDaoImpl.findByAll();

        for(Client client : clients){

            Set<ClientService> clientServiceSet = client.getClientServiceSet();
            if(clientServiceSet.size() != 0)
                System.out.println(clientServiceSet);




        }


    }
}
