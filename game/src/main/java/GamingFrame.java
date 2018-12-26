import com.config;

import javax.swing.*;
import java.awt.*;

public class GamingFrame extends MyPanel {

    JButton jButton[] = new JButton[14];
    JLabel jLabelOfPlayer[] = new JLabel[14];

    JLabel win = new JLabel("");
    JPanel jPanelOfWin = new JPanel();

    /**
     * verbs of majiang
     */
    JButton jbPeng = new JButton("peng");
    JButton jbGang = new JButton("gang");
    JButton jbHu = new JButton("hu");
    JButton jbZhiMo = new JButton("zhi mo");
    JButton jbJump = new JButton("jump");
    JButton jbYes = new JButton(config.YES_BUTTON);

    GamingFrame() {
        this.setTitle("majiang");
        this.jb3.setVisible(false);
        this.setLayout(null);
        jPanelOfWin.setLayout(null);
        jPanelOfWin.setVisible(false);
        jPanelOfWin.add(win);
        jPanelOfWin.setBounds(20, 20, 200, 200);

        for (int i = 1; i <= 14; i++) {
            jButton[i - 1] = new JButton("");
            jLabelOfPlayer[i - 1] = new JLabel("");
            jLabelOfPlayer[i - 1].setBounds(160 + config.WIDTH_NORMAL_CARD * i, 600, config.WIDTH_NORMAL_CARD, config.HEIGHT_NORMAL_CARD);
            jButton[i - 1].setBounds(160 + config.WIDTH_NORMAL_CARD * i, 600, config.WIDTH_NORMAL_CARD, config.HEIGHT_NORMAL_CARD);
            jButton[i - 1].setBorderPainted(false);
            jButton[i - 1].setVisible(true);
            jLabelOfPlayer[i - 1].setVisible(true);
            this.myPanel.add(jLabelOfPlayer[i - 1]);
            this.myPanel.add(jButton[i - 1]);
        }

        jbYes.setBounds(1050, 540, 90, 45);
        this.add(jbYes);
        this.add(jPanelOfWin);
        jbGang.setBounds(config.X_VERBS, config.Y_VERBS, config.WIDTH_VERBS, config.HEIGHT_VERBS);
        jbHu.setBounds(config.X_VERBS + 50, config.Y_VERBS, config.WIDTH_VERBS, config.HEIGHT_VERBS);
        jbZhiMo.setBounds(config.X_VERBS + 100, config.Y_VERBS, config.WIDTH_VERBS, config.HEIGHT_VERBS);
        jbPeng.setBounds(config.X_VERBS + 100, config.Y_VERBS, config.WIDTH_VERBS, config.HEIGHT_VERBS);
        jbJump.setBounds(config.X_VERBS + 200, config.Y_VERBS, config.WIDTH_VERBS, config.HEIGHT_VERBS);
        verbsOFMJ(false, false, false, false, false);
        this.add(jbGang);
        this.add(jbHu);
        this.add(jbZhiMo);
        this.add(jbPeng);
        this.add(jbJump);

        this.setVisible(true);
    }

    ImageIcon negtive = new ImageIcon();
    ImageIcon previousCardOfRight = new ImageIcon();
    ImageIcon previousCardOfMiddle = new ImageIcon();
    ImageIcon previousCardOfLeft = new ImageIcon();
    ImageIcon previousCardOfPlayer = new ImageIcon();
    ImageIcon[] myCards = new ImageIcon[14];

    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawImage(config.BACKGROUND1.getImage(), 0, 0, config.WIDTH_BACKGROUND, config.HEIGHT_BACKGROUND, null);
        for (int i = 1; i <= 13; i++) {
            graphics.drawImage(config.LEFT_NORMAL.getImage(), 80, 100 + 30 * i, 30, 60, null);
            graphics.drawImage(config.RIGHT_NORMAL.getImage(), 1080, 100 + 30 * i, 30, 60, null);
            graphics.drawImage(config.MIDDLE_NORMAL.getImage(), 250 + 45 * i, 60, 44, 72, null);
        }
        graphics.drawImage(negtive.getImage(), 530, 363, config.WIDTH_PREVIOUS_CARD + 10, config.HEIGHT_PREVIOUS_CARD + 10, null, null);
        graphics.drawImage(previousCardOfRight.getImage(), 1000, 143, config.WIDTH_PREVIOUS_CARD, config.HEIGHT_PREVIOUS_CARD, null, null);
        graphics.drawImage(previousCardOfMiddle.getImage(), 250, 128, config.WIDTH_PREVIOUS_CARD, config.HEIGHT_PREVIOUS_CARD, null, null);
        graphics.drawImage(previousCardOfLeft.getImage(), 150, 443, config.WIDTH_PREVIOUS_CARD, config.HEIGHT_PREVIOUS_CARD, null, null);
        graphics.drawImage(previousCardOfPlayer.getImage(), 900, 543, config.WIDTH_PREVIOUS_CARD, config.HEIGHT_PREVIOUS_CARD, null, null);
        for (int i = 0; i <= 13; i++) {
            graphics.drawImage(myCards[i].getImage(),225 + config.WIDTH_NORMAL_CARD * i, 643, config.WIDTH_NORMAL_CARD, config.HEIGHT_NORMAL_CARD,null,null);
        }

        graphics.drawImage(config.YES_BUTTON.getImage(), 1060, 585, 90, 45, null, null);
        graphics.drawImage(config.HELP.getImage(), 21, 55, 72, 40, null);

    }

    void verbsOFMJ(boolean peng, boolean gang, boolean hu, boolean zhimo, boolean jump) {
        jbPeng.setVisible(peng);
        jbJump.setVisible(jump);
        jbZhiMo.setVisible(zhimo);
        jbHu.setVisible(hu);
        jbGang.setVisible(gang);
    }

    void robotWins(int player, int c) {
        if (player == c) win.setText("Player" + player + "wins, he ZHIMO");
        else if (c != 0) win.setText("Player" + player + "wins, player" + (c + 1) + "FANGPAO");
        else win.setText("Player" + player + "wins, you FANGPAO");
        jPanelOfWin.add(win);
        jPanelOfWin.setVisible(true);
        this.add(jPanelOfWin);
        this.setBounds(config.X_WIN_PANEL, config.Y_WIN_PANEL, config.WIDTH_WIN_PANEL, config.HEIGHT_WIN_PANEL);
        jPanelOfWin.setVisible(false);
    }

    void playerWins(int player) {
        if (player == 0) win.setText("YOU WIN! YOU ZHIMO");
        else win.setText("YOU WIN! PLAYER" + (player + 1) + "FANGPAO");
        jPanelOfWin.add(win);
        jPanelOfWin.setVisible(true);
        this.add(jPanelOfWin);
        this.setBounds(config.X_WIN_PANEL, config.Y_WIN_PANEL, config.WIDTH_WIN_PANEL, config.HEIGHT_WIN_PANEL);
        jPanelOfWin.setVisible(false);
    }

    void deuce() {
        win.setText("A DEAD HEAT");
        jPanelOfWin.add(win);
        jPanelOfWin.setVisible(true);
        this.add(jPanelOfWin);
        this.setBounds(config.X_WIN_PANEL, config.Y_WIN_PANEL, config.WIDTH_WIN_PANEL, config.HEIGHT_WIN_PANEL);
        jPanelOfWin.setVisible(false);
    }

}
