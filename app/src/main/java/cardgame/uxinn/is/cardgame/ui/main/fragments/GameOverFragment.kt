package cardgame.uxinn.`is`.cardgame.ui.main.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cardgame.uxinn.`is`.cardgame.R
import cardgame.uxinn.`is`.cardgame.ui.main.viewmodels.GameOverViewModel

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private lateinit var viewHolder: ViewHolder
    private lateinit var viewModel: GameOverViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.game_over_fragment, container, false)
        viewHolder = ViewHolder(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOverViewModel::class.java)
    }

    private class ViewHolder (view: View) {
        val title: TextView = view.findViewById(R.id.place_bet_text_view)
        val count: TextView = view.findViewById(R.id.counter_text_view)
        val dealerCount: TextView = view.findViewById(R.id.dealer_counter_text_view)
        val playerCount: TextView = view.findViewById(R.id.player_counter_text_view)
        val playerAvatar: ImageView = view.findViewById(R.id.player_avatar)
    }
}
