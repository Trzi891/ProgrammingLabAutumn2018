import com.config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MyPanel extends JFrame {

    JButton help;
    boolean isStarting = false;

    JButton jb3;
    JPanel myPanel;

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(config.BACKGROUND.getImage(), 0, 0, 1200, 800, null);
        graphics.drawImage(config.HELP.getImage(), 21, 55, 72, 40, null);
    }

    MyPanel() {
        jb3 = new JButton(config.GS);
        help = new JButton(config.HELP);
        myPanel = new JPanel();

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
                jb3.setVisible(false);
                dispose();

            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpButton();
            }
        });

        this.setLayout(null);
        myPanel.setLayout(null);
        myPanel.add(jb3);

        this.add(help);
        this.add(myPanel);
        help.setBounds(10, 10, 72, 40);
        jb3.setBounds(200, 550, 200, 60);
        myPanel.setBounds(0, 0, config.WIDTH_BACKGROUND, config.HEIGHT_BACKGROUND);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(40, 40, config.WIDTH_BACKGROUND, config.HEIGHT_BACKGROUND);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


}
