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
import androidx.navigation.fragment.NavHostFragment
import cardgame.uxinn.`is`.cardgame.R
import cardgame.uxinn.`is`.cardgame.ui.main.viewmodels.GameOverViewModel

class GameOverFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.game_over_fragment, container, false)
        view.findViewById<Button>(R.id.button_close)
                .setOnClickListener {
                    NavHostFragment.findNavController(this).navigate(R.id.action_game_over_next)
                }
        return view
    }
}
