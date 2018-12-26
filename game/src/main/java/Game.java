import com.config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Game implements ActionListener {
    private GamingFrame frame = new GamingFrame();

    private Card cards[] = new Card[109];
    private HuUtil huUtil = new HuUtil();
    private int currentCard;
    private int player;
    private int previousClick = -1;
    private Card cardOfPlayer = null;

    private boolean isOperable = false;
    private boolean isOperableToo = false;

    private Player competitors[] = new Player[6];
    private Card choosedCard = new Card();

    Game() {
        addListener();
        init();
        ruffle();
        deal();
        playerPlays(competitors, 0, false);
        currentCard = 53;
        player = (int) (Math.random() * 100) % 4;
        for (int i = 0; i < 4; i++) Card.sortCards(competitors, i);
        starting();
    }

    private void init() {

        for (int i = 0; i <= 108; i++) cards[i] = new Card();
        for (int i = 0; i <= 5; i++) competitors[i] = new Player();
        for (int i = 0; i <= 108; i++) {
            cards[i].setOrder(i % 9 + 1);
            if (i < 36) cards[i].setType(config.TONG);
            else if (i <= 72) cards[i].setType(config.TIAO);
            else cards[i].setType(config.WAN);
            cards[i].setGang(false);
            cards[i].setPeng(false);
            String string = "images/";
            String s="";
            string = string + (i % 9 + 1);
            if (cards[i].getType() == config.TONG) string = string + "tong.png";
            else if (cards[i].getType() == config.TIAO) string = string + "tiao.png";
            else string = string + "wan.png";
            /*try {
                RotateImage.getImage(string,"270right",270);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            cards[i].setImageIcon(string);
        }
    }

    private void deal() {
        for (int i = 1; i <= 13; i++) {
            competitors[0].cardsHaving[i] = cards[i];
            competitors[1].cardsHaving[i] = cards[i + 13];
            competitors[2].cardsHaving[i] = cards[i + 26];
            competitors[3].cardsHaving[i] = cards[i + 39];
        }
        for (int i = 0; i < 4; i++)
            for (int j = 14; j <= 18; j++)
                competitors[i].cardsHaving[j].setType(1000);
    }

    private void ruffle() {
        Card temp;
        for (int i = 1; i < 109; i++) {
            int random = (int) ((Math.random() * 10000) % (109 - i) + i);
            temp = cards[i];
            cards[i] = cards[random];
            cards[random] = temp;
        }
    }

    private Card getCard() {
        if (currentCard == 109) frame.deuce();
        int i = currentCard;
        currentCard++;
        return cards[i];
    }

    private void playerPlays(Player[] players, int n, boolean b) {
        isOperable = b;
        for (int i = 0; i < 14; i++) {
            frame.jButton[i].setVisible(false);
            frame.jLabelOfPlayer[i].setVisible(false);
        }
        int numberOfJbutton = 0, numberOfJlabel = 0;
        for (int i = 1; i < 18; i++) {
            if ((players[n].cardsHaving[i].is_Peng() || players[n].cardsHaving[i].is_Gang()) && players[n].cardsHaving[i].getType() < 4) {
                frame.jLabelOfPlayer[numberOfJlabel].setIcon(competitors[0].cardsHaving[i].getImageIcon());
                frame.jLabelOfPlayer[numberOfJlabel++].setVisible(true);
                this.frame.show(true);
            } else if (players[n].cardsHaving[i].getType() < 4) {
                frame.jButton[numberOfJbutton].setIcon(competitors[0].cardsHaving[i].getImageIcon());
                frame.myCards[numberOfJbutton] = competitors[0].cardsHaving[i].getImageIcon();
                frame.jButton[numberOfJbutton++].setVisible(true);
                this.frame.show(true);
            }
        }
        int array[] = new int[19];
        boolean b1 = HuUtil.isHu(competitors, player, array);
        if (b1 && isOperableToo) {
            frame.jbZhiMo.setVisible(true);
            frame.jbJump.setVisible(true);
            isOperableToo = true;
            this.frame.show(true);
            return;
        }
        if (factory.isAnGang(competitors[0]) != -1 && !isOperableToo) {
            frame.jbGang.setVisible(true);
            frame.jbJump.setVisible(true);
            isOperableToo = true;
            this.frame.show(true);
            return;
        }
        isOperableToo = false;
    }

    private void robotPlays(Player[] players, int n) {
        Card.sortCards(players, n);
        int array[] = new int[19];
        if (HuUtil.isHu(players, n, array)) frame.robotWins(n, n);
        else {
            int gang = factory.isAnGang(players[n]);
            if (gang != -1) {
                for (int i = 0; i <= 3; i++) players[n].cardsHaving[gang + i].setGang(true);
                players[n].cardsHaving[18] = getCard();
                robotPlays(players, n);
                return;
            }
            for (int i = 1; i <= 18; i++) {
                if (array[i] == 0 && players[n].cardsHaving[i].getType() <= 3) {
                    if ((Card.isTheSameCard(players[n].cardsHaving[i], players[n].cardsHaving[i + 1]))
                            || (players[n].cardsHaving[i].getOrder() + 1 == players[n].cardsHaving[i + 1].getOrder()
                            && players[n].cardsHaving[i].getType() == players[n].cardsHaving[i + 1].getType())
                            || (players[n].cardsHaving[i].getOrder() + 2 == players[n].cardsHaving[i + 1].getOrder()
                            && players[n].cardsHaving[i].getType() == players[n].cardsHaving[i + 1].getType())) {
                        array[i]--;
                        if (array[i + 1] == 0) array[i + 1]--;
                    }
                }
            }
            int transfer = 0, sum = -55;
            for (int i = 1; i <= 18; i++) {
                if (array[i] == 0 && players[n].cardsHaving[i].getType() <= 3) {
                    transfer = i;
                    break;
                }
                if (array[i] == 1) ;
                else {
                    if (array[i] > sum && players[n].cardsHaving[i].getType() <= 3) {
                        sum = array[i];
                        transfer = i;
                    }
                }
            }
            cardOfPlayer = players[n].cardsHaving[transfer];
            players[n].cardsHaving[transfer] = choosedCard;
            Card.sortCards(players, n);
            if (n == 1) {
                frame.previousCardOfRight = cardOfPlayer.getImageIcon();
//
            } else if (n == 2) {
                frame.previousCardOfMiddle = cardOfPlayer.getImageIcon();
            } else if (n == 3) {
                frame.previousCardOfLeft = cardOfPlayer.getImageIcon();
            }
        }
    }

    private void show1() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 18; j++) {
                if (competitors[i].cardsHaving[j].getType() == 1)
                    System.out.print(competitors[i].cardsHaving[j].getOrder() + "筒  ");
                else if (competitors[i].cardsHaving[j].getType() == 2)
                    System.out.print(competitors[i].cardsHaving[j].getOrder() + "条  ");
                else if (competitors[i].cardsHaving[j].getType() == 3)
                    System.out.print(competitors[i].cardsHaving[j].getOrder() + "万 ");
            }
            System.out.println();
        }
    }

    private void starting() {
        if (cardOfPlayer == null) {
            competitors[player].cardsHaving[18] = getCard();
            Card.sortCards(competitors, player);
            show1();
            System.out.println();
            if (player == 0) {
                playerPlays(competitors, 0, true);
                return;
            } else {
                robotPlays(competitors, player);
            }
        }
        if (cardOfPlayer != null && cardOfPlayer.getType() < 4) {
            int address;
            if ((address = factory.isCurrentWin(competitors, (player + 1) % 4, cardOfPlayer)) != -1) {
                if (address == 0) {
                    frame.jbHu.setVisible(true);
                    frame.jbJump.setVisible(true);
                    this.frame.show(true);
                    return;
                } else {
                    System.arraycopy(competitors[5].cardsHaving, 1, competitors[address].cardsHaving, 1, 18);
                    frame.robotWins(address, player);
                    return;
                }
            } else if ((address = factory.isCurrentCardGang(competitors, (player + 1), cardOfPlayer)) != -1) {
                if (address == 0) {
                    frame.jbGang.setVisible(true);
                    frame.jbPeng.setVisible(true);
                    frame.jbJump.setVisible(true);
                    this.frame.show(true);
                    return;
                } else {
                    competitors[address].cardsHaving[18] = cardOfPlayer;
                    Card.sortCards(competitors, address);
                    int i = factory.isAnGang(competitors[address]);
                    for (int j = 0; j <= 3; j++) competitors[address].cardsHaving[i + j].setGang(true);
                    player = address;
                    cardOfPlayer = null;
                    starting();
                    return;
                }
            } else if ((address = factory.isCurrentCardPeng(competitors, (player + 1), cardOfPlayer)) != -1) {
                if (address == 0) {
                    frame.jbPeng.setVisible(true);
                    frame.jbJump.setVisible(true);
                    this.frame.show(true);
                    return;
                } else {
                    int suma = 0, sumb = 0, array[] = new int[19];
                    Card.sortCards(competitors, address);
                    HuUtil.isHu(competitors, address, array);
                    for (int i = 1; i <= 18; i++) {
                        if (array[i] == 1) suma++;
                    }
                    System.arraycopy(competitors[address].cardsHaving, 1, competitors[5].cardsHaving, 1, 18);
                    competitors[5].cardsHaving[17] = cardOfPlayer;
                    competitors[5].cardsHaving[18] = cardOfPlayer;
                    Card.sortCards(competitors, 5);
                    int num = factory.isAnGang(competitors[5]);
                    HuUtil.isHu(competitors, 5, array);
                    for (int i = 1; i <= 18; i++) {
                        if (array[i] == 1) sumb++;
                    }
                    if (suma >= sumb) ;
                    else {
                        competitors[address].cardsHaving[18] = cardOfPlayer;
                        Card.sortCards(competitors, address);
                        for (int i = 0; i <= 2; i++) competitors[address].cardsHaving[i + num].setPeng(true);
                        player = address;
                        robotPlays(competitors, address);
                        starting();
                        return;
                    }
                }
            }
            cardOfPlayer = null;
            player = (player + 1) % 4;
            starting();
        }
    }


    private void addListener() {
        for (int i = 1; i <= 14; i++) {
            frame.jButton[i - 1].addActionListener(this);
        }
        frame.jbYes.addActionListener(this);
        frame.jbGang.addActionListener(this);
        frame.jbHu.addActionListener(this);
        frame.jbZhiMo.addActionListener(this);
        frame.jbJump.addActionListener(this);
        frame.jbPeng.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.jbPeng) {
            frame.jbPeng.setVisible(false);
            frame.jbJump.setVisible(false);
            this.frame.show(true);
            System.arraycopy(competitors[0].cardsHaving, 1, competitors[5].cardsHaving, 1, 18);
            competitors[5].cardsHaving[17] = cardOfPlayer;
            competitors[5].cardsHaving[18] = cardOfPlayer;
            Card.sortCards(competitors, 5);
            int i = factory.isAnGang(competitors[5]);
            competitors[0].cardsHaving[18] = cardOfPlayer;
            for (int j = 0; j <= 2; j++)
                competitors[0].cardsHaving[i + j].setPeng(true);
            player = 0;
            Card.sortCards(competitors, 0);
            playerPlays(competitors, 0, true);
        }
        if (e.getSource() == frame.jbJump) {
            frame.verbsOFMJ(false, false, false, false, false);
            this.frame.show(true);
            if (isOperableToo) playerPlays(competitors, 0, isOperableToo);
            else {
                cardOfPlayer = null;
                starting();
            }
        }
        if (e.getSource() == frame.jbGang) {
            frame.jbGang.setVisible(false);
            frame.jbPeng.setVisible(false);
            frame.jbJump.setVisible(false);
            this.frame.show(true);
            System.arraycopy(competitors[0].cardsHaving, 1, competitors[5].cardsHaving, 1, 18);
            if (cardOfPlayer != null) competitors[5].cardsHaving[18] = cardOfPlayer;
            Card.sortCards(competitors, 5);
            int i = factory.isAnGang(competitors[5]);
            competitors[0].cardsHaving[18] = cardOfPlayer;
            Card.sortCards(competitors, 0);
            for (int a = 0; a <= 3; a++)
                competitors[0].cardsHaving[i + a].setGang(true);
            player = 0;
            cardOfPlayer = null;
            Card.sortCards(competitors, 0);
            starting();
        }
        if (e.getSource() == frame.jbHu) {
            frame.verbsOFMJ(false, false, false, false, false);
            frame.playerWins(player);
        }

        if (e.getSource() == frame.jbZhiMo) {
            frame.verbsOFMJ(false, false, false, false, false);
            frame.playerWins(0);
        }

        if (isOperable && e.getSource() == frame.jbYes && previousClick != -1) {
            cardOfPlayer = (competitors[0].cardsHaving[previousClick + 1]);
            competitors[0].cardsHaving[previousClick + 1] = choosedCard;
            frame.jButton[previousClick].setBounds(160 + 55 * (previousClick + 1), 600, config.WIDTH_NORMAL_CARD, config.HEIGHT_NORMAL_CARD);
            frame.previousCardOfPlayer = cardOfPlayer.getImageIcon();
            frame.negtive = cardOfPlayer.getImageIcon();
            frame.repaint();
            Card.sortCards(competitors, 0);
            playerPlays(competitors, 0, false);
            isOperable = false;
            starting();

        }
        for (int i = 0; i < 14; i++) {
            if (frame.jButton[i] != null && e.getSource() == frame.jButton[i]) {
                if (previousClick != -1)
                    frame.jButton[previousClick].setBounds(160 + config.WIDTH_NORMAL_CARD * (previousClick + 1), 600, config.WIDTH_NORMAL_CARD, config.HEIGHT_NORMAL_CARD);
                frame.jButton[i].setBounds(164 + config.WIDTH_NORMAL_CARD * (i + 1), 590, 50, 75);
                previousClick = i;
            }
        }
        this.frame.show(true);
    }


}
