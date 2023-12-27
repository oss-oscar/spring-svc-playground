package oscar.c.pozas.playground.infrastructure.datasource

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
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.infrastructure.datamodel.PokemonResponseModel

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonApiDataSourceIntegrationTest {

    @MockK
    private lateinit var pokeApiClient: PokeApiV2Client

    @InjectMockKs
    private lateinit var pokemonApiDataSource: PokemonApiClientDataSource

    private val pokemonApiResponse = PokemonResponseModel(id = 5, name = "Charmeleon")

    private val pokemon = Pokemon(id = 5, name = "Charmeleon")

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        every { pokeApiClient.getPokemonById(POKEMON_ID.toString()) }.returns(pokemonApiResponse)
    }

    @Test
    fun `when getEvents then return a list of 1 events`() {
        val result = pokemonApiDataSource.get(POKEMON_ID)

        assertEquals(pokemon, result.get())
    }

    @AfterAll
    fun afterTests() {
        unmockkAll()
    }

    companion object {
        const val POKEMON_ID = 5
    }
}
