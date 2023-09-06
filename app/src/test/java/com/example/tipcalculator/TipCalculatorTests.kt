package com.example.tipcalculator
import org.junit.Test
import org.junit.Assert.assertEquals
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculate2010Tip() {
        val amount = "10.00"
        val tip = "20"

        val expected: String = NumberFormat.getCurrencyInstance().format(2.0)

        val actual: String = calculateTip(amount, tip, false)

        assertEquals(expected, actual)
    }

    @Test
    fun test2() {
        val amount = "120.97"
        val tip = "35.5"

        val expected: String = NumberFormat.getCurrencyInstance().format(42.94)

        val actual: String = calculateTip(amount, tip, false)

        assertEquals(actual, expected)
    }

    @Test
    fun test3() {
        val amount = "120.97"
        val tip = "35.5"

        val expected: String = NumberFormat.getCurrencyInstance().format(43)

        val actual: String = calculateTip(amount, tip, true)

        assertEquals(actual, expected)
    }
}