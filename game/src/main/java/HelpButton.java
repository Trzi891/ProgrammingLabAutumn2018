import com.config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class HelpButton extends JFrame {
    HelpButton() {
        this.setLayout(null);
        JLabel jlHelp = new JLabel("<html><body><p align=\\\"center\\\">There are more than 88 ways to play majiang<br/>" +
                "in this game, we have tiao, tong, wan.<br/>" +
                "Without feng(windy), dong, xi, nan, bei, laizi,and so on.<br/>" +
                "We can peng, gang, zhi mo, fang pao, can check if cards are<br/>" +
                "<br/>dragon seven couple, jiang card, gua feng(windy)" +
                "<br/>xia yu(rainy)...." +
                "<br/>you can click it and see how to play it in other ways</p></body></html>");
        JPanel jpHelp = new JPanel();
        JButton click = new JButton(config.CLICK_ME);
        jpHelp.add(jlHelp);
        jlHelp.setBounds(0,0,500,250);
        jpHelp.setBounds(10, 5, 500, 300);
        click.setBounds(170, 200, 150, 30);
        this.add(click);//要加在frame上，加在panel上的话b显示不出来
        click.setVisible(true);
        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpOpen();
            }
        });
        this.add(jpHelp);
        this.setBounds(10, 0, 500, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
