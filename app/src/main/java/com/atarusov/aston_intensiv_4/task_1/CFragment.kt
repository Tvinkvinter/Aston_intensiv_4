package com.atarusov.aston_intensiv_4.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.atarusov.aston_intensiv_4.R
import com.atarusov.aston_intensiv_4.databinding.FragmentCBinding

private const val MESSAGE_KEY = "message"

class CFragment : Fragment() {

    private lateinit var binding: FragmentCBinding

    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(MESSAGE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMessage.text = message

        binding.btnToFragmentD.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, DFragment.newInstance())
                setReorderingAllowed(true)
                addToBackStack(DFragment.TAG)
            }
        }

        binding.btnToFragmentA.setOnClickListener {
            parentFragmentManager.popBackStack(AFragment.TAG, 0)
        }
    }

    companion object {
        const val TAG = "fragment_c"

        @JvmStatic
        fun newInstance(message: String) =
            CFragment().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE_KEY, message)
                }
            }
    }
}