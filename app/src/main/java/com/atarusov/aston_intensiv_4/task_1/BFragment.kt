package com.atarusov.aston_intensiv_4.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.atarusov.aston_intensiv_4.R
import com.atarusov.aston_intensiv_4.databinding.FragmentBBinding

class BFragment : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToFragmentC.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.fragment_container,
                    CFragment.newInstance(getString(R.string.task_1_message_from_b_to_c))
                )
                setReorderingAllowed(true)
                addToBackStack(CFragment.TAG)
            }
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        const val TAG = "fragment_b"

        @JvmStatic
        fun newInstance() = BFragment()
    }
}