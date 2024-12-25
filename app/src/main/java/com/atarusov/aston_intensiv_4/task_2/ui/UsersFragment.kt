package com.atarusov.aston_intensiv_4.task_2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.atarusov.aston_intensiv_4.R
import com.atarusov.aston_intensiv_4.databinding.FragmentUsersBinding
import com.atarusov.aston_intensiv_4.task_2.adapter.UsersAdapter
import com.atarusov.aston_intensiv_4.task_2.model.User
import com.atarusov.aston_intensiv_4.task_2.viewmodel.UsersViewModel
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        val rvAdapter = UsersAdapter { userToEdit: User ->
            parentFragmentManager.commit {
                replace(R.id.fragment_container, EditUserFragment.newInstance(userToEdit))
                setReorderingAllowed(true)
                addToBackStack(EditUserFragment.TAG)
            }
        }
        binding.rvUsers.adapter = rvAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect { contacts ->
                    rvAdapter.users = contacts
                }
            }
        }

        setFragmentResultListener(EditUserFragment.EDIT_RESULT_KEY) { _: String, bundle: Bundle ->
            val user: User? = BundleCompat.getParcelable(
                bundle,
                EditUserFragment.EDITED_USER_KEY,
                User::class.java
            )
            user?.let { viewModel.editUser(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "users_fragment"

        @JvmStatic
        fun newInstance() = UsersFragment()
    }
}