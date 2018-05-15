package cardgame.uxinn.`is`.cardgame.ui.main.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cardgame.uxinn.`is`.cardgame.R
import cardgame.uxinn.`is`.cardgame.model.OverUnderGame
import cardgame.uxinn.`is`.cardgame.model.Player
import cardgame.uxinn.`is`.cardgame.ui.main.fragments.GameFragment.Bet.CardDown
import cardgame.uxinn.`is`.cardgame.ui.main.fragments.GameFragment.Bet.CardUp
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
        game.startNewGame(context)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.cardDeal = game.deal()
        viewModel.dealerCount = 0
        viewModel.playerCount = 0
        viewModel.dealsLeftCount = game.remainingDeals()

        displayView(viewModel)
    }

    private fun displayView(viewModel: GameViewModel) {
        viewHolder.count.text = viewModel.dealsLeftCount.toString()
        viewHolder.dealerCount.text = viewModel.dealerCount.toString()
        viewHolder.playerCount.text = viewModel.playerCount.toString()
        viewHolder.cardUp.setImageResource(getImageResource(viewModel.cardDeal.second.imageResource))
    }

    private fun getImageResource(resourceName: String): Int {
        println(resourceName)
        val identifier = context!!.resources
                .getIdentifier(resourceName,
                        "drawable", context!!.packageName)

        return if (identifier == 0) R.drawable.ic_card_black_joker else identifier
    }

    private fun onBetPlaced(what: Bet) {
        when (what) {
            CardUp -> Toast.makeText(context, "Up kort", Toast.LENGTH_LONG).show()
            CardDown -> Toast.makeText(context, "Niður kort", Toast.LENGTH_LONG).show()
        }
    }

    inner class ViewHolder(view: View) {
        val title: TextView = view.findViewById(R.id.place_bet_text_view)
        val count: TextView = view.findViewById(R.id.counter_text_view)
        val dealerCount: TextView = view.findViewById(R.id.dealer_counter_text_view)
        val playerCount: TextView = view.findViewById(R.id.player_counter_text_view)
        val playerAvatar: ImageView = view.findViewById(R.id.player_avatar)
        val cardUp: ImageView = view.findViewById(R.id.card_up_image_view)
        private val cardDown: ImageView = view.findViewById(R.id.card_down_image_view)

        init {
            cardUp.setOnClickListener { onBetPlaced(Bet.CardUp) }
            cardDown.setOnClickListener { onBetPlaced(Bet.CardDown) }
        }
    }
}
