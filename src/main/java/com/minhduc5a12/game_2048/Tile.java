package com.minhduc5a12.game_2048;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private int value;

    public Tile() {
        setOpaque(false); // Đặt nền trong suốt để bo tròn hiển thị đúng
        setPreferredSize(new Dimension(100, 100)); // Kích thước của ô
        setFont(new Font("Arial", Font.BOLD, 24));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding
        value = 0;
        updateLabel();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateLabel();
    }

    public void updateLabel() {
        repaint(); // Yêu cầu vẽ lại ô
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Bo tròn góc với bán kính 15
        int arcWidth = 15;
        int arcHeight = 15;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Làm mịn cạnh
        g2d.setColor(getTileColor(value)); // Màu nền của ô
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight); // Vẽ hình chữ nhật bo tròn

        // Vẽ giá trị của ô
        if (value != 0) {
            g2d.setColor(value > 4 ? new Color(0xf9f6f2) : new Color(0x776e65)); // Màu chữ
            FontMetrics fm = g2d.getFontMetrics();
            String text = String.valueOf(value);
            int x = (getWidth() - fm.stringWidth(text)) / 2; // Căn giữa theo chiều ngang
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent(); // Căn giữa theo chiều dọc
            g2d.drawString(text, x, y);
        }

        g2d.dispose();
    }

    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> new Color(0xeee4da);
            case 4 -> new Color(0xede0c8);
            case 8 -> new Color(0xf2b179);
            case 16 -> new Color(0xf59563);
            case 32 -> new Color(0xf67c5f);
            case 64 -> new Color(0xf65e3b);
            case 128 -> new Color(0xedcf72);
            case 256 -> new Color(0xedcc61);
            case 512 -> new Color(0xedc850);
            case 1024 -> new Color(0xedc53f);
            case 2048 -> new Color(0xedc22e);
            default -> new Color(0xcdc1b4); // Màu nền cho ô trống
        };
    }
}