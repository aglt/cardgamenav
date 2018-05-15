package cardgame.uxinn.`is`.cardgame.model

import java.util.*

/**
 * Created by aegir on 13.05.2018.
 */
//data class Rank(val value: Int, val name: String)

data class Card(private val suit: Suit, private val rank: Rank) {
    val text: String by lazy {
        rank.name + " of " + suit.name
    }

    val value: Int
        get() {
            return rank.ordinal
        }

    val imageResource: String by lazy {
        "ic_card_"
                .plus(rank.cardValue)
                .plus("_")
                .plus(suit.name.toLowerCase())
    }

}

class CardDeck(private val cards: MutableList<Card>) {

    fun shuffle() {
        cards.shuffle();
    }

    fun removeCards(from: Int = 0, to: Int = cards.size / 2 - 1) {
        cards.removeAll(cards.subList(from, to));
    }

    fun removeCards(count: Int = cards.size / 2) {
        if (count < 1 || count > 52) throw IndexOutOfBoundsException()
        for (i in 0 until count) {
            cards.removeAt(0)
        }
    }

    val count: Int
        get() {
            return cards.size
        }

    val hasCards: Boolean
        get() {
            return cards.size > 0
        }

    fun deal(numberOfCards: Int): List<Card> {
        if (numberOfCards > cards.size) throw IndexOutOfBoundsException("numberOfCards " + numberOfCards + " > size " + cards.size)

        val result = ArrayList<Card>(numberOfCards)

        fun draw(): Card {
            return cards.removeAt(cards.size - 1)
        }

        for (i in 0 until numberOfCards) {
            result.add(draw())
        }

        return result
    }
};

enum class Suit {
    HEARTS,
    SPADES,
    DIAMONDS,
    CLUBS;
}

enum class Rank(val cardValue: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13)
}

class CardDeckBuilder {
    private val ranks = Rank.values()
    private val suits = Suit.values()

    fun build(): CardDeck {
        val cards = ArrayList<Card>()
        for (suit in suits) {
            for (rank in ranks) {
                cards.add(Card(suit, rank))
            }
        }

        return CardDeck(cards)
    }
}
