package com.game_2048;

import javax.swing.*;
import java.awt.*;

public class Game2048 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("2048 Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 600); // Tăng chiều cao để chứa phần hiển thị điểm số và padding

            // Đặt cửa sổ ở giữa màn hình
            frame.setLocationRelativeTo(null);

            // Tạo panel chứa điểm số
            JPanel scorePanel = new JPanel();
            scorePanel.setLayout(new GridLayout(1, 2)); // 1 hàng, 2 cột
            scorePanel.setBackground(new Color(0xfaf8f1)); // Màu nền phù hợp
            scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

            // Tạo và thêm các JLabel cho "Score" và "Best"
            JLabel scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
            JLabel bestLabel = new JLabel("Best: 0", SwingConstants.CENTER);

            scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
            bestLabel.setFont(new Font("Arial", Font.BOLD, 20));

            scoreLabel.setForeground(new Color(0x9f8975));
            bestLabel.setForeground(new Color(0x9f8975));

            scorePanel.add(scoreLabel);
            scorePanel.add(bestLabel);

            // Tạo game panel
            GamePanel gamePanel = new GamePanel(scoreLabel, bestLabel);

            // Thêm các panel vào cửa sổ
            frame.add(scorePanel, BorderLayout.NORTH); // Đặt scorePanel ở phía trên
            frame.add(gamePanel, BorderLayout.CENTER); // Đặt gamePanel ở giữa

            frame.setVisible(true);
            gamePanel.requestFocusInWindow(); // Đảm bảo GamePanel nhận sự kiện bàn phím
        });
    }
}