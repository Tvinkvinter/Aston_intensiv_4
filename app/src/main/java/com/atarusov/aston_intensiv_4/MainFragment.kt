package com.atarusov.aston_intensiv_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.atarusov.aston_intensiv_4.databinding.FragmentMainBinding
import com.atarusov.aston_intensiv_4.task_1.AFragment
import com.atarusov.aston_intensiv_4.task_2.ui.UsersFragment

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTask1.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, AFragment.newInstance())
                setReorderingAllowed(true)
                addToBackStack(AFragment.TAG)
            }
        }

        binding.tvTask2.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, UsersFragment.newInstance())
                setReorderingAllowed(true)
                addToBackStack(UsersFragment.TAG)
            }
        }
    }

    companion object {
        const val TAG = "fragment_main"

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}