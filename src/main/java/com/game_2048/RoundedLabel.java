package com.game_2048;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {
    private int cornerRadius = 15; // Bán kính bo tròn
    private Color backgroundColor; // Màu nền của label

    public RoundedLabel(String text, Color backgroundColor) {
        super(text);
        setBackgroundColor(backgroundColor);
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(new Color(0x9f8975));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Thêm padding
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Làm mịn cạnh

        // Vẽ nền nếu màu nền được cung cấp
        if (backgroundColor != null) {
            g2d.setColor(backgroundColor);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius); // Vẽ hình chữ nhật bo tròn
        }

        // Vẽ viền bo tròn
        g2d.setColor(new Color(0x9e8c7b)); // Màu viền
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius); // Vẽ viền

        super.paintComponent(g2d);
        g2d.dispose();
    }
}