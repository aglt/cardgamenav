package cardgame.uxinn.`is`.cardgame.model

/**
 * Created by aegir on 14.05.2018.
 */
class OverUnderBet(pair: CardPair) {
    val cardDeal: CardPair = pair
    lateinit var betOnCard: Card

    fun playerWins(): Boolean {
        if (cardDeal.first.value == cardDeal.second.value)
            return false

        val winnerCardValue = if (cardDeal.first.value > cardDeal.second.value)
            cardDeal.first.value
        else
            cardDeal.second.value

        return betOnCard.value == winnerCardValue
    }
}