package oscar.c.pozas.playground.context.user.infraestructure

import jakarta.inject.Named
import oscar.c.pozas.playground.context.user.domain.User
import oscar.c.pozas.playground.context.user.domain.repository.UserRepository
import oscar.c.pozas.playground.context.user.infraestructure.datasource.PlayerStoreDataSourceAdapter

@Named
class PersistenceUserRepository(
    private val storeDataSource: PlayerStoreDataSourceAdapter,
) : UserRepository {

    override fun save(user: User) {
        storeDataSource.save(user)
    }

    override fun getBy(id: User.Id): User? {
        return storeDataSource.getById(id)
    }
}
