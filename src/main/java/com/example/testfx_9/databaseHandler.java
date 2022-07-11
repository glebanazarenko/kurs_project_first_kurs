package com.example.testfx_9;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class databaseHandler{
    static int id;
    static Connection connection;
    static String role_level;
    static int user_id_ok;
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
                //role_level = resultSet.getString("role_level");
                id = resultSet.getInt("id");
                //System.out.println("role_level = " + role_level);
                count ++;
            }

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateUser(String new_name, String new_login, String new_password, int user_id){

        String insert = "UPDATE `users` " +
                "SET `name` = ?, `login` = ?, `password` = ?" +
                "WHERE `users`.`id` = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, new_name);
            preparedStatement.setString(2, new_login);
            preparedStatement.setString(3, new_password);
            preparedStatement.setInt(4, user_id);

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

    public int checkQuote(int id_qoute){
        String insert = "SELECT control_quotes.user_id\n" +
                "FROM control_quotes\n" +
                "WHERE control_quotes.quote_id = ?;";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id_qoute);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()){
                //user_id_ok = resultSet.getInt("user_id");
                //System.out.println("id_user_ok = " + user_id_ok);
                if(user_id_ok == id) {
                    count++;
                }
            }

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList checkQuoteVer(int id_ver){
        String insert = "SELECT control_quotes.quote_id\n" +
                "FROM control_quotes, control\n" +
                "WHERE control.controlled_id = ? AND control.user_id = control_quotes.user_id;";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id_ver);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList id_quotes = new ArrayList<Integer>();
            while (resultSet.next()){
                int quote_id = resultSet.getInt("quote_id");
                //System.out.println("quote_id_ok = " + quote_id);
                id_quotes.add(quote_id);
            }
            return id_quotes;
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addQuote(String data, String quote_text, String subject_name, String teacher_name){

        String insert = "INSERT INTO `quotes` (`id`, `date`, `quote_text`, `subject_id`, `teacher_id`) \n" +
                "VALUES (NULL, ?, ?, (SELECT s.id FROM subject AS s WHERE s.subject_name = ?), (SELECT t.id FROM teacher AS t WHERE t.teacher_name = ?));";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, subject_name);
            preparedStatement.setString(4, teacher_name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addQuoteControl(int id_user, int id_quote){
        String insert = "INSERT INTO `control_quotes` (`quote_id`, `user_id`) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id_quote);
            preparedStatement.setInt(2, id_user);

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
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
            //System.out.println("Привет, как дела?");
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

    public void deleteQuote(String data, String quote_text, String subject_name, String teacher_name) {

        String insert = "DELETE FROM quotes\n" +
                "WHERE date = ? AND quote_text = ? AND subject_id = (SELECT s.id FROM subject AS s WHERE s.subject_name = ?) AND teacher_id = (SELECT t.id FROM teacher AS t WHERE t.teacher_name = ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, quote_text);
            preparedStatement.setString(3, subject_name);
            preparedStatement.setString(4, teacher_name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletequoteControl(String data, String quote_text, String subject_name, String teacher_name){

        String insert = "DELETE FROM control_quotes\n"+
                "WHERE control_quotes.quote_id = ? AND control_quotes.user_id = ?";

        try {
            databaseHandler handler = new databaseHandler();
            handler.checkQuote(MainController.id_quote);
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, MainController.id_quote);
            preparedStatement.setInt(2, user_id_ok);
            //System.out.println("USER_ID_OK = " + user_id_ok);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String hashPassword(String password){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());

            StringBuilder builder = new StringBuilder();
            for (byte password_hash : bytes) {
                builder.append(String.format("%02X",password_hash));
                //last_password_hash = password_hash;
            }
            //System.out.println(builder.toString() + "          ");
            String newPassword = builder.toString();
            return newPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int countQuotes(){
        String insert = "SELECT control_quotes.quote_id\n" +
                "FROM quotes, control_quotes\n" +
                "WHERE quotes.id = control_quotes.quote_id AND control_quotes.user_id = ?;";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            int count_quotes = 0;
            while (resultSet.next()) {
                count_quotes ++;
            }
            return count_quotes;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}