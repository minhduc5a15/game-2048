package com.minhduc5a12.game_2048;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveScore(int score) {
        String sql = "INSERT INTO scores (score) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getBestScore() {
        String sql = "SELECT MAX(score) AS best FROM scores";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("best");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
