package com.metro_pos.Utils;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

public class CustomInputBox extends JTextField {
    private CustomInputBox(Builder builder) {
        super(builder.columns); // Setting the number of columns

        // Set additional properties
        if (builder.size != null) {
            this.setPreferredSize(builder.size);
        } else {
            this.setPreferredSize(new Dimension(200, 30)); // Default size
        }
        if (builder.text != null) {
            this.setText(builder.text);
        }
        if (builder.color != null) {
            this.setBackground(builder.color);
        }
        if (builder.bounds != null) {
            this.setBounds(builder.bounds);
        }
    }

    public static class Builder {
        private final int columns; // Mandatory field
        private Dimension size; // Optional field
        private String text; // Optional field
        private Color color; // Optional field
        private Rectangle bounds; // Optional field

        public Builder(int columns) {
            this.columns = columns;
        }

        public Builder setSize(Dimension size) {
            this.size = size;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder setBounds(int x, int y, int width, int height) {
            this.bounds = new Rectangle(x, y, width, height);
            return this;
        }

        public CustomInputBox build() {
            return new CustomInputBox(this);
        }
    }
}
