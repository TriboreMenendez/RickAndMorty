package tribore.rickandmorty.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tribore.rickandmorty.R
import tribore.rickandmorty.databinding.ActivityMainBinding
import tribore.rickandmorty.presentation.viewmodels.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main)