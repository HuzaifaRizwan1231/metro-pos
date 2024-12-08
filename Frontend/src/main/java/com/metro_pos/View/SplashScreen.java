package com.metro_pos.View;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    private static final int SPLASH_WIDTH = 400;
    private static final int SPLASH_HEIGHT = 300;

    public SplashScreen() {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int width = SPLASH_WIDTH;
        int height = SPLASH_HEIGHT;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Correct path to your image file
        String imagePath = "Frontend\\src\\main\\java\\com\\metro_pos\\Images\\OIP.jpg";
        ImageIcon originalIcon = new ImageIcon(imagePath);

        // Resize the image
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Add the resized image to the label
        JLabel label = new JLabel(resizedIcon);
        JLabel copyrt = new JLabel("Copyright 2024, Metro POS", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color border = new Color(156, 20, 20, 255);
        content.setBorder(BorderFactory.createLineBorder(border, 10));

        // Display it
        setVisible(true);

        // Pause for a brief period
        try {
            Thread.sleep(5000); // Display for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
    }


}
