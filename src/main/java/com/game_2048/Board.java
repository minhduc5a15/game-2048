package com.game_2048;

import java.util.Random;

public class Board {
    private static final int SIZE = 4; // Kích thước bảng (4x4)
    private final Tile[][] tiles; // Mảng 2D chứa các ô
    private final Random random;
    private final GamePanel gamePanel;

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[SIZE][SIZE];
        random = new Random();
        initializeTiles();
    }

    // Khởi tạo các ô trên bảng
    protected void initializeTiles() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = new Tile();
            }
        }
        addRandomTile(); // Thêm 2 ô ngẫu nhiên ban đầu
        addRandomTile();
    }

    // Trả về ô tại vị trí (i, j)
    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    // Thêm một ô ngẫu nhiên có giá trị 2 hoặc 4
    public void addRandomTile() {
        if (!hasEmptyTile()) {
            return; // Không còn ô trống để thêm
        }

        int value = random.nextDouble() < 0.9 ? 2 : 4; // 90% là 2, 10% là 4
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (tiles[x][y].getValue() != 0); // Tìm ô trống
        tiles[x][y].setValue(value);
    }

    // Kiểm tra xem còn ô trống không
    private boolean hasEmptyTile() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].getValue() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Di chuyển các ô theo hướng chỉ định
    public boolean moveTiles(Direction direction) {
        boolean moved = switch (direction) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        };
        if (moved) {
            addRandomTile();
            updateBoard();
        }
        return moved;
    }

    // Di chuyển các ô lên trên
    private boolean moveUp() {
        boolean moved = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (tiles[i][j].getValue() != 0) {
                    int k = i;
                    while (k > 0 && tiles[k - 1][j].getValue() == 0) {
                        // Di chuyển ô lên trên
                        tiles[k - 1][j].setValue(tiles[k][j].getValue());
                        tiles[k][j].setValue(0);
                        k--;
                        moved = true;
                    }
                    if (k > 0 && tiles[k - 1][j].getValue() == tiles[k][j].getValue()) {
                        // Kết hợp hai ô có cùng giá trị
                        tiles[k - 1][j].setValue(tiles[k - 1][j].getValue() * 2);
                        tiles[k][j].setValue(0);
                        gamePanel.updateScore(tiles[k - 1][j].getValue()); // Cập nhật điểm số
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    // Di chuyển các ô xuống dưới
    private boolean moveDown() {
        boolean moved = false;
        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (tiles[i][j].getValue() != 0) {
                    int k = i;
                    while (k < SIZE - 1 && tiles[k + 1][j].getValue() == 0) {
                        // Di chuyển ô xuống dưới
                        tiles[k + 1][j].setValue(tiles[k][j].getValue());
                        tiles[k][j].setValue(0);
                        k++;
                        moved = true;
                    }
                    if (k < SIZE - 1 && tiles[k + 1][j].getValue() == tiles[k][j].getValue()) {
                        // Kết hợp hai ô có cùng giá trị
                        tiles[k + 1][j].setValue(tiles[k + 1][j].getValue() * 2);
                        tiles[k][j].setValue(0);
                        gamePanel.updateScore(tiles[k + 1][j].getValue()); // Cập nhật điểm số
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    // Di chuyển các ô sang trái
    private boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (tiles[i][j].getValue() != 0) {
                    int k = j;
                    while (k > 0 && tiles[i][k - 1].getValue() == 0) {
                        // Di chuyển ô sang trái
                        tiles[i][k - 1].setValue(tiles[i][k].getValue());
                        tiles[i][k].setValue(0);
                        k--;
                        moved = true;
                    }
                    if (k > 0 && tiles[i][k - 1].getValue() == tiles[i][k].getValue()) {
                        // Kết hợp hai ô có cùng giá trị
                        tiles[i][k - 1].setValue(tiles[i][k - 1].getValue() * 2);
                        tiles[i][k].setValue(0);
                        gamePanel.updateScore(tiles[i][k - 1].getValue()); // Cập nhật điểm số
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    // Di chuyển các ô sang phải
    private boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (tiles[i][j].getValue() != 0) {
                    int k = j;
                    while (k < SIZE - 1 && tiles[i][k + 1].getValue() == 0) {
                        // Di chuyển ô sang phải
                        tiles[i][k + 1].setValue(tiles[i][k].getValue());
                        tiles[i][k].setValue(0);
                        k++;
                        moved = true;
                    }
                    if (k < SIZE - 1 && tiles[i][k + 1].getValue() == tiles[i][k].getValue()) {
                        // Kết hợp hai ô có cùng giá trị
                        tiles[i][k + 1].setValue(tiles[i][k + 1].getValue() * 2);
                        tiles[i][k].setValue(0);
                        gamePanel.updateScore(tiles[i][k + 1].getValue()); // Cập nhật điểm số
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    // Kiểm tra xem có thể di chuyển được không
    public boolean canMove() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].getValue() == 0) {
                    return true; // Còn ô trống
                }
                if (i < SIZE - 1 && tiles[i][j].getValue() == tiles[i + 1][j].getValue()) {
                    return true; // Có thể kết hợp theo chiều dọc
                }
                if (j < SIZE - 1 && tiles[i][j].getValue() == tiles[i][j + 1].getValue()) {
                    return true; // Có thể kết hợp theo chiều ngang
                }
            }
        }
        return false; // Không thể di chuyển
    }

    // Cập nhật giao diện của tất cả các ô
    public void updateBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j].updateLabel();
            }
        }
    }
}