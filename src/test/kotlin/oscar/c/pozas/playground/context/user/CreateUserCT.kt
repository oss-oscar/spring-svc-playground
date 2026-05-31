package oscar.c.pozas.playground.context.user

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import oscar.c.pozas.playground.Application
import oscar.c.pozas.playground.context.user.domain.User
import oscar.c.pozas.playground.context.user.domain.repository.UserRepository
import java.util.UUID
import oscar.c.pozas.playground.context.user.primaryadapter.rest.create.CreateUserResponse
import oscar.c.pozas.playground.kernel.clock.Clock
import oscar.c.pozas.playground.kernel.clock.ClockUTC
import java.time.ZonedDateTime

@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
)
@ActiveProfiles("test")
class CreateUserCT {

    @Inject private lateinit var repository: UserRepository

    private val httpClient = OkHttpClient()
    private val objectMapper = jacksonObjectMapper() // Instead of ObjectMapper to support Kotlin constructors

    @Test
    fun `should create a player`() {
        val playerName = "Player_${UUID.randomUUID()}"
        val playerInput = """{ "name": "$playerName" }""".toRequestBody("application/json".toMediaType())

        val request = okhttp3.Request.Builder()
            .url(API_URL)
            .post(playerInput)
            .build()

        val response = httpClient.newCall(request).execute()
        assert(response.isSuccessful)

        val expectedPlayerId = objectMapper.readValue<CreateUserResponse>(response.body!!.string())
        val expectedUser = User.create(User.Id(expectedPlayerId.id), User.Name(playerName), now)

        val savedUser = repository.getBy(User.Id(expectedPlayerId.id))
        assertThat(savedUser).isEqualTo(expectedUser)
    }

    companion object {
        private const val API_URL = "http://localhost:8080/public/v1/player"

        private val now = ZonedDateTime.now()
    }

    @SpringBootApplication
    class TestConfiguration {

        @Bean fun clock(): Clock = ClockUTC()
    }
}