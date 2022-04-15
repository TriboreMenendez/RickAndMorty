package tribore.rickandmorty.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tribore.rickandmorty.databinding.FragmentHomeBinding
import tribore.rickandmorty.domain.models.PersonDomainModel
import tribore.rickandmorty.presentation.adapter.MainAdapter
import tribore.rickandmorty.presentation.viewmodels.MainViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val adapterPerson: MainAdapter by lazy {
        MainAdapter { item -> onClickItemRecyclerView(item) }
    }
    private lateinit var rcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rcView = binding.rcViewLayout
        rcView.adapter = adapterPerson

        viewModel.allPersonRickAndMorty.observe(viewLifecycleOwner) {
            bindRecyclerView(it)
        }

        return binding.root
    }

    private fun onClickItemRecyclerView(itemPerson: PersonDomainModel) {

    }

    private fun bindRecyclerView(items: List<PersonDomainModel>) {
        adapterPerson.submitList(items)
    }

}