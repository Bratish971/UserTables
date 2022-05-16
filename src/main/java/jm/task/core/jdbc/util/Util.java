package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "mytestdb";
        String userName = "root";
        String password = "root";

        return getConnection(hostName,dbName,userName,password);

    }

    public static Connection getConnection(String hostName, String dbName
            , String userName, String password) throws SQLException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        return DriverManager.getConnection(connectionURL,userName,password);

    }

    public static SessionFactory setUpSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(Environment.URL, "jdbc:mysql://localhost:3306/mytestdb?serverTimezone=Europe/Moscow")
                .applySetting(Environment.USER, "root")
                .applySetting(Environment.PASS, "root")
                .build();
        try {
            return new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
    }
}
