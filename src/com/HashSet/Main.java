package com.HashSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        /**
         * Задание 2
         * Необходимо разработать приложение, которое по-
         * зволит сохранять информацию о логинах пользователей
         * и их паролях. Каждому пользователю соответствует пара
         * логин — пароль. При старте приложение отображается
         * меню:
         * 1. Добавить нового пользователя.
         * 2. Удалить существующего пользователя.
         * 3. Проверить существует ли пользователь.
         * 4. Изменить логин существующего пользователя.
         * 5. Изменить пароль существующего пользователя.
         * В зависимости от выбора пользователя выполняет-
         * ся действие, после чего меню отображается снова. Для
         * решения задачи используйте подходящий класс из Java
         * Collections Framework.
         */

        HashSet<User> user = new HashSet<>();
        user.add(new User("Artem", "111"));
        user.add(new User("Tatsiana", "222"));
        user.add(new User("Alexei", "333"));
        user.add(new User("Natasha", "444"));

        System.out.println("1. Добавить нового пользователя.\n" +
                "2. Удалить существующего пользователя.\n" +
                "3. Проверить существует ли пользователь.\n" +
                "4. Изменить логин существующего пользователя.\n" +
                "5. Изменить пароль существующего пользователя.");
        String menu = inputNumber(); // вызываем метод для ввода числа от 1 до 5
        while (menu.equals("1") ||menu.equals("2") || menu.equals("3") || menu.equals("4") || menu.equals("5")) {
            switch (menu) {
                case "1":
                    System.out.println("Вы выбрали: \n1. Добавить нового пользователя.");
                    System.out.println("Input LOGIN");
                    String login = addingLogin(); //вводим логин
                    System.out.println("Input PASSWORD");
                    String password = addingPassword(); //вводим пароль
                    //создаем нового user с введенными значениями
                    User u = new User(login, password);
                    if (!user.contains(u)) {// проверяем существует ли такой же логин
                        //если не существует то добавляем
                        user.add(u);
                    } else {
                        throw new Error("Такой логин уже используется");
                    }
                    menu = inputNumber();
                    break;
                case "2":
                    System.out.println("Вы выбрали: \n2. Удалить существующего пользователя.");
                    System.out.println("Введите LOGIN пользователя которого хотите удалить");
                    String deletelogin = addingLogin();//вводим логин
                    //создаем нового user с введенными значениями (пароль нам не важен)
                    User delUser = new User(deletelogin, null);
                    if (user.contains(delUser)) { // если пользователь существует удаляем
                        user.removeIf(us -> deletelogin.equals(us.getLogin()));
                        System.out.println("User " + deletelogin + " удален!!!");
                        menu = inputNumber();
                    } else {
                        //выбрасываем проверяемое исключение
                        try {
                            throw new IllegalArgumentException();
                        }catch (IllegalArgumentException ex){
                            System.out.println("Такого логина не существует!!!");
                            menu = inputNumber();
                        }
                    }
                    break;
                case "3":
                    System.out.println("Вы выбрали: \n3. Проверить существует ли пользователь.");
                    System.out.println("Введите LOGIN пользователя");
                    login = addingLogin();//вводим логин
                    User userCheck = new User(login, null);
                    if (user.contains(userCheck)) { //проверяем существует ли пользователь с таким именем
                        System.out.println("Такой пользователь существует!!!");
                    } else {
                        System.out.println("Такого пользователя не существует!!!");
                    }
                    menu = inputNumber();
                    break;
                case "4":
                    System.out.println("Вы выбрали: \n4. Изменить логин существующего пользователя.");
                    System.out.println("Введите LOGIN пользователя который хотите изменить");
                    login = addingLogin();//вводим логин
                    // выполняем перебор коллекции
                    String newLogin;
                    for (User us : user) {
                        if (us.getLogin().equals(login)) { //сравниваем логин
                            String tempPass = us.getPassword();//создаем временную переменную для
                            // хранения пароля необходимого для нас пользователя
                            System.out.println("Password : " + tempPass);//проверка
                            user.remove(us);//удаляем пользователя с введенным логином
                            System.out.println("Введите новый логин");
                            String newlogin = reader.readLine();
                            user.add(new User(newlogin, tempPass)); //добавляем пользователя со старым паролем
                            menu = inputNumber();
                            break;
                        }
                    }
                    System.out.println("Логина с именем : \"" + login + "\" не существует в списке!!!");
                    menu = inputNumber();
                    break;
                case "5":
                    System.out.println("Вы выбрали: \n5. Изменить пароль существующего пользователя.");
                    System.out.println("Введите LOGIN пользователя пароль которого хотите изменить");
                    login = reader.readLine();
                    //проверяем есть ли пользователь в списке и если есть удаляем
                    if (user.removeIf(x -> x.getLogin().equals(login))) {
                        System.out.println("Введите новый пароль для пользователя : " + login);
                        String newPassword = reader.readLine();
                        user.add(new User(login, newPassword));////добавляем пользователя с новым паролем
                    } else {
                        System.out.println("Логина с именем : \"" + login + "\" не существует в списке!!!");
                    }
                    menu = inputNumber();
                    break;
            }
        }
        user.forEach(x-> System.out.println("Nane : " + x.getLogin() + ", Password : " + x.getPassword()));
    }

    // метод для ввода числа от 1 до 5
    private static String inputNumber() throws IOException {
        System.out.println("Choose an action from 1 to 5");
        String str = reader.readLine();
        return str;
    }


    //вводим логин
    private static String addingPassword() throws IOException {
        String password = reader.readLine();
        return password;
    }

    //вводим пароль
    private static String addingLogin() throws IOException {
        String login = reader.readLine();
        return login;
    }
}
