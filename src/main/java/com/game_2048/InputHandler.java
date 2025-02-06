package com.game_2048;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private final Board board;
    private final GamePanel gamePanel;

    public InputHandler(Board board, GamePanel gamePanel) {
        this.board = board;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Kiểm tra xem trò chơi đã kết thúc chưa
        if (!board.canMove()) {
            gamePanel.saveScore(); // Lưu điểm số vào cơ sở dữ liệu
            gamePanel.showGameOverDialog(); // Hiển thị hộp thoại khi thua game
            return;
        }

        boolean moved = switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> board.moveTiles(Direction.UP);
            case KeyEvent.VK_DOWN -> board.moveTiles(Direction.DOWN);
            case KeyEvent.VK_LEFT -> board.moveTiles(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> board.moveTiles(Direction.RIGHT);
            default -> false;
        };

        // Nếu di chuyển thành công, thêm ô mới và cập nhật giao diện
        if (moved) {
            board.addRandomTile();
            board.updateBoard();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}