package com.example.demo.util;

import java.sql.*;
import java.util.Random;

public class UserPostDummyDataGenerator {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Scale_out_session_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "6548";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Class.forName("org.mariadb.jdbc.Driver");

            generateDummyUserData(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void generateDummyUserData(Connection conn) throws SQLException {
        final String insertUserQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
        final String insertPostQuery = "INSERT INTO posts (title, content, user_id) VALUES (?, ?, ?)";
        Random random = new Random();

        for (int userId = 1; userId <= 1000; userId++) {
            int createdUserId = insertUser(conn, insertUserQuery, "User" + userId, random);
            insertPostsForUser(conn, insertPostQuery, createdUserId, random);
        }
    }

    private static int insertUser(Connection conn, String query, String username, Random random) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, "Password" + random.nextInt(1000));
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    private static void insertPostsForUser(Connection conn, String query, int userId, Random random) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            int numberOfPosts = 5 + random.nextInt(11);

            for (int i = 1; i <= numberOfPosts; i++) {
                pstmt.setString(1, "Post Title " + i + " of User " + userId);
                pstmt.setString(2, "Post Content " + i + " of User " + userId);
                pstmt.setInt(3, userId);
                pstmt.executeUpdate();
            }
        }
    }
}