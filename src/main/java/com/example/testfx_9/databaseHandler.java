package com.example.testfx_9;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class databaseHandler{
    static int id;
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

            checkUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkUser(String login, String password){
        String insert = "SELECT users.role_level, users.id FROM users WHERE users.login = ? AND users.password = ?;";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()){
                String role_level = resultSet.getString("role_level");
                id = resultSet.getInt("id");
                System.out.println("role_level = " + role_level);
                count ++;
            }

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateUser(String new_name, String new_login, String new_password, String user_id){

        String insert = "UPDATE `users` " +
                "SET `name` = ?, `login` = ?, `password` = ?" +
                "WHERE `users`.`id` = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, new_name);
            preparedStatement.setString(2, new_login);
            preparedStatement.setString(3, new_password);
            preparedStatement.setString(4, user_id);

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

    public ArrayList Quotes(){
        String insert = "SELECT a.date, t.teacher_name, a.quote_text, s.subject_name  " +
                "FROM quotes AS a, teacher AS t, subject AS s  " +
                "WHERE t.id = a.teacher_id AND s.id = a.subject_id;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList arrayList = new ArrayList<Person>();
            while (resultSet.next()){
                Date data = resultSet.getDate("date");
                String teacher_name = resultSet.getString("teacher_name");
                String quote_text = resultSet.getString("quote_text");
                String subject_name = resultSet.getString("subject_name");
                Person new_person = new Person(data.toString(), teacher_name, quote_text, subject_name);
                arrayList.add(new_person);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public void updateQuote(String data, String quote_text, String subject_name, String teacher_name, int quotes_id){

        String insert = "UPDATE `quotes` \n" +
                "SET `date` = ?, `quote_text` = ?, `subject_id` = (SELECT s.id FROM subject AS s WHERE s.subject_name = ?), `teacher_id` = (SELECT t.id FROM teacher AS t WHERE t.teacher_name = ?) \n" +
                "WHERE `quotes`.`id` = ?;";


        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, subject_name);
            preparedStatement.setString(4, teacher_name);
            preparedStatement.setInt(5, quotes_id);

            preparedStatement.executeUpdate();
            System.out.println("Привет, как дела?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int id_qoutes(String data, String teacher_name, String quote_text, String subject_name){
        String insert = "SELECT a.id\n" +
                "FROM quotes AS a, teacher AS t, subject AS s\n" +
                "WHERE a.date = ? AND a.quote_text = ? AND (SELECT t.id FROM teacher AS t WHERE t.teacher_name = ?) = a.teacher_id AND (SELECT s.id FROM subject AS s WHERE s.subject_name = ?) = a.subject_id\n" +
                "LIMIT 1;";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, teacher_name);
            preparedStatement.setString(4, subject_name);

            ResultSet resultSet = preparedStatement.executeQuery();
            int id_quote = 0;
            while (resultSet.next()) {
                id_quote = resultSet.getInt("id");
            }
            return id_quote;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
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