package com.androidmeda.kotlinbridge

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class KotlinBridgeTest {

    @Test fun strings() {
        assertTrue(isNullOrEmpty(null as String?))
        assertTrue(isNullOrBlank("   "))
        assertEquals("", orEmpty(null))
        assertNull(trimToNull("   "))
        assertEquals("ab…", ellipsize("abcd", 3))
    }

    @Test fun collections() {
        assertTrue(isNullOrEmpty(listOf<Int>()))
        assertEquals(0, sizeOrZero(null))
        assertEquals(1, firstOrNull(listOf(1,2,3)))
        assertEquals(3, lastOrNull(listOf(1,2,3)))
        assertNull(getOrNull(listOf(1,2,3), 10))
    }

    @Test fun parsing() {
        assertEquals(123, toIntOrNull("123"))
        assertNull(toIntOrNull("nope"))
        assertEquals(5, coerceIn(10, 0, 5))
    }
}
