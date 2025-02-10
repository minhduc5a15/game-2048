package com.game_2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
    private int cornerRadius = 15; // Bán kính bo tròn
    private final boolean rounded; // Biến kiểm soát việc bo tròn

    // Constructor với tham số rounded
    public StyledButton(String text, boolean rounded) {
        super(text);
        this.rounded = rounded; // Thiết lập có bo tròn hay không
        setFont(new Font("Arial", Font.BOLD, 18));
        setBackground(new Color(0x8f7a66)); // Màu nền mặc định
        setForeground(Color.WHITE); // Màu chữ
        setFocusPainted(false); // Loại bỏ viền focus
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Con trỏ tay khi hover
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding cho nút
        setContentAreaFilled(false); // Đặt nền trong suốt để tự vẽ nền bo tròn

        // Thêm hiệu ứng hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0x7b6a58)); // Màu nền khi hover
                repaint(); // Yêu cầu vẽ lại nút
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(0x8f7a66)); // Màu nền mặc định
                repaint(); // Yêu cầu vẽ lại nút
            }
        });
    }

    // Constructor mặc định (không bo tròn)
    public StyledButton(String text) {
        this(text, false); // Gọi constructor chính với rounded = false
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Làm mịn cạnh

        // Vẽ nền
        g2d.setColor(getBackground());
        if (rounded) {
            // Vẽ nền bo tròn nếu rounded = true
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        } else {
            // Vẽ nền hình chữ nhật nếu rounded = false
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Vẽ viền
        g2d.setColor(new Color(0x9e8c7b)); // Màu viền
        if (rounded) {
            // Vẽ viền bo tròn nếu rounded = true
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        } else {
            // Vẽ viền hình chữ nhật nếu rounded = false
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        // Vẽ text
        g2d.setColor(getForeground());
        FontMetrics fm = g2d.getFontMetrics();
        String text = getText();
        int x = (getWidth() - fm.stringWidth(text)) / 2; // Căn giữa theo chiều ngang
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent(); // Căn giữa theo chiều dọc
        g2d.drawString(text, x, y);

        g2d.dispose();
    }
}