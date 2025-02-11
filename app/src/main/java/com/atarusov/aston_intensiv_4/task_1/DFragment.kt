package com.atarusov.aston_intensiv_4.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.atarusov.aston_intensiv_4.databinding.FragmentDBinding


class DFragment : Fragment() {

    private lateinit var binding: FragmentDBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToFragmentB.setOnClickListener {
            parentFragmentManager.popBackStack(BFragment.TAG, 0)
        }
    }

    companion object {
        const val TAG = "fragment_d"

        @JvmStatic
        fun newInstance() = DFragment()
    }
}