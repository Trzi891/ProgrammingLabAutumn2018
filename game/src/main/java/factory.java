import com.config;

public class factory {
    static boolean isPeng(Player[] players, int n, Card card) {
        for (int i = 1; i <= 17; i++) {
            if (players[n].cardsHaving[i].getType() == card.getType()
                    && players[n].cardsHaving[i].getOrder() == card.getOrder()
                    && players[n].cardsHaving[i + 1].getType() == card.getType()
                    && players[n].cardsHaving[i + 1].getOrder() == card.getOrder()) {
                if (players[n].cardsHaving[i].is_Peng() && players[n].cardsHaving[i].is_Gang()) return true;
            }
        }
        return false;
    }


    static int isGangCard(Player[] players, int n, Card card) {
        for (int i = 1; i <= 16; i++) {
            if (players[n].cardsHaving[i].getType() == card.getType()
                    && players[n].cardsHaving[i].getOrder() == card.getOrder()
                    && players[n].cardsHaving[i + 1].getType() == card.getType()
                    && players[n].cardsHaving[i + 1].getOrder() == card.getOrder()
                    && players[n].cardsHaving[i + 2].getType() == card.getType()
                    && players[n].cardsHaving[i + 2].getOrder() == card.getOrder()) {
                if (players[n].cardsHaving[i].is_Peng()) return config.WINDY;
                else return config.RAINY;
            }
        }
        return config.WINDY;
    }

    static int isAnGang(Player player) {
        int sum;
        for (int i = 1; i <= 15; i++) {
            sum = 0;
            if (player.cardsHaving[i].is_Gang()) {
                for (int j = 1; j < 4; j++) {
                    if (player.cardsHaving[i].getType() == player.cardsHaving[i + 1].getType()
                            && player.cardsHaving[i].getOrder() == player.cardsHaving[i + j].getOrder()
                            && player.cardsHaving[i].getType() < 4) {
                        sum++;
                    }
                }
                if (sum == 3) {
                    return i;
                }
            }
        }
        return config.WINDY;
    }

    static int isCurrentCardPeng(Player[] players, int n, Card currentCard) {
        int i = 0;
        while (i <= 2) {
            System.arraycopy(players[n].cardsHaving, 1, players[5].cardsHaving, 1, 18);
            if (isPeng(players, 5, currentCard)) return n;
            n = (n + 1) % 4;
            i++;
        }
        return config.WINDY;
    }

    static int isCurrentCardGang(Player[] players, int n, Card currentCard) {
        int i = 0, num;
        while (i <= 2) {
            System.arraycopy(players[n].cardsHaving, 1, players[5].cardsHaving, 1, 18);
            if ((num = isGangCard(players, 5, currentCard)) != -1) {
                if (num == 2) currentCard.setPeng(true);
                return n;
            }
            n = (n + 1) % 4;
            i++;
        }
        return config.WINDY;
    }

    static int isCurrentWin(Player[] players, int n, Card cardOfPlayer) {
        int i = 0;
        while (i <= 2) {
            System.arraycopy(players[n].cardsHaving, 1, players[5].cardsHaving, 1, 18);
            players[5].cardsHaving[18] = cardOfPlayer;
            int[] a = new int[19];
            if (HuUtil.isHu(players, 5, a)) {
                return n;
            }
            n = (n + 1) % 4;
            i++;
        }
        return config.WINDY;
    }
}
