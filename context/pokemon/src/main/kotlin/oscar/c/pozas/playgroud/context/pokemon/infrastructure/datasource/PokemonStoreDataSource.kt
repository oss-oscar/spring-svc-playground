package oscar.c.pozas.playgroud.context.pokemon.infrastructure.datasource

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playground.spring.annotation.DataSource

@DataSource
class PokemonStoreDataSource {

    init {
        Database.connect(url = "jdbc:postgresql://[::1]:15432/pokemon", driver = "org.postgresql.Driver", user = "root", password = "")
    }

    fun save(pokemon: Pokemon) {
        transaction {
            PokemonDAO.new {
                pokedex = pokemon.id.value
                name = pokemon.name.value
            }
        }
    }

    fun getById(id: Int): Pokemon? = transaction { PokemonDAO.findById(id)?.toDomain() }
}

object PokemonTable : IntIdTable(name = "pokemon") {
    val pokedex = integer("pokedex").index()
    val name = varchar("name", 50)
}

class PokemonDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PokemonDAO>(PokemonTable)

    var pokedex by PokemonTable.pokedex
    var name by PokemonTable.name

    fun toDomain(): Pokemon = Pokemon(Pokemon.Id(pokedex), Pokemon.Name(name))
}
