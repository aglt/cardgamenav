package cardgame.uxinn.`is`.cardgame.model

import android.util.Pair

/**
 * Created by aegir on 14.05.2018.
 */
class OverUnderDealer {
    private lateinit var cardDeck: CardDeck

    fun prepareGame() {
        cardDeck = CardDeckBuilder().build()
        cardDeck.shuffle()
        cardDeck.removeCards(26)
    }

    fun remainingDeals(): Int {
        return cardDeck.count / 2
    }

    fun deal(): Pair<Card, Card> {
        if (remainingDeals() == 0) throw RuntimeException("No more cards left")

        val pair = cardDeck.deal(2)
        return Pair(pair[0], pair[1])
    }
}
