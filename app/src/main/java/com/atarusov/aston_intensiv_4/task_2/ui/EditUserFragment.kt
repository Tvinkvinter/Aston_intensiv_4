package com.atarusov.aston_intensiv_4.task_2.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import com.atarusov.aston_intensiv_4.R
import com.atarusov.aston_intensiv_4.databinding.FragmentEditUserBinding
import com.atarusov.aston_intensiv_4.task_2.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL


class EditUserFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var mediaPicker: ActivityResultLauncher<PickVisualMediaRequest>

    private lateinit var userToEdit: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userToEdit = BundleCompat.getParcelable(it, USER_TO_EDIT_KEY, User::class.java)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)

        setViewsWithUser(userToEdit)

        mediaPicker = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                displayAvatarImage(uri)
                userToEdit = userToEdit.copy(imageUri = uri)
                arguments?.putParcelable(USER_TO_EDIT_KEY, userToEdit)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imgAvatar.setOnClickListener {
            mediaPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.btnSave.setOnClickListener {
            val editedUser = userToEdit.copy(
                name = binding.etName.text.toString(),
                lastname = binding.etLastname.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString()
            )
            val result = Bundle().apply { putParcelable(EDITED_USER_KEY, editedUser) }
            parentFragmentManager.setFragmentResult(EDIT_RESULT_KEY, result)
            parentFragmentManager.popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setViewsWithUser(user: User) {
        if (user.imageUri == null) binding.imgAvatar.setImageResource(R.drawable.ic_default_avatar_48)
        else displayAvatarImage(user.imageUri)

        binding.etName.setText(user.name)
        binding.etLastname.setText(user.lastname)
        binding.etPhoneNumber.setText(user.phoneNumber)
    }

    private fun displayAvatarImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
            .transform(FitCenter(), CircleCrop())
            .into(binding.imgAvatar)
    }

    companion object {
        const val EDIT_RESULT_KEY = "user_to_edit"
        const val USER_TO_EDIT_KEY = "user_to_edit"
        const val EDITED_USER_KEY = "edited_user"
        const val TAG = "edit_fragment"

        @JvmStatic
        fun newInstance(userToEdit: User) = EditUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_TO_EDIT_KEY, userToEdit)
            }
        }
    }
}