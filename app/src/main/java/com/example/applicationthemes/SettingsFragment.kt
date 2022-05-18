package com.example.applicationthemes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applicationthemes.databinding.FragmentSettingsBinding
import kotlin.time.measureTime

const val SHARED_KEY = "shared_key"
const val INDIGO = "indigo"
const val LIME = "lime"

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipThemeIndigo.setOnCheckedChangeListener() {_, _ ->
            saveSelectedTheme(INDIGO)
        }

        binding.chipThemeLime.setOnCheckedChangeListener { _, _ ->
            saveSelectedTheme(LIME)
        }
    }

    fun saveSelectedTheme(theme: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(SHARED_KEY, theme)
            apply()
        }

        val intent = activity?.intent
        activity?.finish()
        startActivity(intent)
        activity?.overridePendingTransition(0, 0)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

}