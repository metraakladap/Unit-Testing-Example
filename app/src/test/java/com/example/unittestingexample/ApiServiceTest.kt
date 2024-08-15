package com.example.unittestingexample

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ApiServiceTest {

    private val mockApiService = mock(ApiService::class.java)

    @Test
    fun `test successful rate fetch`() = runBlocking {
        val fakeResponse = RateResponse(RateData("bitcoin", "BTC", "$", "45000.00"))
        `when`(mockApiService.getBitcoinRate()).thenReturn(fakeResponse)

        val result = mockApiService.getBitcoinRate()
        assertEquals("45000.00", result.data.rateUsd)
    }
}

class RateResponseTest {

    private val jsonResponse = """
        {
            "data": {
                "id": "bitcoin",
                "symbol": "BTC",
                "currencySymbol": "$",
                "rateUsd": "45000.00"
            }
        }
    """

    @Test
    fun `test parsing of JSON response`() {
        val gson = Gson()
        val rateResponse = gson.fromJson(jsonResponse, RateResponse::class.java)

        assertEquals("bitcoin", rateResponse.data.id)
        assertEquals("BTC", rateResponse.data.symbol)
        assertEquals("$", rateResponse.data.currencySymbol)
        assertEquals("45000.00", rateResponse.data.rateUsd)
    }
}
