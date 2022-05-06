package edu.school21.chat.app;

import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        MessagesRepositoryJdbcImpl mrji = new MessagesRepositoryJdbcImpl(DataSource.getDs());

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message ID");
        try {
            long id = sc.nextLong();
            mrji.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
