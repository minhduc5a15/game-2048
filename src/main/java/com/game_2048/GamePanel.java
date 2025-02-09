package com.game_2048;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final Board board;
    private final JLabel scoreLabel;
    private final JLabel bestLabel;
    private int score;
    private int best;

    public GamePanel(JLabel scoreLabel, JLabel bestLabel) {
        this.scoreLabel = scoreLabel;
        this.bestLabel = bestLabel;
        this.score = 0;

        this.best = DatabaseManager.getBestScore();
        bestLabel.setText("Best: " + best);

        setLayout(new GridLayout(4, 4, 10, 10));
        setBackground(new Color(0x9e8c7b));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        board = new Board(this);
        addInputHandler();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                add(board.getTile(i, j));
            }
        }
        board.addRandomTile();
        board.addRandomTile();
    }

    private void addInputHandler() {
        InputHandler inputHandler = new InputHandler(board, this);
        addKeyListener(inputHandler);
    }

    // Cập nhật điểm số
    public void updateScore(int points) {
        score += points;
        scoreLabel.setText("Score: " + score);

        if (score > best) {
            best = score;
            bestLabel.setText("Best: " + best);
        }
    }

    // Trả về điểm số hiện tại
    public int getScore() {
        return score;
    }

    // Lưu điểm số khi trò chơi kết thúc
    public void saveScore() {
        DatabaseManager.saveScore(score);
    }

    // Hiển thị hộp thoại khi thua game
    public void showGameOverDialog() {
        new GameOverDialog(this).show();
    }

    // Khởi động lại trò chơi
    public void restartGame() {
        score = 0;
        scoreLabel.setText("Score: 0");
        best = DatabaseManager.getBestScore();
        bestLabel.setText("Best: " + best);

        // Xóa tất cả các ô cũ khỏi bảng
        removeAll();

        // Khởi tạo lại Board
        board.initializeTiles(); // Khởi tạo lại các ô trên bảng

        // Thêm các ô mới vào giao diện
        initializeBoard();

        // Cập nhật giao diện
        revalidate();
        repaint();

        // Yêu cầu focus để nhận sự kiện bàn phím
        requestFocusInWindow();
    }
}