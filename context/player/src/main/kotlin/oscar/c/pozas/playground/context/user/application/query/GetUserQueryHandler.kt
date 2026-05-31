package oscar.c.pozas.playground.context.user.application.query

import jakarta.inject.Named
import oscar.c.pozas.playground.context.user.domain.User
import oscar.c.pozas.playground.context.user.domain.repository.UserRepository

@Named
class GetUserQueryHandler(private val repository: UserRepository) {

    fun handle(query: GetPlayerQuery): User =
        repository.getBy(User.Id(query.id)) ?: throw User.Error.PlayerNotFound
}

data class GetPlayerQuery(val id: String)