package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.ClientEntity;
import ru.sapteh.model.ClientServiceEntity;
import ru.sapteh.model.GenderEntity;
import ru.sapteh.model.ServiceEntity;
import ru.sapteh.service.ClientServiceService;
import ru.sapteh.service.GenderService;
import ru.sapteh.service.ServiceService;
import ru.sapteh.service.ClientService;

import java.sql.Date;
import java.util.List;

public class ProgramConsole {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<ClientEntity, Integer> clientService = new ClientService(factory);
        DAO<ServiceEntity, Integer> serviceService= new ServiceService(factory);
        DAO<ClientServiceEntity, Integer> clientServiceService = new ClientServiceService(factory);
        GenderService genderService = new GenderService(factory);

//        System.out.println("===============================================================================\n");
//        clientService.readByAll().stream().forEach(System.out::println);
//        System.out.println("===============================================================================\n");
//        serviceService.readByAll().stream().forEach(System.out::println);
//        System.out.println("===============================================================================\n");
//        clientServiceService.readByAll().stream().forEach(System.out::println);

//        System.out.println(clientService.read(1));
//        System.out.println();
//        System.out.println(serviceService.read(1));
//        System.out.println();
//        System.out.println(clientServiceService.read(1));

//        ClientEntity client = new ClientEntity();
//        client.setLastName("Ivanov");
//        client.setFirstName("Ivan");
//        client.setPatronymic("Ivanovich");
//        client.setEmail("ivanov@mail.ru");
//        client.setBirthday(new Date(723947923423L));
//        client.setRegistrationDate(new Date(23874923L));
//        client.setPhone("23423423");
//        client.setPhotoPath("/src/lib.jpg");
//        client.setGenderEntity(genderService.read('м'));
//
//        clientService.creat(client);

        ClientEntity client = clientService.read(97);


        clientService.delete(client);
//        System.out.println(client);




        factory.close();


    }
}
