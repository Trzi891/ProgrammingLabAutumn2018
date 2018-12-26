import org.junit.Assert;
import org.junit.Test;

public class HuUtilTest {

    @Test
    public void dragon7Couple() {
        Player[] players = new Player[5];
        int n = 1;
        Card card = new Card();
        int[] arrayT = {1,1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3};
        int[] arrayO = {1,1, 1, 2, 2, 3, 3, 5, 5, 6, 6, 8, 8, 9, 9, 4, 4, 2, 2};
        for (int i = 0; i <= 4; i++) players[i] = new Player();
        for (int i = 1; i <= 18; i++) {
            players[n].cardsHaving[i] = new Card();
            players[n].cardsHaving[i].setType(arrayT[i]);
            players[n].cardsHaving[i].setOrder(arrayO[i]);
        }
        boolean r = true;
        for (int i = 1; i <= 14; i = i + 2) {
            if (players[n].cardsHaving[i].is_Peng() || players[n].cardsHaving[i].is_Gang()) r = false;
            if (players[n].cardsHaving[i].getType() != players[n].cardsHaving[i + 1].getType()
                    || players[n].cardsHaving[i].getOrder() != players[n].cardsHaving[i + 1].getOrder()) r = false;
            System.out.print(players[n].cardsHaving[i].getOrder());
            System.out.println(players[n].cardsHaving[i+1].getOrder());
        }
        Assert.assertTrue(r);
    }

    @Test
    public void isHu() {

    }

    @Test
    public void canDo() {

    }

    @Test
    public void isJiangCard() {

    }

}
