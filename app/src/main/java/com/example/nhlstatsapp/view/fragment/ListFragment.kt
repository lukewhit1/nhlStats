package com.example.nhlstatsapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nhlstatsapp.databinding.FragmentListBinding
import com.example.nhlstatsapp.model.PlayerDisplayStats
import com.example.nhlstatsapp.model.SearchByNameResponse
import com.example.nhlstatsapp.model.StatElement
import com.example.nhlstatsapp.view.UIState
import com.example.nhlstatsapp.view.adapter.SinglePlayerStatsAdapter

class ListFragment(private val playerName: String): ViewModelFragment() {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private lateinit var singlePlayerStatsAdapter: SinglePlayerStatsAdapter
    private var playerStatsMap: MutableMap<Int, PlayerDisplayStats> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater)
        configureSearchObserver()
        return binding.root
    }

    private fun configureSearchObserver() {
        viewModel.searchList.observe(viewLifecycleOwner) {
            when(it) {
                is UIState.Success<*> -> {
                    //Log.d("configureObserver()", "success")
                    parsePlayerList(it.response as SearchByNameResponse)
                }
                is UIState.Error -> {
                    binding.apply {
                        //TODO
                        //Log.d("configureObserver()", "error")
                    }
                }
                is UIState.Loading -> {
                    viewModel.getPlayersByName(playerName = playerName)
                }
            }
        }
    }

    private fun configureStatsObserver(playerStats: PlayerDisplayStats) {
        viewModel.singleStats.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success<*> -> {
                    println("    success")
                    println(it.response)
                    //getCompletePlayerInfo(it.response as StatsByPlayer, playerStats)
                }
                is UIState.Error -> {
                    binding.apply {
                        //TODO
                        println("   error")
                    }
                }
                is UIState.Loading -> {
                    println("   loading")
                    viewModel.getSinglePlayerStats(playerStats.playerId)
                }
            }
        }
    }

    private fun parsePlayerList(response: SearchByNameResponse) {
        for (player in response.suggestions) {
            val playerStats = PlayerDisplayStats()

            val splitStrings = player.split("|")

            val firstName = splitStrings[2]
            val lastName = splitStrings[1]

            playerStats.fullName = "$firstName $lastName"
            playerStats.playerId = splitStrings[0].toInt()
            playerStats.teamName = splitStrings[11]
            playerStats.birthday = splitStrings[10]
            playerStats.height = splitStrings[5]
            playerStats.weight = splitStrings[6]

            playerStatsMap[playerStats.playerId] = playerStats
            viewModel.setStatsLoadingState()
            configureStatsObserver(playerStats)
        }

        binding.apply {
            rvPlayerList.apply {
                singlePlayerStatsAdapter = SinglePlayerStatsAdapter(openDetails = ::openDetails)
                adapter = singlePlayerStatsAdapter
            }
            val values: List<PlayerDisplayStats> = playerStatsMap.values.toList()
            singlePlayerStatsAdapter.setPlayerList(values)
        }
    }

    private fun getCompletePlayerInfo(response: StatElement, playerStats: PlayerDisplayStats) {
//        playerStats.assists = response.stats[0].splits[0].stat.assists
//        playerStats.goals = response.stats[0].splits[0].stat.goals
//        playerStats.points = response.stats[0].splits[0].stat.points
        println(response)

        playerStatsMap[playerStats.playerId] = playerStats
    }

    private fun openDetails(playerStats: PlayerDisplayStats) {
        PopupFragment.newInstance(playerStats)
            .show(childFragmentManager, "TestDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}