import com.config;

import javax.swing.*;

 class Card {
     /**
      * @oreder the number of a card
      * @isPeng check if a card can peng (AAA:111,222,333,)
      * @isGang check if a card can Gang (AAAA:1111,2222,3333)
      * @isEaten check if a card can be eaten(ABC:123,234,345)
      */
    private int order;
    private int type;
    private boolean isPeng;
    private boolean isGang;
    private boolean isEaten;
    private ImageIcon imageIcon;

    Card() {
        type = 1000;
        order = 0;
        isGang = false;
        isPeng = false;
        isEaten = false;
        imageIcon = config.IMAGE;
    }

    int getOrder() {
        return order;
    }

    int getType() {
        return type;
    }

    ImageIcon getImageIcon() {
        return imageIcon;
    }

    boolean is_Peng() {
        return isPeng;
    }

    boolean is_Gang() {
        return isGang;
    }

    boolean is_Eaten() {
        return isEaten;
    }

   static boolean isTheSameCard(Card card1, Card card2) {
        return card1.getOrder() == card2.getOrder() && card1.getType() == card2.getType();
    }

    void setPeng(boolean b) {
        isPeng = b;
    }

    void setGang(boolean b) {
        isGang = b;
    }

    void setEaten(boolean b) {
        isEaten = b;
    }

    void setImageIcon(String image) {
        imageIcon = new ImageIcon(image);
    }

    void setOrder(int i) {
        order = i;
    }

    void setType(int i) {
        type = i;
    }

    static void sortCards(Player[] players, int n) {
        Card temp;
        int array[] = new int[19];
        for (int i = 1; i <= 18; i++) {
            if (players[n].cardsHaving[i].is_Gang() || players[n].cardsHaving[i].is_Peng()) array[i] = 1;
            else array[i] = 0;
        }
        for (int i = 18; i >= 2; i--) {
            for (int j = 1; j < i; j++) {
                temp = players[n].cardsHaving[j];
                int a = array[j];
                if (players[n].cardsHaving[j + 1].getType() * 10 + array[j + 1] * 100 + players[n].cardsHaving[j + 1].getOrder()
                        < array[j] * 100 + players[n].cardsHaving[j].getType() * 10 + players[n].cardsHaving[j].getOrder()) {
                    players[n].cardsHaving[j] = players[n].cardsHaving[j + 1];
                    players[n].cardsHaving[j + 1] = temp;
                    array[j] = array[j + 1];
                    array[j + 1] = a;
                }
            }
        }
    }
}
