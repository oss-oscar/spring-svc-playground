package oscar.c.pozas.playground.infrastructure.datamodel

import org.jetbrains.exposed.sql.Table

object PokemonDAO : Table() {
    val id = varchar("id", 10) // Column<String>
    val name = varchar("name", length = 50) // Column<String>

    override val primaryKey = PrimaryKey(id, name = "PK_Pokemon_ID")
}
