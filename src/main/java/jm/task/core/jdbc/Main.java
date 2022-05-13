package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Vasya","Pechkin",(byte)35);
        System.out.println("User с именем – Vasya добавлен в базу данных");
        userService.saveUser("Kiernan","Shipka",(byte)20);
        System.out.println("User с именем – Kiernan добавлен в базу данных");
        userService.saveUser("Lala","Salamanka",(byte)45);
        System.out.println("User с именем – Lala добавлен в базу данных");
        userService.saveUser("Kim","Wexler",(byte)40);
        System.out.println("User с именем – Kim добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
