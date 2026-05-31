package oscar.c.pozas.playground.context.user.infraestructure.datasource

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import oscar.c.pozas.playground.context.user.domain.User
import java.time.ZonedDateTime
import java.util.UUID

@Repository
interface PlayerStoreDataSource : CrudRepository<PlayerDto, UUID> {
    fun findByIdValue(id: UUID): PlayerDto?
}

@Repository
class PlayerStoreDataSourceAdapter(private val repository: PlayerStoreDataSource) {

    fun save(user: User) {
        val playerDto = PlayerDto(
            idValue = user.id.value,
            name = user.name.value,
            createdOn = user.createdOn.toString()
        )
        repository.save(playerDto)
    }

    fun getById(id: User.Id): User? {
        val playerDto = repository.findByIdValue(id.value) ?: return null
        return User.create(
            id = User.Id(playerDto.idValue),
            name = User.Name(playerDto.name),
            now = ZonedDateTime.parse(playerDto.createdOn)
        )
    }
}

@Entity
@Table(name = "player")
data class PlayerDto(
    @Id
    val idValue: UUID,
    val name: String,
    val createdOn: String
) {
    // Default constructor required by JPA
    constructor() : this(UUID.randomUUID(), "", "")
}
