@file:JvmName("StringExtensions")
package com.androidmeda.kotlinbridge.strings

import com.androidmeda.kotlinbridge.isNullOrBlank
import com.androidmeda.kotlinbridge.isNullOrEmpty
import java.util.Base64
import java.util.Locale
import java.util.regex.Pattern

// ==================== String checks and predicates ====================

fun isNotNullOrEmpty(s: CharSequence?): Boolean = !isNullOrEmpty(s)

fun isNotNullOrBlank(s: CharSequence?): Boolean = !isNullOrBlank(s)

fun startsWith(s: String?, prefix: String, ignoreCase: Boolean = false): Boolean =
    s?.startsWith(prefix, ignoreCase) ?: false

fun endsWith(s: String?, suffix: String, ignoreCase: Boolean = false): Boolean =
    s?.endsWith(suffix, ignoreCase) ?: false

fun contains(s: String?, char: Char, ignoreCase: Boolean = false): Boolean =
    s?.contains(char, ignoreCase) ?: false

fun contains(s: String?, substring: String, ignoreCase: Boolean = false): Boolean =
    s?.contains(substring, ignoreCase) ?: false

fun matches(s: String?, regex: String): Boolean =
    s?.matches(Regex(regex)) ?: false

// ==================== String transformation ====================

fun orEmpty(s: String?): String = s ?: ""

fun orDefault(s: String?, defaultValue: String): String = s ?: defaultValue

fun trim(s: String?): String? = s?.trim()

fun trimStart(s: String?): String? = s?.trimStart()

fun trimEnd(s: String?): String? = s?.trimEnd()

fun trimToNull(s: String?): String? {
    if (s == null) return null
    val trimmed = s.trim()
    return if (trimmed.isEmpty()) null else trimmed
}

fun toLowerCase(s: String?, locale: Locale = Locale.getDefault()): String? =
    s?.lowercase(locale)

fun toUpperCase(s: String?, locale: Locale = Locale.getDefault()): String? =
    s?.uppercase(locale)

fun capitalize(s: String?, locale: Locale = Locale.getDefault()): String? {
    if (s.isNullOrEmpty()) return s
    return s[0].uppercaseChar().toString() + s.substring(1).lowercase(locale)
}

fun decapitalize(s: String?, locale: Locale = Locale.getDefault()): String? {
    if (s.isNullOrEmpty()) return s
    return s[0].lowercaseChar().toString() + s.substring(1)
}

// ==================== Substring operations ====================

fun take(s: String?, n: Int): String? {
    if (s == null || n <= 0) return ""
    return s.take(n.coerceAtMost(s.length))
}

fun takeLast(s: String?, n: Int): String? {
    if (s == null || n <= 0) return ""
    return s.takeLast(n.coerceAtMost(s.length))
}

fun takeWhile(s: String?, predicate: (Char) -> Boolean): String? =
    s?.takeWhile(predicate)

fun drop(s: String?, n: Int): String? {
    if (s == null || n <= 0) return s
    return s.drop(n.coerceAtMost(s.length))
}

fun dropLast(s: String?, n: Int): String? {
    if (s == null || n <= 0) return s
    return s.dropLast(n.coerceAtMost(s.length))
}

fun substringBefore(s: String?, delimiter: Char, missingDelimiterValue: String = s ?: ""): String =
    s?.substringBefore(delimiter, missingDelimiterValue) ?: missingDelimiterValue

fun substringBeforeLast(s: String?, delimiter: Char, missingDelimiterValue: String = s ?: ""): String =
    s?.substringBeforeLast(delimiter, missingDelimiterValue) ?: missingDelimiterValue

fun substringAfter(s: String?, delimiter: Char, missingDelimiterValue: String = s ?: ""): String =
    s?.substringAfter(delimiter, missingDelimiterValue) ?: missingDelimiterValue

fun substringAfterLast(s: String?, delimiter: Char, missingDelimiterValue: String = s ?: ""): String =
    s?.substringAfterLast(delimiter, missingDelimiterValue) ?: missingDelimiterValue

fun substringBetween(s: String?, open: String, close: String): String? {
    if (s == null) return null
    val startIndex = s.indexOf(open)
    if (startIndex == -1) return null
    val endIndex = s.indexOf(close, startIndex + open.length)
    if (endIndex == -1) return null
    return s.substring(startIndex + open.length, endIndex)
}

// ==================== String padding and alignment ====================

fun padStart(s: String?, length: Int, padChar: Char = ' '): String? =
    s?.padStart(length, padChar)

fun padEnd(s: String?, length: Int, padChar: Char = ' '): String? =
    s?.padEnd(length, padChar)

