package com.game_2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
    public StyledButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 18));
        setBackground(new Color(0x8f7a66)); // Màu nền mặc định
        setForeground(Color.WHITE); // Màu chữ
        setFocusPainted(false); // Loại bỏ viền focus
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Con trỏ tay khi hover
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding cho nút

        // Thêm hiệu ứng hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0x7b6a58)); // Màu nền khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(0x8f7a66)); // Màu nền mặc định
            }
        });
    }
}