package tribore.rickandmorty.presentation.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import tribore.rickandmorty.R
import tribore.rickandmorty.databinding.FragmentAboutInfoBinding
import tribore.rickandmorty.domain.models.CharacterDomainModel
import tribore.rickandmorty.util.LoadingStatus

@AndroidEntryPoint
class DetailsInfoFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private val binding: FragmentAboutInfoBinding by lazy {
        FragmentAboutInfoBinding.inflate(layoutInflater)
    }
    private var data: CharacterDomainModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = DetailsInfoFragmentArgs.fromBundle(requireArguments())
        val idFromArgs = args.id

        viewModel.loadDataCharacter(idFromArgs)

        viewModel.loadStatus.observe(viewLifecycleOwner) {
            if (it == LoadingStatus.ERROR) errorEvent()
        }

        viewModel.detailsCharacter.observe(viewLifecycleOwner) {
            successEvent(it)
        }

        return binding.root
    }

    private fun successEvent(character: CharacterDomainModel) {
        binding.progressBar.visibility = View.GONE

        binding.apply {
            tvNameAboutFragment.text = character.name
            tvRaceAboutFragment.text = character.race
            tvGenderAboutFragment.text = character.gender
            tvStatusAboutFragment.text = character.status
            tvLocationAboutFragment.text = character.location.name
            tvEpisodesAboutFragment.text = character.episode.size.toString()
        }

        Glide
            .with(this)
            .load(character.urlAvatar)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .fallback(R.drawable.ic_placeholder)
            .centerCrop()
            .into(binding.imageAvatar)
    }

    private fun errorEvent() {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
    }
}