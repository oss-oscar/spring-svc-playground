package oscar.c.pozas.playground.domain.usecase

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
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.domain.repository.PokemonRepository
import oscar.c.pozas.playground.domain.usecases.GetPokemonByIdUseCase
import java.util.Optional

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetPokemonUseCaseTest {

    @MockK
    private lateinit var repository: PokemonRepository

    @InjectMockKs
    private lateinit var getPokemonById: GetPokemonByIdUseCase

    private val pokemon = Pokemon(id = 5, name = "Charmeleon")

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.findById(POKEMON_ID) }.returns(Optional.of(pokemon))
    }

    @Test
    fun `when provided an Id then return a Pokemon`() {
        val result = getPokemonById(id = POKEMON_ID)

        assertEquals(pokemon, result.get())
    }

    @Test
    fun `when provided a negative Id the throw an error`() {
        assertThrows<IllegalStateException>("Poke id cannot be negative") {
            getPokemonById(id = POKEMON_ID * -1)
        }
    }

    @AfterAll
    fun afterTests() {
        unmockkAll()
    }

    companion object {
        const val POKEMON_ID = 5
    }
}
