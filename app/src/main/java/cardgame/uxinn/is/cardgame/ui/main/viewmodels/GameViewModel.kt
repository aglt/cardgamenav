package cardgame.uxinn.`is`.cardgame.ui.main.viewmodels

import android.arch.lifecycle.ViewModel
import cardgame.uxinn.`is`.cardgame.model.Card
import cardgame.uxinn.`is`.cardgame.model.CardPair

enum class BetWinner {
    NONE, PLAYER, DEALER
}

class GameViewModel : ViewModel() {
    var playerCount: Int = 0
    var dealsLeftCount: Int = 0
    var dealerCount: Int = 0
    lateinit var title: String
    lateinit var cardDeal: CardPair
    lateinit var betOnCard: Card
    var lastDeal: CardPair? = null
    var winner: BetWinner = BetWinner.NONE
}
