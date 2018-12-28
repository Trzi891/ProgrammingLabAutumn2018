public class Player {
    /**
     * @name name of player
     * @coins the coins a player has
     * @iswin check a player can win
     * @cardsHaving the cards a player has
     */
    private String name;
    private int coins;
    private boolean iswin;
    Card[] cardsHaving = new Card[19];

    Player() {
        name = "catLovesSleep";
        coins = 0;
        iswin = false;
        for (int i = 0; i < 19; i++) cardsHaving[i] = new Card();
    }

    String getName() {
        return name;
    }

    int getCoins() {
        return coins;
    }

    boolean is_win() {
        return iswin;
    }

    Card[] getCardsHaving() {
        return cardsHaving;
    }

    void setName(String s) {
        name = s;
    }

    void setCoins(int i) {
        coins = i;
    }

    void setIswin(boolean b) {
        iswin = b;
    }

    void setCardsHaving(Card[] cards) {
        cardsHaving = cards;
    }
}
