package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.entity.Service;
import ru.sapteh.service.ClientDaoImp;
import ru.sapteh.service.ClientServiceDaoImp;
import ru.sapteh.service.GenderDaoImp;
import ru.sapteh.service.ServiceDaoImp;

import java.util.List;
import java.util.Set;

public class ProgramConsole {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        DAO<Client, Integer> clientDaoImp = new ClientDaoImp(factory);
        DAO<Service, Integer> serviceDaoImp= new ServiceDaoImp(factory);
        DAO<ClientService, Integer> clientServiceDaoImp = new ClientServiceDaoImp(factory);
        GenderDaoImp genderDaoImp = new GenderDaoImp(factory);

        System.out.println();

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


        Client client = clientDaoImp.read(87);



//        List<ClientService> clientServiceList = clientServiceDaoImp.readByAll();
//
////        clientServiceList.stream().forEach(System.out :: println);
//
//        int count = 1;
//        for (ClientService clientService : clientServiceList){
//            if(clientService.getClient().getId() == (client.getId())){
//                count++;
//            }
//        }
//
//        System.out.println(count);

        List<ClientService> clientServiceList1 = clientServiceDaoImp.readByAll();
        System.out.println(clientServiceList1);


        Set<ClientService> clientServiceList = client.getClientServiceSet();

        System.out.println(clientServiceList);

//        ClientService clientService = clientServiceDaoImp.read(10);
//
//

//        clientService.delete(client);

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
