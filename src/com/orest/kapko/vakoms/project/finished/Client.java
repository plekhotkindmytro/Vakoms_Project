package com.orest.kapko.vakoms.project.finished;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.sql.SQLException;
import java.util.List;

class Client {

    private static String line = null;
    private static Socket skt = null;

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
//---------Створюємо Swing Window------------------------------------------------------------------------------------------
        /* Створюємо нову форму Gui */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
//----------------------------------------------------------------------------------------------------------------------
        DataBase.conToDB();
        DataBase.createDB();
        DataBase.updateUser();
        DataBase.deleteUser();
        DataBase.addNewUser();
        DataBase.getAllUsers();
        DataBase.closeDB();
//----------------------------------------------------------------------------------------------------------------------
        try {
            Socket skt = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            System.out.print("Виводжу отриману від сервера строку на консоль: '");
            while (!in.ready()) {
            }
            System.out.print(line = in.readLine()); // Читаємо строку і одразу ж виводимо
            System.out.print("'\n");

            // Converts JSON string into a collection of Users object.
            Gson gson = new Gson();
            Type type = new TypeToken<List<User>>() {}.getType();
            List<User> usersList = gson.fromJson(line, type);

            for (User user : usersList) {
                System.out.println("User's ID: " + user.getId() + ". " + "User's NAME: " +
                        user.getName() + ". " + "User's Email: " + user.getEmail());
            }
            in.close();
        }
        catch(Exception e) {
            System.out.print("Бляха-муха, щось пішло не так!\n");
        }
    }

    public static void doAdd(String name, String email) {

//        try (BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()))) {
//            System.out.print("Виводжу отриману від сервера строку на консоль: '");
//            while (!in.ready()) {
//            }
//            System.out.print(line = in.readLine()); // Читаємо строку і одразу ж виводимо
//            System.out.print("'\n");
//
//            // Converts JSON string into a collection of Users object.
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<User>>() {
//            }.getType();
//            List<User> usersList = gson.fromJson(line, type);
//
//            for (User user : usersList) {
//                System.out.println("User's ID: " + user.getId() + ". " + "User's NAME: " +
//                        user.getName() + ". " + "User's Email: " + user.getEmail());
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(name + " " + email);
        //упакувати це в JSON
        //надсилаю сокетом на сервер((як серверу сказати, яка конкрено операція має відбутись ????)),
        // розпаршую там і заношу в базу даних
        //по замовчуванню має в свінгу показути всіх наявних в БД юзерів, як це реалізувати ??
        //є метод getAllUsers!
    }
}