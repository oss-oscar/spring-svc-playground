package oscar.c.pozas.playground.app.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.serverError
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.restassured.RestAssured
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_EACH_TEST_METHOD
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.ResourceUtils

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8089)
@AutoConfigureEmbeddedDatabase(type = POSTGRES, refresh = AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class PokemonControllerAcceptanceTest {

    @LocalServerPort
    protected var springBootPort = 0 // Port used during the test is injected

    fun getSearchResponseBodyString(expectedStatusCode: HttpStatus, params: Map<String, Any>): String =
        RestAssured.given()
            .params(params)
            .`when`()
            .port(springBootPort).get("/public/v1/pokemon/**")
            .then()
            .statusCode(expectedStatusCode.value())
            .extract()
            .response().body().asString()

    @Test
    fun `given start_at and end_at valid parameters, when search then return one available event`() {
        val stubXml = ResourceUtils.getFile("classpath:mapping/providerApi_getEvents_content_response_200.xml").readBytes()
        stubFor(get("/api/events").willReturn(ok().withBody(stubXml).withHeader("Content-Type", "application/xml")))

        val response = getSearchResponseBodyString(
            HttpStatus.OK,
            mapOf(Pair("starts_at", "2021-06-10T12:30:00Z"), Pair("ends_at", "2022-06-06T19:15:20Z"))
        )

        val expectedJson = ObjectMapper().readTree(ResourceUtils.getFile("classpath:mapping/feverController_search_content_response_200.json"))
        val responseJson = ObjectMapper().readTree(response)

        assertEquals(expectedJson, responseJson)
    }

    @Test
    fun `given start_at and end_at out of range parameters, when search then return empty list of events`() {
        val stubXml = ResourceUtils.getFile("classpath:mapping/providerApi_getEvents_content_response_200.xml").readBytes()
        stubFor(get("/api/events").willReturn(ok().withBody(stubXml).withHeader("Content-Type", "application/xml")))

        val response = getSearchResponseBodyString(
            HttpStatus.OK,
            mapOf(Pair("starts_at", "2021-10-10T12:30:00Z"), Pair("ends_at", "2022-06-06T19:15:20Z"))
        )

        val expectedJson = ObjectMapper().readTree(ResourceUtils.getFile("classpath:mapping/feverController_search_empty_response_200.json"))
        val responseJson = ObjectMapper().readTree(response)

        assertEquals(expectedJson, responseJson)
    }

    @Test
    fun `given start_at and end_at valid parameters, when search then return internal error code`() {
        stubFor(get("/api/events").willReturn(serverError()))

        val response = getSearchResponseBodyString(
            HttpStatus.INTERNAL_SERVER_ERROR,
            mapOf(Pair("starts_at", "2021-06-10T12:30:00Z"), Pair("ends_at", "2022-06-06T19:15:20Z"))
        )

        val expectedJson = ObjectMapper().readTree(ResourceUtils.getFile("classpath:mapping/feverController_search_error_response_500.json"))
        val responseJson = ObjectMapper().readTree(response)

        assertEquals(expectedJson, responseJson)
    }

    @Test
    fun `given start_at and end_at invalid parameters, when search then return error code`() {
        val response = getSearchResponseBodyString(
            HttpStatus.BAD_REQUEST,
            mapOf(Pair("starts_at", "random-stuff-as-parameter"), Pair("ends_at", "2022-06-06T19:15:20Z"))
        )

        val expectedJson = ObjectMapper().readTree(ResourceUtils.getFile("classpath:mapping/feverController_search_error_response_400.json"))
        val responseJson = ObjectMapper().readTree(response)

        assertEquals(expectedJson, responseJson)
    }
}
