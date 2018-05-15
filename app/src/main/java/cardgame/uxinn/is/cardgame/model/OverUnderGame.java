package cardgame.uxinn.is.cardgame.model;

import android.content.Context;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aegir on 14.05.2018.
 */
public class OverUnderGame {
    private final Player player;
    private OverUnderDealer dealer;
    private List<OverUnderBet> bets;

    public OverUnderGame(Player player) {
        this.player = player;
    }

    public void startNewGame(Context context) {
        bets = new ArrayList<>();
        dealer = new OverUnderDealer();
        dealer.prepareGame();
    }

    public int remainingDeals() {
        return dealer.remainingDeals();
    }

    public Pair<Card, Card> deal() {
        if(remainingDeals() == 0) throw new RuntimeException("No more cards");
        return dealer.deal();
    }

    public void addBet(OverUnderBet bet) {
        bets.add(bet);
    }

    public List<OverUnderBet> getBets() {
        return bets;
    }

    public boolean hasMoreDeals() {
        return remainingDeals() > 0;
    }

    public boolean isPlayerWinner() {
        return false;
    }
}
