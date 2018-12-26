import com.config;

public class HuUtil {

    static boolean dragon7Couple(Player[] players, int n) {
        for (int i = 1; i <= 14; i = i + 2) {
            if (players[n].cardsHaving[i].is_Peng() || players[n].cardsHaving[i].is_Gang()) return false;
            if (players[n].cardsHaving[i].getType() != players[n].cardsHaving[i + 1].getType()
                    || players[n].cardsHaving[i].getOrder() != players[n].cardsHaving[i + 1].getOrder()) return false;
        }
        return true;
    }

    static boolean isHu(Player[] players, int n, int[] array) {
        if (dragon7Couple(players, n)) return true;
        Card.sortCards(players, n);
        boolean check = true;
        int a = 1, b = 1, type = players[n].cardsHaving[1].getType(), isBoss = 0;
        for (int i = 1; i <= 18; i++) {
            if (players[n].cardsHaving[i].is_Gang() || players[n].cardsHaving[i].is_Peng()) array[i] = 1;
        }
        while (a < 19 && players[n].cardsHaving[a].getType() < 4
                && players[n].cardsHaving[a].is_Gang()
                && players[n].cardsHaving[a].is_Peng()) {
            if (players[n].cardsHaving[a].getType() == type) a++;
            else {
                type = players[n].cardsHaving[a].getType();
                if (canDo(players, n, array, a, b) == 0 && !isJiangCard(players, n, array, b, a - 1)) check = false;
                else if (isJiangCard(players, n, array, b, a - 1)) {
                    if (isBoss == 0) isBoss = 1;
                    else check = false;
                }
                b = a;
            }
        }
        if ((canDo(players, n, array, b, a - 1) == 1 && isBoss == 1 & check)
                || (isBoss == 0 && isJiangCard(players, n, array, b, a - 1) && check)) ;
        else check = false;
        return check;
    }

    static int canDo(Player[] players, int n, int[] array, int a, int b) {
        int result = 1, next = -1;
        for (int i = a; i <= b; i++) array[i] = 0;
        for (int i = a; i <= b; i++) {
            if (players[n].cardsHaving[i].is_Peng() || players[n].cardsHaving[i].is_Gang()) array[i] = 1;
            if (array[i] == 1) ;
            else {
                if (i + 2 <= b && players[n].cardsHaving[i + 1].getOrder() == players[n].cardsHaving[i].getOrder()
                        && players[n].cardsHaving[i + 2].getOrder() == players[n].cardsHaving[i].getOrder())
                    array[i] = array[i + 1] = array[i + 2] = 1;
                else {
                    for (int j = i + 1; j < b; j++) {
                        if (players[n].cardsHaving[j].getOrder() == players[n].cardsHaving[i].getOrder() + 1
                                && array[j] == 0) next = j;
                        if (players[n].cardsHaving[j].getOrder() == players[n].cardsHaving[i].getOrder() + 2
                                && array[j] == 0 && next == -1) {
                            array[i] = config.TONG;
                            array[next] = config.TONG;
                            array[j] = config.TONG;
                            next = -2;
                            break;
                        }
                    }
                    if (next != -2) result = 0;
                }
            }
            if (array[i] != 1) result = 0;
        }
        return result;
    }

    static boolean isJiangCard(Player[] players, int n, int[] array, int a, int b) {
        for (int i = a; i <= b; i++) {
            if ((players[n].cardsHaving[i].getOrder() == players[n].cardsHaving[i + 1].getOrder())
                    || (canDo(players, n, array, a, i - 1) == 1
                    && canDo(players, n, array, i + 2, b) == 1)) return true;
        }
        return false;
    }
}
