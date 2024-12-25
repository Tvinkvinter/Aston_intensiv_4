package com.atarusov.aston_intensiv_4.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.atarusov.aston_intensiv_4.R
import com.atarusov.aston_intensiv_4.databinding.FragmentABinding


class AFragment : Fragment() {

    private lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToFragmentB.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, BFragment.newInstance())
                setReorderingAllowed(true)
                addToBackStack(BFragment.TAG)
            }
        }
    }

    companion object {
        const val TAG = "fragment_a"

        @JvmStatic
        fun newInstance() = AFragment()
    }
}