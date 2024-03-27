package oscar.c.pozas.playground.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.repository.PokemonRepository
import oscar.c.pozas.playgroud.context.pokemon.domain.service.PokemonCreator

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreatePokemonUseCaseTest {

    @MockK
    private lateinit var repository: PokemonRepository

    @InjectMockKs
    private lateinit var pokemonCreator: PokemonCreator

    private val pokemon = Pokemon(id = Pokemon.Id(5), name = Pokemon.Name("Charmeleon"))

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.create(pokemon) }.returns(pokemon)
    }

    @Test
    fun `when provided a Pokemon then not throw exception`() {
        assertDoesNotThrow {
            pokemonCreator(pokemon)
        }
    }
}
