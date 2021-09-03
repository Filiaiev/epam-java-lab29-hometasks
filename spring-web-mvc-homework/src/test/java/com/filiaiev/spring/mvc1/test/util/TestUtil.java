package com.filiaiev.spring.mvc1.test.util;

import com.filiaiev.spring.mvc1.model.*;
import sun.nio.cs.US_ASCII;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private List<Order> orders;
    private List<Client> clients;
    private List<Employee> employees;
    private List<User> users;
    private List<Person> persons;

    public TestUtil() {}

    public void initOrdersTestData() {
        initPersons();
        initUsers();
        initClients();
        initEmployees();
        initOrders();
    }

    private void initUsers() {
        User user1 = new User();
        User user2 = new User();

        user1.setId(1);
        user1.setEmail("furna@gmail.com");
        user1.setLogin("furna");
        user1.setPassword("123321");
        user1.setPerson(persons.get(0));
        user1.setRole(Role.ROLE_CLIENT);

        user2.setId(2);
        user2.setEmail("komar@gmail.com");
        user2.setLogin("koma");
        user2.setPassword("1111");
        user2.setPerson(persons.get(1));
        user2.setRole(Role.ROLE_REPAIRER);

        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }

    private void initPersons() {
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setId(1);
        person1.setFirstName("Vladyslav");
        person1.setMiddleName("Dmytrovych");
        person1.setLastName("Filiaiev");
        person1.setBirthDate(LocalDate.of(2002, 2, 20));

        person2.setId(2);
        person2.setFirstName("Dmytro");
        person2.setMiddleName("Komarov");
        person2.setLastName("Kostyantynovych");
        person2.setBirthDate(LocalDate.of(1987, 4, 12));

        persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
    }

    private void initClients() {
        Client client1 = new Client();

        client1.setId(1);
        client1.setUser(users.get(0));
        client1.setCash(new BigDecimal(1200));

        clients = new ArrayList<>();
        clients.add(client1);
    }

    private void initEmployees() {
        Employee employee1 = new Employee();

        employee1.setId(1);
        employee1.setUser(users.get(0));

        employees = new ArrayList<>();
        employees.add(employee1);
    }

    private void initOrders() {
        Order order1 = new Order();
        Order order2 = new Order();

        order1.setId(1);
        order1.setOrderDate(
                Timestamp.valueOf(LocalDateTime.of(2020, 5, 26, 7, 43)));
        order1.setClient(clients.get(0));
        order1.setDescription("Test order furna");
        order1.setStatus(OrderStatus.CREATED);

        order2.setId(2);
        order2.setOrderDate(
                Timestamp.valueOf(LocalDateTime.of(2019, 5, 14, 7, 43)));
        order2.setClient(clients.get(0));
        order2.setDescription("Test order furna 2");
        order2.setEmployee(employees.get(0));
        order2.setCost(new BigDecimal(600));
        order2.setStatus(OrderStatus.PAID);

        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
