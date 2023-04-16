package oscar.c.pozas.playground.infrastructure.datasource

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import oscar.c.pozas.playground.common.annotation.DataSource
import oscar.c.pozas.playground.domain.model.Pokemon
import java.util.Optional

@DataSource
class PokemonStoreDataSource {

    init {
        Database.connect(url = "jdbc:postgresql://[::1]:15432/pokemon", driver = "org.postgresql.Driver", user = "root", password = "")
    }

    fun save(pokemon: Pokemon) {
        transaction {
            PokemonDAO.new {
                pokedex = pokemon.id
                name = pokemon.name
            }
        }
    }

    fun getById(id: Int): Optional<Pokemon> = transaction { Optional.ofNullable(PokemonDAO.findById(id)?.toDomain()) }
}

object PokemonTable : IntIdTable(name = "pokemon") {
    val pokedex = integer("pokedex").index()
    val name = varchar("name", 50)
}

class PokemonDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PokemonDAO>(PokemonTable)

    var pokedex by PokemonTable.pokedex
    var name by PokemonTable.name

    fun toDomain(): Pokemon = Pokemon(pokedex, name)
}
