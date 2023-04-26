package oscar.c.pozas.playground.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.domain.repository.PokemonRepository
import oscar.c.pozas.playground.domain.usecases.CreatePokemonUseCase

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreatePokemonUseCaseTest {

    @MockK
    private lateinit var repository: PokemonRepository

    @InjectMockKs
    private lateinit var createPokemon: CreatePokemonUseCase

    private val pokemon = Pokemon(id = 5, name = "Charmeleon")

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.create(pokemon) }.returns(pokemon)
    }

    @Test
    fun `when provided a Pokemon then not throw exception`() {
        assertDoesNotThrow {
            createPokemon(pokemon)
        }
    }
}