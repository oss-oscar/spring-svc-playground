package oscar.c.pozas.playground.context.user.application.command

import jakarta.inject.Named
import oscar.c.pozas.playground.context.user.domain.User
import oscar.c.pozas.playground.context.user.domain.repository.UserRepository
import oscar.c.pozas.playground.kernel.clock.Clock
import oscar.c.pozas.playground.kernel.event.DomainEventPublisher

@Named
class CreatePlayerCommandHandler(
    private val repository: UserRepository,
    private val eventPublisher: DomainEventPublisher,
    private val clock: Clock,
) {

    fun handle(command: CreatePlayerCommand) {
        val userId = User.Id(command.id)

        guardPlayerNotExists(userId)

        val user = User.create(userId, User.Name(command.name), clock.now())

        repository.save(user)

        eventPublisher.publish(user.pullEvents())
    }

    private fun guardPlayerNotExists(id: User.Id) {
        if (repository.getBy(id) != null) throw User.Error.PlayerAlreadyExists
    }
}

data class CreatePlayerCommand(
    val id: String,
    val name: String,
)
