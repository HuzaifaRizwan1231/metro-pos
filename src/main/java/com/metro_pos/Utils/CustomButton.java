package com.metro_pos.Utils;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class CustomButton extends JButton {
    private CustomButton(Builder builder) {
        super(builder.text); // Setting the text
        // Set additional properties
        if (builder.toolTipText != null) {
            this.setToolTipText(builder.toolTipText);
        }
        if (builder.size != null) {
            this.setSize(builder.size);
        } else {
            this.setSize(new Dimension(100, 50)); // Default size
        }
        if (builder.color != null) {
            this.setBackground(builder.color);
        } else {
            this.setBackground(Color.LIGHT_GRAY); // Default color
        }
        this.setEnabled(builder.enabled == null || builder.enabled); // Default enabled to true if not set
    }

    public static class Builder {
        private final String text; // Mandatory field
        private String toolTipText; // Optional field
        private Boolean enabled; // Optional field
        private Dimension size; // Optional field
        private Color color; // Optional field

        public Builder(String text) {
            this.text = text;
        }

        public Builder setToolTipText(String toolTipText) {
            this.toolTipText = toolTipText;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setSize(Dimension size) {
            this.size = size;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        // Add more setter methods for other properties

        public CustomButton build() {
            return new CustomButton(this);
        }
    }
}
