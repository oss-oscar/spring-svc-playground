package oscar.c.pozas.playground.context.user.domain.repository

import oscar.c.pozas.playground.context.user.domain.User

interface UserRepository {

    fun save(user: User)

    fun getBy(id: User.Id): User?
}