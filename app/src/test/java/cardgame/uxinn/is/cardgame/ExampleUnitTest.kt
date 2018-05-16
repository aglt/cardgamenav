package cardgame.uxinn.`is`.cardgame

import cardgame.uxinn.`is`.cardgame.model.OverUnderBet
import cardgame.uxinn.`is`.cardgame.model.OverUnderGame
import cardgame.uxinn.`is`.cardgame.model.Player
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun house_always_wins() {

        val iterations = 10000
        var playerWins = 0

        for (i in 0 until iterations) {
            val game = OverUnderGame(Player())
            game.startNewGame()
            while (!game.isFinished) {
                val cardDeal = game.deal()
                val bet = OverUnderBet(cardDeal)
                bet.betOnCard = if (Random().nextBoolean()) cardDeal.first else cardDeal.second
                game.addBet(bet)
            }
            if (game.isPlayerWinner) playerWins++
        }

        val houseWinsPercentage = 100 - (playerWins.toDouble() / iterations * 100)

        println("house wins " + houseWinsPercentage + "% of the time with " + iterations + " iterations")
        assertTrue(houseWinsPercentage > 51)
    }
}
