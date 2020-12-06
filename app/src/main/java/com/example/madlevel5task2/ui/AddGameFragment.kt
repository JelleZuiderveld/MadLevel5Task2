package com.example.madlevel5task2.ui

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.time.LocalDate
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        fabAddGame.setOnClickListener{
            addGame()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            navController.popBackStack()
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun addGame(){
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val day = Integer.parseInt(etDay.text.toString())
        val month = Integer.parseInt(etMonth.text.toString())
        val year = Integer.parseInt(etYear.text.toString())
        val date = LocalDate.of(year, month, day)
        val game = Game(title, platform, date)

        viewModel.addGame(game)
    }

}