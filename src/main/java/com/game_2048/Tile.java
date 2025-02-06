package com.game_2048;

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {
    private int value;

    public Tile() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 24));
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
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
        if (value == 0) {
            setText("");
            setBackground(new Color(0xcdc1b4)); // Màu nền cho ô trống
        } else {
            setText(String.valueOf(value));
            setBackground(getTileColor(value));
            setForeground(value > 4 ? new Color(0xf9f6f2) : new Color(0x776e65)); // Màu chữ
        }
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
            default -> new Color(0x3c3a32);
        };
    }
}