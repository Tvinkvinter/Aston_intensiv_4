package com.atarusov.aston_intensiv_4.task_2.data

import com.atarusov.aston_intensiv_4.task_2.model.User
import com.github.javafaker.Faker

object UsersSource {
    private val faker = Faker()
    val users = mutableListOf<User>()

    init {
        repeat(20) { i ->
            users.add(
                User(
                    id = i.toLong() + 1,
                    name = faker.name().firstName(),
                    lastname = faker.name().lastName(),
                    phoneNumber = faker.numerify("+7 (9##) ### ##-##")
                )
            )
        }
    }
}