package cardgame.uxinn.`is`.cardgame.model

import android.util.Pair

/**
 * Created by aegir on 14.05.2018.
 */
class OverUnderBet(pair: CardPair) {
    private val faceDown: Card = pair.first
    private val faceUp: Card = pair.second
    lateinit var betOnCard: Card

    fun playerWins(): Boolean {
        if (faceDown.value == faceUp.value)
            return false

        val winnerCardValue = if (faceDown.value > faceUp.value)
            faceDown.value
        else
            faceUp.value

        return betOnCard.value == winnerCardValue
    }
}