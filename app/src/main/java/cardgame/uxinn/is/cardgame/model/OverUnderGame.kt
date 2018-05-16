package cardgame.uxinn.`is`.cardgame.model

import android.util.Pair
import java.util.*

/**
 * Created by aegir on 14.05.2018.
 */

data class Player(var wins: Int = 0)

class OverUnderGame(private val player: Player) {
    private lateinit var dealer: OverUnderDealer
    private lateinit var bets: MutableList<OverUnderBet>
    private var totalDeals: Int = 0

    val isFinished: Boolean
        get() = remainingDeals() == 0

    val isPlayerWinner: Boolean
        get() = player.wins > totalDeals / 2

    fun startNewGame() {
        bets = ArrayList()
        dealer = OverUnderDealer()
        dealer.prepareGame()
        totalDeals = dealer.remainingDeals()
        player.wins = 0
    }

    fun remainingDeals(): Int {
        return dealer.remainingDeals()
    }

    fun deal(): Pair<Card, Card> {
        if (remainingDeals() == 0) throw RuntimeException("No more cards")
        return dealer.deal()
    }

    fun addBet(bet: OverUnderBet) {
        bets.add(bet)
        if (bet.playerWins()) {
            player.wins++
        }
    }

    fun lastBet(): OverUnderBet {
        if (bets.size == 0) throw IndexOutOfBoundsException("No bets have been made")
        return bets[bets.size - 1]
    }
}
