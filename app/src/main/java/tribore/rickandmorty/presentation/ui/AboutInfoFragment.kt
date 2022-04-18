package tribore.rickandmorty.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tribore.rickandmorty.databinding.FragmentAboutInfoBinding

class AboutInfoFragment : Fragment() {

    private val binding: FragmentAboutInfoBinding by lazy {
        FragmentAboutInfoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




        return binding.root
    }
}