fun center(s: String?, length: Int, padChar: Char = ' '): String? {
    if (s == null) return null
    val padding = (length - s.length).coerceAtLeast(0)
    val leftPad = padding / 2
    val rightPad = padding - leftPad
    return "".padEnd(leftPad, padChar) + s + "".padEnd(rightPad, padChar)
}

// ==================== String truncation ====================

fun truncate(s: String?, maxLength: Int, ellipsis: String = "..."): String? {
    if (s == null) return null
    if (maxLength < 0) throw IllegalArgumentException("maxLength must be >= 0")
    if (s.length <= maxLength) return s
    if (maxLength <= ellipsis.length) return ellipsis.take(maxLength)
    return s.take(maxLength - ellipsis.length) + ellipsis
}

fun ellipsize(s: String?, maxChars: Int): String? {
    if (s == null) return null
    if (maxChars < 0) throw IllegalArgumentException("maxChars must be >= 0")
    if (s.length <= maxChars) return s
    return if (maxChars <= 1) "…" else s.take(maxChars - 1) + "…"
}

fun ellipsizeMiddle(s: String?, maxChars: Int, ellipsis: String = "..."): String? {
    if (s == null) return null
    if (maxChars < 0) throw IllegalArgumentException("maxChars must be >= 0")
    if (s.length <= maxChars) return s
    if (maxChars <= ellipsis.length) return ellipsis.take(maxChars)

    val keepChars = maxChars - ellipsis.length
    val leftKeep = keepChars / 2
    val rightKeep = keepChars - leftKeep
    return s.take(leftKeep) + ellipsis + s.takeLast(rightKeep)
}

// ==================== String replacement and cleaning ====================

fun replace(s: String?, oldChar: Char, newChar: Char, ignoreCase: Boolean = false): String? =
    s?.replace(oldChar, newChar, ignoreCase)

fun replace(s: String?, oldValue: String, newValue: String, ignoreCase: Boolean = false): String? =
    s?.replace(oldValue, newValue, ignoreCase)

fun removePrefix(s: String?, prefix: String): String? =
    s?.removePrefix(prefix)

fun removeSuffix(s: String?, suffix: String): String? =
    s?.removeSuffix(suffix)

fun removeSurrounding(s: String?, prefix: String, suffix: String): String? =
    s?.removeSurrounding(prefix, suffix)

fun removeWhitespace(s: String?): String? =
    s?.replace("\\s+".toRegex(), "")

fun collapseWhitespace(s: String?): String? =
    s?.trim()?.replace("\\s+".toRegex(), " ")

// ==================== String splitting and joining ====================

fun split(s: String?, delimiter: Char, limit: Int = 0): List<String> =
    s?.split(delimiter, limit = limit) ?: emptyList()

fun split(s: String?, regex: String, limit: Int = 0): List<String> =
    s?.split(Regex(regex), limit = limit) ?: emptyList()

fun lines(s: String?): List<String> =
    s?.lines() ?: emptyList()

fun lineSequence(s: String?): Sequence<String> =
    s?.lineSequence() ?: emptySequence()

fun <T> joinToString(
    items: Iterable<T>?,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    limit: Int = -1,
    truncated: String = "...",
    transform: ((T) -> String)? = null
): String =
    items?.joinToString(separator, prefix, postfix, limit, truncated, transform) ?: ""

fun repeat(s: String?, times: Int): String? {
    if (s == null || times <= 0) return ""
    return s.repeat(times)
}

fun repeatChar(c: Char, times: Int): String =
    if (times <= 0) "" else c.toString().repeat(times)

// ==================== String builders ====================

fun buildString(builder: StringBuilder.() -> Unit): String =
    StringBuilder().apply(builder).toString()

fun appendTo(builder: StringBuilder?, s: String?): StringBuilder {
    val sb = builder ?: StringBuilder()
    if (s != null) sb.append(s)
    return sb
}

// ==================== Indentation ====================

fun indent(s: String?, indent: String = "    "): String? =
    s?.prependIndent(indent)

fun trimIndent(s: String?): String? =
    s?.trimIndent()

fun trimMargin(s: String?, marginPrefix: String = "|"): String? =
    s?.trimMargin(marginPrefix)

// ==================== Regular expression ====================

fun matches(s: String?, pattern: Pattern): Boolean =
    s?.let { pattern.matcher(it).matches() } ?: false

fun find(s: String?, pattern: Pattern): String? =
    s?.let {
        val matcher = pattern.matcher(it)
        if (matcher.find()) matcher.group() else null
    }

