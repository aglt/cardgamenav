package cardgame.uxinn.`is`.cardgame.ui.main.viewmodels

import android.arch.lifecycle.ViewModel
import android.util.Pair
import cardgame.uxinn.`is`.cardgame.model.Card
import cardgame.uxinn.`is`.cardgame.model.CardPair

class GameViewModel : ViewModel() {
    var playerCount: Int = 0
    var dealsLeftCount: Int = 0
    var dealerCount: Int = 0
    lateinit var title: String
    lateinit var playerAvatarSource: String
    lateinit var cardDeal: CardPair
}
