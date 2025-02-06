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
        setBackground(new Color(0xbbada0));
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
        Object[] options = {"Restart", "Exit"};
        int choice = JOptionPane.showOptionDialog(this, "Game Over! Final Score: " + score,
                "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    // Khởi động lại trò chơi
    private void restartGame() {
        score = 0;
        scoreLabel.setText("Score: 0");
        best = DatabaseManager.getBestScore();
        bestLabel.setText("Best: " + best);

        // Khởi tạo lại Board
        initializeBoard();

        // Yêu cầu focus để nhận sự kiện bàn phím
        requestFocusInWindow();
    }
}