package cardgame.uxinn.`is`.cardgame.ui.main.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import cardgame.uxinn.`is`.cardgame.R
import cardgame.uxinn.`is`.cardgame.ui.main.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewHolder: ViewHolder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        viewHolder = ViewHolder(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.buttonText = "Spila"
        displayView(viewModel)
    }

    private fun displayView(m: MainViewModel) {
        viewHolder.playGameButton.text = m.buttonText
        viewHolder.playGameButton.setOnClickListener({ NavHostFragment.findNavController(this).navigate(R.id.nav_action_next) })
    }

    private class ViewHolder (view: View) {
        val playGameButton: Button = view.findViewById(R.id.start_game_button)
    }
}
