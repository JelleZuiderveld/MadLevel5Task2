package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private val games: ArrayList<Game> = arrayListOf()
    private val homeAdapter: HomeAdapter = HomeAdapter(games)
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addGameFragment)
        }

        initView()
        observeChanges()

    }

    private fun initView(){
        rvGames.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = homeAdapter
        rvGames.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        creatItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun observeChanges(){
        gameViewModel.gameData.observe(viewLifecycleOwner, Observer { liveData: List<Game> ->
            games.clear()
            games.addAll(liveData)
            games.sortBy { it.date }
            homeAdapter.notifyDataSetChanged()
        })
    }

    private fun creatItemTouchHelper(): ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val remove = games[position]

                gameViewModel.deleteGame(remove)
            }

        }

        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.delete_all -> {
            gameViewModel.deleteAllGames()
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }

}