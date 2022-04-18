package tribore.rickandmorty.presentation.screens.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tribore.rickandmorty.R
import tribore.rickandmorty.databinding.FragmentHomeBinding
import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.presentation.adapter.MainAdapter
import tribore.rickandmorty.util.LoadingStatus

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val adapterRcView: MainAdapter by lazy {
        MainAdapter { item -> onClickItemRecyclerView(item) }
    }
    private lateinit var rcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rcView = binding.rcViewLayout
        rcView.adapter = adapterRcView
        scrollRecyclerView()

        viewModel.loadStatus.observe(viewLifecycleOwner) {
            if (it == LoadingStatus.ERROR) errorEvent()
        }

        viewModel.allCharacterRickAndMorty.observe(viewLifecycleOwner) {
            adapterRcView.submitList(it)
        }

        return binding.root
    }

    // лямбда для элемента ресайклера
    private fun onClickItemRecyclerView(itemCharacter: CharacterDomainModel) {
        val id = itemCharacter.id
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutInfoFragment(id))
    }

    // Обработчик прокрутки ресайклера
    private fun scrollRecyclerView() {
        val layoutManager = rcView.layoutManager as LinearLayoutManager

        rcView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (viewModel.loadStatus.value != LoadingStatus.LOADING) {
                    val maxSizeItem = layoutManager.itemCount
                    val lastItem = layoutManager.findLastVisibleItemPosition()
                    if (lastItem + 3 >= maxSizeItem) {
                        Log.d("Test", "Работает")
                        viewModel.loadData()
                    }
                }
            }
        })
    }

    private fun errorEvent() {
        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
    }
}