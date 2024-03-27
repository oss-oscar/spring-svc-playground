package oscar.c.pozas.playground.context.pokemon.domain.service

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.repository.PokemonRepository
import oscar.c.pozas.playgroud.context.pokemon.domain.service.PokemonFinder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonFinderTest {

    @MockK
    private lateinit var repository: PokemonRepository

    @InjectMockKs
    private lateinit var pokemonFinder: PokemonFinder

    private val pokemon = Pokemon(id = Pokemon.Id(5), name = Pokemon.Name("Charmeleon"))

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.findById(POKEMON_ID) }.returns(pokemon)
    }

    @Test
    fun `when provided an Id then return a Pokemon`() {
        val result = pokemonFinder.findBy(id = POKEMON_ID)

        assertEquals(pokemon, result)
    }

    @Test
    fun `when provided a negative Id the throw an error`() {
        assertThrows<IllegalStateException>("Poke id cannot be negative") {
            pokemonFinder.findBy(id = Pokemon.Id(-1))
        }
    }

    @AfterAll
    fun afterTests() {
        unmockkAll()
    }

    companion object {

        val POKEMON_ID = Pokemon.Id(5)
    }
}
