import com.google.gson.Gson
import khttp.get
import model.Vacancy
import model.VacancyResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class VacancyTextTest : AbstractTest() {

    private val host = config[hhApiHost] + config[vacanciesEndpoint]
    private val bearerToken = "Bearer ${config[token]}"
    private val header = mapOf("Authorization" to bearerToken)

    @Test
    fun `Check status code`() {
        print(bearerToken)
        val r = get(host, headers = header)
        Assertions.assertEquals(200, r.statusCode)
    }

    @Test
    fun `Check vacancies with empty text`(){
        val r = get(host, headers = header)
        val vacanciesResponse: VacancyResponse = Gson().fromJson(r.jsonObject.toString(), VacancyResponse::class.java)

        Assertions.assertEquals(200, r.statusCode)
        Assertions.assertTrue(!vacanciesResponse.found?.equals(0)!!)
    }

    @Test
    fun `Check vacancies with one correct word`(){
        val word: String = "Тестировщик"
        val payload = mapOf("text" to word)
        val r = get(host, headers = header, params = payload)

        val vacanciesResponse: VacancyResponse = Gson().fromJson(r.jsonObject.toString(), VacancyResponse::class.java)
        val vac: Vacancy? = vacanciesResponse.items?.get(0)

        Assertions.assertEquals(200, r.statusCode)
        Assertions.assertTrue(vac?.name!!.contains(word))
    }

    @Test
    fun `Check vacancies with two correct word`(){
        val wordOne: String = "Тестировщик"
        val wordTwo: String = "Программист"
        val payload = mapOf("text" to ("$wordOne,$wordTwo"))
        val r = get(host, headers = header, params = payload)

        val vacanciesResponse: VacancyResponse = Gson().fromJson(r.jsonObject.toString(), VacancyResponse::class.java)
        val vac: Vacancy? = vacanciesResponse.items?.get(0)

        Assertions.assertEquals(200, r.statusCode)
        Assertions.assertTrue(vac?.name!!.contains(wordOne) || vac?.name!!.contains(wordTwo))
    }


    @Test
    fun `Check vacancies with star`(){
        val word: String = "гео"
        val payload = mapOf("text" to ("$word*"))
        val r = get(host, headers = header, params = payload)

        val vacanciesResponse: VacancyResponse = Gson().fromJson(r.jsonObject.toString(), VacancyResponse::class.java)
        val vacOne: Vacancy? = vacanciesResponse.items?.get(0)
        val vacTwo: Vacancy? = vacanciesResponse.items?.get(1)

        Assertions.assertEquals(200, r.statusCode)
        Assertions.assertTrue(vacOne?.name!!.contains(word))
        Assertions.assertTrue(vacTwo?.name!!.contains(word))
    }
}