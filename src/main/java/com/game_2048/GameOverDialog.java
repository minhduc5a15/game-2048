package com.game_2048;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog {
    private final GamePanel gamePanel;

    public GameOverDialog(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void show() {
        // Tạo một JPanel tùy chỉnh để chứa nội dung của hộp thoại
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(0xfaf8ef));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Thêm padding xung quanh panel

        // Tạo JLabel để hiển thị thông báo "Game Over"
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Font lớn và đậm
        gameOverLabel.setForeground(new Color(0x776e65)); // Màu chữ phù hợp
        gameOverLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Thêm padding dưới tiêu đề
        panel.add(gameOverLabel, BorderLayout.NORTH);

        // Tạo JLabel để hiển thị điểm số cuối cùng
        JLabel scoreLabel = new JLabel("Final Score: " + gamePanel.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Font vừa phải
        scoreLabel.setForeground(new Color(0x776e65)); // Màu chữ phù hợp
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Thêm padding dưới điểm số
        panel.add(scoreLabel, BorderLayout.CENTER);

        // Tạo các nút tùy chỉnh
        StyledButton restartButton = new StyledButton("Restart", true);
        StyledButton exitButton = new StyledButton("Exit", true);

        // Tạo một JPanel để chứa các nút
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(new Color(0xfaf8ef)); // Màu nền phù hợp
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Thêm padding phía trên
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Tạo JDialog và gán vào biến để tham chiếu
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(gamePanel), "Game Over", true);
        dialog.setContentPane(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(gamePanel); // Hiển thị giữa cửa sổ chính

        // Xử lý sự kiện khi nhấn nút Restart
        restartButton.addActionListener(e -> {
            gamePanel.restartGame(); // Gọi phương thức khởi động lại trò chơi
            dialog.dispose(); // Chỉ đóng hộp thoại, không đóng cửa sổ chính
        });

        // Xử lý sự kiện khi nhấn nút Exit
        exitButton.addActionListener(e -> System.exit(0)); // Thoát toàn bộ ứng dụng

        // Hiển thị hộp thoại
        dialog.setVisible(true);
    }
}