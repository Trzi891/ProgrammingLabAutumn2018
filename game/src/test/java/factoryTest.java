import org.junit.Assert;
import org.junit.Test;


public class factoryTest {
    private factory factory = new factory();
    int n;

    void temp(Player[] players, int n, Card card, int type, int order, int[] at, int[] ao, boolean peng, boolean gang) {
        card.setType(type);
        card.setOrder(order);
        for (int i = 0; i <= 4; i++) players[i] = new Player();
        for (int i = 1; i <= 18; i++) {
            players[n].cardsHaving[i] = new Card();
            players[n].cardsHaving[i].setType(at[i]);
            players[n].cardsHaving[i].setOrder(ao[i]);
            players[n].cardsHaving[i].setPeng(peng);
            players[n].cardsHaving[i].setGang(gang);
        }
    }

    @Test
    public void isPeng() {
        n = 1;
        Player[] players = new Player[5];
        Card card = new Card();
        int[] arrayT = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
        int[] arrayO = {1, 2, 3, 4, 5, 1, 2, 4, 2, 5, 7, 9, 1, 7, 4, 4, 2, 2, 3};
        temp(players, n, card, 3, 4, arrayT, arrayO, true, false);
        Assert.assertFalse(factory.isPeng(players, n, card));

        n = 2;
        Player[] players1 = new Player[5];
        Card card1 = new Card();
        int[] arrayT1 = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
        int[] arrayO1 = {1, 2, 3, 4, 5, 1, 2, 2, 2, 5, 7, 9, 1, 2, 2, 3, 4, 4, 7};
        temp(players1, n, card1, 2, 2, arrayT1, arrayO1, true, true);
        Assert.assertTrue(factory.isPeng(players1, n, card1));
    }

    @Test
    public void isGangCard() {
        n = 1;
        Player[] players = new Player[5];
        Card card = new Card();
        int[] arrayT = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
        int[] arrayO = {1, 2, 3, 4, 5, 1, 2, 4, 5, 5, 7, 9, 1, 2, 4, 4, 4, 5, 6};
        temp(players, n, card, 3, 4, arrayT, arrayO, true, false);
        Assert.assertEquals(-1, factory.isGangCard(players, n, card));

        n = 2;
        Player[] players1 = new Player[5];
        Card card1 = new Card();
        int[] arrayT1 = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
        int[] arrayO1 = {1, 2, 3, 4, 5, 1, 2, 2, 2, 2, 7, 9, 1, 2, 2, 3, 4, 4, 7};
        temp(players1, n, card1, 2, 2, arrayT1, arrayO1, false, true);
        Assert.assertEquals(1, factory.isGangCard(players1, n, card1));
    }

    void temp1(Player player, int[] arrayT, int[] arrayO) {
        for (int i = 1; i <= 18; i++) {
            player.cardsHaving[i] = new Card();
            player.cardsHaving[i].setType(arrayT[i]);
            player.cardsHaving[i].setOrder(arrayO[i]);
            player.cardsHaving[i].setGang(true);
        }
    }

    @Test
    public void isAnGang() {
        Player player = new Player();
        int[] arrayT = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3};
        int[] arrayO = {1, 2, 2, 2, 2, 3, 5, 4, 5, 5, 7, 9, 2, 3, 4, 5, 5, 6, 6};
        temp1(player, arrayT, arrayO);
        Assert.assertEquals(1, factory.isAnGang(player));

        int[] arrayT1 = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3};
        int[] arrayO1 = {1, 2, 2, 3, 3, 4, 5, 4, 5, 5, 5, 5, 2, 3, 4, 5, 5, 6, 6};
        temp1(player, arrayT1, arrayO1);
        Assert.assertEquals(8, factory.isAnGang(player));

        int[] arrayT2 = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3};
        int[] arrayO2 = {1, 2, 2, 3, 3, 4, 5, 4, 5, 6, 8, 9, 2, 3, 4, 5, 5, 6, 6};
        temp1(player, arrayT2, arrayO2);
        Assert.assertEquals(-1, factory.isAnGang(player));
    }

}
