package com.atarusov.aston_intensiv_4.task_2.data

import com.atarusov.aston_intensiv_4.task_2.model.User

object UsersSource {
    val users = mutableListOf<User>()

    init {
        repeat(20) { i ->
            users.add(
                User(
                    id = i.toLong() + 1,
                    name = "Name ${i + 1}",
                    lastname = "Lastname ${i + 1}",
                    phoneNumber = "+${(1..9999999999999).random()}"
                )
            )
        }
    }
}