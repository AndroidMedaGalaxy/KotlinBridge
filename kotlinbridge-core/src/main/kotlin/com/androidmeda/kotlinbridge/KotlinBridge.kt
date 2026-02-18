@file:JvmName("KotlinBridge")
package com.androidmeda.kotlinbridge

import java.util.concurrent.Callable
import java.util.function.Function

// -------------------- Strings / CharSequence --------------------

fun isNullOrEmpty(s: CharSequence?): Boolean = s == null || s.isEmpty()

fun isNullOrBlank(s: CharSequence?): Boolean = s == null || s.isBlank()

fun orEmpty(s: String?): String = s ?: ""

fun trimToNull(s: String?): String? {
    if (s == null) return null
    val t = s.trim()
    return if (t.isEmpty()) null else t
}

fun ellipsize(s: String?, maxChars: Int): String? {
    if (s == null) return null
    if (maxChars < 0) throw IllegalArgumentException("maxChars must be >= 0")
    if (s.length <= maxChars) return s
    return if (maxChars <= 1) "…" else s.take(maxChars - 1) + "…"
}

// -------------------- Collections / Maps --------------------

fun isNullOrEmpty(c: Collection<*>?): Boolean = c == null || c.isEmpty()

fun isNullOrEmpty(m: Map<*, *>?): Boolean = m == null || m.isEmpty()

fun sizeOrZero(c: Collection<*>?): Int = c?.size ?: 0

fun <T> firstOrNull(it: Iterable<T>?): T? = it?.firstOrNull()

fun <T> lastOrNull(list: List<T>?): T? = list?.lastOrNull()

fun <T> getOrNull(list: List<T>?, index: Int): T? {
    if (list == null) return null
    return if (index in 0 until list.size) list[index] else null
}

fun <T> emptyIfNull(list: List<T>?): List<T> = list ?: emptyList()

fun <T> emptyIfNull(set: Set<T>?): Set<T> = set ?: emptySet()

fun <K, V> emptyIfNull(map: Map<K, V>?): Map<K, V> = map ?: emptyMap()

// -------------------- Numbers / Parsing / Bounds --------------------

fun toIntOrNull(s: String?): Int? = s?.toIntOrNull()

fun toLongOrNull(s: String?): Long? = s?.toLongOrNull()

fun toDoubleOrNull(s: String?): Double? = s?.toDoubleOrNull()

fun coerceIn(value: Int, min: Int, max: Int): Int = value.coerceIn(min, max)

fun coerceIn(value: Long, min: Long, max: Long): Long = value.coerceIn(min, max)

fun coerceAtLeast(value: Int, min: Int): Int = value.coerceAtLeast(min)

fun coerceAtMost(value: Int, max: Int): Int = value.coerceAtMost(max)

// -------------------- Null-safety helpers --------------------

fun <T> orElse(value: T?, fallback: T): T = value ?: fallback

fun <T> letOrNull(value: T?, block: Function<T, T?>): T? {
    // Note: Function returns nullable to keep Java callsites flexible
    return if (value == null) null else block.apply(value)
}

// -------------------- Exceptions / runCatching-like --------------------

fun <T> tryOrNull(block: Callable<T>): T? =
    try { block.call() } catch (_: Throwable) { null }

fun <T> tryOrDefault(defaultValue: T, block: Callable<T>): T =
    try { block.call() } catch (_: Throwable) { defaultValue }
