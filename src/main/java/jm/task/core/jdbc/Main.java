package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Vasya","Pechkin",(byte)35);
        userService.saveUser("Kiernan","Shipka",(byte)20);
        userService.saveUser("Lala","Salamanka",(byte)45);
        userService.saveUser("Kim","Wexler",(byte)40);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
