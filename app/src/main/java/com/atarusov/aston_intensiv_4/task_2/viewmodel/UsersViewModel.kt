package com.atarusov.aston_intensiv_4.task_2.viewmodel

import androidx.lifecycle.ViewModel
import com.atarusov.aston_intensiv_4.task_2.data.UsersSource
import com.atarusov.aston_intensiv_4.task_2.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UsersViewModel : ViewModel() {

    private val _users = MutableStateFlow(UsersSource.users)
    val users: StateFlow<List<User>> = _users

    fun editUser(user: User) {
        _users.value = _users.value.toMutableList().apply {
            val editIndex = indexOfFirst { it.id == user.id }
            if (editIndex == -1)
                throw NoSuchElementException("Contact with id ${user.id} not found")
            this[editIndex] = user
        }
    }
}