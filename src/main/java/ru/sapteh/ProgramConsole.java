package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.ClientEntity;
import ru.sapteh.entity.ClientServiceEntity;
import ru.sapteh.entity.ServiceEntity;
import ru.sapteh.service.ClientServiceService;
import ru.sapteh.service.GenderService;
import ru.sapteh.service.ServiceService;
import ru.sapteh.service.ClientService;

import java.sql.Date;

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


        ClientEntity client = clientService.read(107);

        clientService.delete(client);

//        System.out.println(client);
//
//        client.setLastName("Petrov");
//        client.setFirstName("Petr");
//        client.setPatronymic("Petrovich");
//        client.setEmail("petrov@mail.ru");
//        client.setBirthday(new Date(7239479234238L));
//        client.setRegistrationDate(new Date(238749231L));
//        client.setPhone("23423423");
//        client.setPhotoPath("/src/lib.jpg");
//        client.setGenderEntity(genderService.read('м'));
//
//        clientService.update(client);
//
//        System.out.println(client);



//        ClientEntity client = clientService.read(97);


//        clientService.delete(client);
//        System.out.println(client);




        factory.close();


    }
}
