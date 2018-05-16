package cardgame.uxinn.`is`.cardgame.ui.main.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import cardgame.uxinn.`is`.cardgame.R
import cardgame.uxinn.`is`.cardgame.model.OverUnderBet
import cardgame.uxinn.`is`.cardgame.model.OverUnderGame
import cardgame.uxinn.`is`.cardgame.model.Player
import cardgame.uxinn.`is`.cardgame.ui.main.fragments.GameFragment.Bet.CardDown
import cardgame.uxinn.`is`.cardgame.ui.main.fragments.GameFragment.Bet.CardUp
import cardgame.uxinn.`is`.cardgame.ui.main.viewmodels.BetWinner
import cardgame.uxinn.`is`.cardgame.ui.main.viewmodels.GameViewModel


class GameFragment : Fragment() {

    enum class Bet {
        CardUp, CardDown
    }

    private lateinit var viewHolder: ViewHolder
    private lateinit var viewModel: GameViewModel
    private lateinit var game: OverUnderGame

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.game_fragment, container, false)
        viewHolder = ViewHolder(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        game = OverUnderGame(Player())
        game.startNewGame()
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.dealsLeftCount = game.remainingDeals()
        viewModel.cardDeal = game.deal()
        viewModel.dealerCount = 0
        viewModel.playerCount = 0

        displayView(viewModel)
    }

    private fun displayView(viewModel: GameViewModel) {
        viewHolder.count.text = viewModel.dealsLeftCount.toString()
        viewHolder.dealerCount.text = viewModel.dealerCount.toString()
        viewHolder.playerCount.text = viewModel.playerCount.toString()
        viewHolder.playerCount.setTextColor(getPlayResultColor(viewModel.winner == BetWinner.PLAYER))
        viewHolder.dealerCount.setTextColor(getPlayResultColor(viewModel.winner == BetWinner.DEALER))

        viewHolder.cardUp.setImageResource(getImageResource(viewModel.cardDeal.second.imageResource))
        fadeInOut(viewHolder.count)
        if (viewModel.lastDeal != null) {
            viewHolder.cardDownLast.setImageResource(getImageResource(viewModel.lastDeal!!.first.imageResource))
            viewHolder.cardUpLast.setImageResource(getImageResource(viewModel.lastDeal!!.second.imageResource))
            viewHolder.cardDownLast.visibility = View.VISIBLE
            viewHolder.cardUpLast.visibility = View.VISIBLE
//            viewHolder.cardDownLast.setBackgroundColor(getPlayResultColor(viewModel.cardDeal.first.value == viewModel.betOnCard.value))
//            viewHolder.cardUpLast.setBackgroundColor(getPlayResultColor(viewModel.cardDeal.second.value == viewModel.betOnCard.value))
        }
    }

    private fun getPlayResultColor(highlightColor: Boolean): Int {
        if (context == null) return 0
        val color = if (highlightColor) R.color.colorPrimary else R.color.colorPrimaryText;
        return ContextCompat.getColor(context!!, color);
    }

    private fun onBetPlaced(what: Bet) {
        val bet = OverUnderBet(viewModel.cardDeal)
        when (what) {
            CardDown -> bet.betOnCard = viewModel.cardDeal.first
            CardUp -> bet.betOnCard = viewModel.cardDeal.second
        }
        game.addBet(bet)
        if (game.isFinished) {
            handleGameFinished();
        } else {
            updateViewAfterBet()
        }
    }

    private fun handleGameFinished() {
        if (game.isPlayerWinner) {
            NavHostFragment.findNavController(this).navigate(R.id.nav_action_game_results_winner);
        } else {
            NavHostFragment.findNavController(this).navigate(R.id.nav_action_game_over);
        }
    }

    private fun updateViewAfterBet() {
        val bet: OverUnderBet = game.lastBet()
        viewModel.betOnCard = bet.betOnCard
        if (bet.playerWins()) {
            viewModel.playerCount++
            viewModel.winner = BetWinner.PLAYER
        } else {
            viewModel.dealerCount++
            viewModel.winner = BetWinner.DEALER
        }
        viewModel.lastDeal = bet.cardDeal
        if (!game.isFinished) {
            viewModel.dealsLeftCount = game.remainingDeals()
            viewModel.cardDeal = game.deal()
            displayView(viewModel)
        }
    }

    private fun fadeInOut(view: View) {
        val alphaAnim = AlphaAnimation(1.0f, 0.0f)
        alphaAnim.startOffset = 200
        alphaAnim.duration = 4000
        alphaAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                view.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        view.animation = alphaAnim
    }

    private fun getImageResource(resourceName: String): Int {
        println(resourceName)
        val identifier = context!!.resources
                .getIdentifier(resourceName,
                        "drawable", context!!.packageName)

        return if (identifier == 0) R.drawable.ic_card_black_joker else identifier
    }

    inner class ViewHolder(view: View) {
        val title: TextView = view.findViewById(R.id.place_bet_text_view)
        val count: TextView = view.findViewById(R.id.counter_text_view)
        val dealerCount: TextView = view.findViewById(R.id.dealer_counter_text_view)
        val playerCount: TextView = view.findViewById(R.id.player_counter_text_view)
        val cardDownLast: ImageView = view.findViewById(R.id.card_down_last)
        val cardUpLast: ImageView = view.findViewById(R.id.card_up_last)
        val cardUp: ImageView = view.findViewById(R.id.card_up_image_view)
        private val cardDown: ImageView = view.findViewById(R.id.card_down_image_view)

        init {
            cardUp.setOnClickListener { onBetPlaced(Bet.CardUp) }
            cardDown.setOnClickListener { onBetPlaced(Bet.CardDown) }
        }
    }
}
