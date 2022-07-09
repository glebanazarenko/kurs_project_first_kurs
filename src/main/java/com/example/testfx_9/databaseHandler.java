package com.example.testfx_9;

import java.sql.*;
public class databaseHandler{
    static Connection connection;
    public static Connection getDbConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2006_quotes",
                    "std_2006_quotes", "qwerty123");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
//
    }

    public void registrationUser(String name, String login, String password){
        String role_level = "user";

        String insert = "INSERT INTO users(name, login, password, role_level)\n" +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role_level);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkUser(String login, String password){
        String insert = "SELECT users.role_level FROM users WHERE users.login = ? AND users.password = ?;";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()){
                String role_level = resultSet.getString("role_level");
                System.out.println("role_level = " + role_level);
                count ++;
            }

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateUser(){
        String new_name = "Олег Евстропович";
        String new_login = "AH212";
        String new_password = "Gleb121";
        String new_role_level = "user";
        String user_id = "5";

        String insert = "UPDATE `users` " +
                "SET `name` = ?, `login` = ?, `password` = ?, `role_level` = ? " +
                "WHERE `users`.`id` = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, new_name);
            preparedStatement.setString(2, new_login);
            preparedStatement.setString(3, new_password);
            preparedStatement.setString(4, new_role_level);
            preparedStatement.setString(5, user_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(){
        String insert = "DELETE FROM users\n" +
                "WHERE users.id > 2";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addQuote(){
        String data = "2008-12-03";
        String quote_text = "Что делать?";
        String subject_id = "1";
        String teacher_id = "3";

        String insert = "INSERT INTO `quotes` (`date`, `quote_text`, `subject_id`, `teacher_id`)" +
                " VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, subject_id);
            preparedStatement.setString(4, teacher_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateQuote(){
        String data = "2008-12-03";
        String quote_text = "Что делать?";
        String subject_id = "1";
        String teacher_id = "3";
        String quotes_id = "1";

        String insert = "UPDATE `quotes` " +
                "SET `date` = ?, `quote_text` = ?, `subject_id` = ?, `teacher_id` = ? " +
                "WHERE `quotes`.`id` = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, subject_id);
            preparedStatement.setString(4, teacher_id);
            preparedStatement.setString(5, quotes_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQuote() {
        String data = "2008-12-03";
        String quote_text = "Что делать?";
        String subject_id = "1";
        String teacher_id = "3";

        String insert = "DELETE FROM quotes\n" +
                "WHERE date = ? AND quotes_text = ? AND subject_id = ? AND teacher_id = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, subject_id);
            preparedStatement.setString(4, teacher_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}