fun findAll(s: String?, pattern: Pattern): List<String> =
    s?.let {
        val matcher = pattern.matcher(it)
        val results = mutableListOf<String>()
        while (matcher.find()) {
            results.add(matcher.group())
        }
        results
    } ?: emptyList()

fun replaceAll(s: String?, pattern: Pattern, replacement: String): String? =
    s?.let { pattern.matcher(it).replaceAll(replacement) }

fun replaceFirst(s: String?, pattern: Pattern, replacement: String): String? =
    s?.let { pattern.matcher(it).replaceFirst(replacement) }

// ==================== URL/Path encoding ====================

fun toSlug(s: String?): String? {
    if (s == null) return null
    return s.lowercase(Locale.getDefault())
        .replace("[^a-z0-9\\s-]".toRegex(), "")
        .trim()
        .replace("\\s+".toRegex(), " ")
        .replace(" ", "-")
        .replace("-+", "-")
}

fun toCamelCase(s: String, separator: String = "[_\\s-]+".toRegex().toString()): String? {
    if (s.isNullOrEmpty()) return s
    return s.split(separator)
        .filter { it.isNotEmpty() }
        .mapIndexed { index, word ->
            if (index == 0) word.lowercase(Locale.getDefault())
            else word.replaceFirstChar { it.uppercaseChar() }
        }
        .joinToString("")
}

fun toSnakeCase(s: String?): String? {
    if (s.isNullOrEmpty()) return s
    return s.replace("([a-z])([A-Z])".toRegex(), "$1_$2")
        .replace("[\\s-]".toRegex(), "_")
        .lowercase(Locale.getDefault())
}

fun toKebabCase(s: String?): String? {
    if (s.isNullOrEmpty()) return s
    return s.replace("([a-z])([A-Z])".toRegex(), "$1-$2")
        .replace("[\\s_]".toRegex(), "-")
        .lowercase(Locale.getDefault())
}

fun toTitleCase(s: String?, locale: Locale = Locale.getDefault()): String? {
    if (s.isNullOrEmpty()) return s
    return s.split("\\s+".toRegex())
        .joinToString(" ") { word ->
            if (word.isEmpty()) word
            else word[0].uppercaseChar().toString() + word.substring(1).lowercase(locale)
        }
}

// ==================== Base64 encoding/decoding ====================

fun encodeBase64(s: String?): String? =
    s?.let {
        @Suppress("NewApi")
        Base64.getEncoder().encodeToString(it.toByteArray())
    }

fun decodeBase64(s: String?): String? =
    s?.let {
        @Suppress("NewApi")
        String(Base64.getDecoder().decode(it))
    }

// ==================== Number parsing ====================

fun toIntOrNull(s: String?): Int? = s?.toIntOrNull()

fun toLongOrNull(s: String?): Long? = s?.toLongOrNull()

fun toDoubleOrNull(s: String?): Double? = s?.toDoubleOrNull()

fun toFloatOrNull(s: String?): Float? = s?.toFloatOrNull()

fun toBooleanOrNull(s: String?): Boolean? =
    when (s?.lowercase(Locale.getDefault())) {
        "true", "1", "yes", "on" -> true
        "false", "0", "no", "off" -> false
        else -> null
    }

// ==================== Checks ====================

fun hasLength(s: String?, min: Int, max: Int): Boolean {
    val len = s?.length ?: 0
    return len in min..max
}

fun isBlank(s: CharSequence?): Boolean = s?.isBlank() ?: true

fun isNotBlank(s: CharSequence?): Boolean = !isBlank(s)

fun isEmpty(s: CharSequence?): Boolean = s?.isEmpty() ?: true

fun isNotEmpty(s: CharSequence?): Boolean = !isEmpty(s)

// ==================== Comparison ====================

fun equals(s1: String?, s2: String?, ignoreCase: Boolean = false): Boolean =
    if (s1 == null && s2 == null) true
    else s1.equals(s2, ignoreCase)

fun compareTo(s1: String?, s2: String?, ignoreCase: Boolean = false): Int =
    when {
        s1 == null && s2 == null -> 0
        s1 == null -> -1
        s2 == null -> 1
        else -> s1.compareTo(s2, ignoreCase)
    }

fun isAlphanumeric(s: String?): Boolean =
    s?.let { it.all { c -> c.isLetterOrDigit() } } ?: false

fun isNumeric(s: String?): Boolean =
    s?.let { it.all { c -> c.isDigit() } } ?: false

fun isAlpha(s: String?): Boolean =
    s?.let { it.all { c -> c.isLetter() } } ?: false
