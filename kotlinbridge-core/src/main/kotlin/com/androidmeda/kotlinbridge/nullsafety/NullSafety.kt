@file:JvmName("NullSafety")
package com.androidmeda.kotlinbridge.nullsafety

import java.util.Optional
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate
import java.util.function.Supplier

// ==================== Basic null checks ====================

fun <T> isNull(value: T?): Boolean = value == null

fun <T> isNotNull(value: T?): Boolean = value != null

fun <T> requireNotNull(value: T?): T =
    value ?: throw IllegalArgumentException("Required value was null")

fun <T> requireNotNull(value: T?, message: String): T =
    value ?: throw IllegalArgumentException(message)

fun <T> requireNotNull(value: T?, lazyMessage: () -> String): T =
    value ?: throw IllegalArgumentException(lazyMessage())

fun <T> checkNotNull(value: T?): T =
    value ?: throw IllegalStateException("Required value was null")

fun <T> checkNotNull(value: T?, message: String): T =
    value ?: throw IllegalStateException(message)

fun <T> checkNotNull(value: T?, lazyMessage: () -> String): T =
    value ?: throw IllegalStateException(lazyMessage())

// ==================== Elvis operator equivalents ====================

fun <T> orElseValue(value: T?, fallback: T): T = value ?: fallback

fun <T> orElseNullable(value: T?, fallback: T?): T? = value ?: fallback

fun <T> orElseGet(value: T?, fallbackSupplier: Supplier<T>): T =
    value ?: fallbackSupplier.get()

fun <T> orElseThrow(value: T?, exceptionSupplier: Supplier<Throwable>): T =
    value ?: throw exceptionSupplier.get()

fun <T, X : Throwable> orElseThrow(value: T?, exceptionSupplier: () -> X): T =
    value ?: throw exceptionSupplier()

fun <T> orElseNull(value: T?): T? = value

fun <T> orDefault(value: T?, defaultValue: T): T = value ?: defaultValue

fun <T> orEmpty(value: String?): String = value ?: ""

fun <T> orZero(value: Int?): Int = value ?: 0

fun <T> orZero(value: Long?): Long = value ?: 0L

fun <T> orZero(value: Double?): Double = value ?: 0.0

fun <T> orFalse(value: Boolean?): Boolean = value ?: false

fun <T> orTrue(value: Boolean?): Boolean = value ?: true

// ==================== Safe call chains (like Kotlin's ?.) ====================

fun <T, R> safeLet(value: T?, transform: Function<T, R>): R? =
    if (value == null) null else transform.apply(value)

fun <T, R> safeLet(value: T?, transform: (T) -> R): R? =
    value?.let(transform)

fun <T, R> letOrDefault(value: T?, transform: Function<T, R>, defaultValue: R): R =
    if (value == null) defaultValue else transform.apply(value)

fun <T, R> letOrDefault(value: T?, defaultValue: R, transform: (T) -> R): R =
    value?.let(transform) ?: defaultValue

fun <T, R> letOrElseGet(value: T?, transform: Function<T, R>, defaultSupplier: Supplier<R>): R =
    if (value == null) defaultSupplier.get() else transform.apply(value)

fun <T, R> letOrElseThrow(value: T?, transform: Function<T, R>, exceptionSupplier: Supplier<Throwable>): R =
    if (value == null) throw exceptionSupplier.get() else transform.apply(value)

fun <T, R> safeAlso(value: T?, action: Consumer<T>): T? {
    if (value != null) action.accept(value)
    return value
}

fun <T> safeAlso(value: T?, action: (T) -> Unit): T? {
    value?.let(action)
    return value
}

// ==================== Multiple value safe calls ====================

fun <T1, T2, R> safeLet(v1: T1?, v2: T2?, block: (T1, T2) -> R): R? =
    if (v1 != null && v2 != null) block(v1, v2) else null

fun <T1, T2, T3, R> safeLet(v1: T1?, v2: T2?, v3: T3?, block: (T1, T2, T3) -> R): R? =
    if (v1 != null && v2 != null && v3 != null) block(v1, v2, v3) else null

fun <T1, T2, T3, T4, R> safeLet(v1: T1?, v2: T2?, v3: T3?, v4: T4?, block: (T1, T2, T3, T4) -> R): R? =
    if (v1 != null && v2 != null && v3 != null && v4 != null) block(v1, v2, v3, v4) else null

fun <T1, T2, T3, T4, T5, R> safeLet(
    v1: T1?, v2: T2?, v3: T3?, v4: T4?, v5: T5?,
    block: (T1, T2, T3, T4, T5) -> R
): R? =
    if (v1 != null && v2 != null && v3 != null && v4 != null && v5 != null)
        block(v1, v2, v3, v4, v5)
    else
        null

fun <T1, T2, R> safeLetOrDefault(v1: T1?, v2: T2?, defaultValue: R, block: (T1, T2) -> R): R =
    if (v1 != null && v2 != null) block(v1, v2) else defaultValue

fun <T1, T2, T3, R> safeLetOrDefault(v1: T1?, v2: T2?, v3: T3?, defaultValue: R, block: (T1, T2, T3) -> R): R =
    if (v1 != null && v2 != null && v3 != null) block(v1, v2, v3) else defaultValue

// ==================== Safe property access ====================

fun <T, R> safeGet(value: T?, property: Function<T, R>): R? =
    if (value == null) null else property.apply(value)

fun <T, R> safeGet(value: T?, property: (T) -> R): R? =
    value?.let(property)

fun <T, R> safeGetOrDefault(value: T?, property: Function<T, R>, defaultValue: R): R =
    if (value == null) defaultValue else property.apply(value)

fun <T, R> safeGetOrDefault(value: T?, defaultValue: R, property: (T) -> R): R =
    value?.let(property) ?: defaultValue

// ==================== Null-aware chaining ====================

fun <T> ifNull(value: T?, action: Runnable): T? {
    if (value == null) action.run()
    return value
}

fun <T> ifNull(value: T?, action: () -> Unit): T? {
    if (value == null) action()
    return value
}

fun <T> ifNotNull(value: T?, action: Consumer<T>): T? {
    if (value != null) action.accept(value)
    return value
}

fun <T> ifNotNull(value: T?, action: (T) -> Unit): T? {
    value?.let(action)
    return value
}

fun <T> ifNotNullElse(value: T?, notNullAction: Consumer<T>, nullAction: Runnable) {
    if (value != null) notNullAction.accept(value) else nullAction.run()
}

fun <T> ifNotNullElse(value: T?, notNullAction: (T) -> Unit, nullAction: () -> Unit) {
    if (value != null) notNullAction(value) else nullAction()
}

fun <T, R> ifNotNullElse(value: T?, notNullTransform: Function<T, R>, nullSupplier: Supplier<R>): R =
    if (value != null) notNullTransform.apply(value) else nullSupplier.get()

fun <T, R> ifNotNullElse(value: T?, notNullTransform: (T) -> R, nullSupplier: () -> R): R =
    if (value != null) notNullTransform(value) else nullSupplier()

// ==================== Optional integration ====================

fun <T> toOptional(value: T?): Optional<T> {
    @Suppress("UNCHECKED_CAST")
    return Optional.ofNullable(value) as Optional<T>
}

fun <T> optionalOf(value: T?): Optional<T> {
    @Suppress("UNCHECKED_CAST")
    return Optional.ofNullable(value) as Optional<T>
}

fun <T> fromOptional(optional: Optional<T>): T? = if (optional.isPresent) optional.get() else null

// ==================== Safe type casting ====================

inline fun <reified T> safeCast(value: Any?): T? = value as? T

@Suppress("UNCHECKED_CAST")
fun <T> safeCast(value: Any?, clazz: Class<T>): T? =
    if (clazz.isInstance(value)) value as T else null

inline fun <reified T> requireCast(value: Any?): T =
    value as? T ?: throw ClassCastException("Cannot cast ${value?.javaClass?.name} to ${T::class.java.name}")

@Suppress("UNCHECKED_CAST")
fun <T> requireCast(value: Any?, clazz: Class<T>): T =
    if (clazz.isInstance(value)) value as T
    else throw ClassCastException("Cannot cast ${value?.javaClass?.name} to ${clazz.name}")

inline fun <reified T> castOrDefault(value: Any?, defaultValue: T): T =
    value as? T ?: defaultValue

@Suppress("UNCHECKED_CAST")
fun <T> castOrDefault(value: Any?, clazz: Class<T>, defaultValue: T): T =
    if (clazz.isInstance(value)) value as T else defaultValue

inline fun <reified T> castOrElse(value: Any?, defaultSupplier: () -> T): T =
    value as? T ?: defaultSupplier()

// ==================== Null-coalescing for chained calls ====================

fun <T, R> safeChain(value: T?, vararg mappers: Function<Any?, Any?>): R? {
    if (value == null) return null
    var result: Any? = value
    for (mapper in mappers) {
        if (result == null) return null
        result = mapper.apply(result)
    }
    @Suppress("UNCHECKED_CAST")
    return result as R?
}

fun <T, R> safeChainOrDefault(value: T?, defaultValue: R, vararg mappers: Function<Any?, Any?>): R {
    if (value == null) return defaultValue
    var result: Any? = value
    for (mapper in mappers) {
        if (result == null) return defaultValue
        result = mapper.apply(result)
    }
    @Suppress("UNCHECKED_CAST")
    return (result as? R) ?: defaultValue
}

// ==================== Null-safe comparison ====================

fun <T> safeCompareTo(a: T?, b: T?): Int where T : Comparable<T>, T : Any =
    when {
        a == null && b == null -> 0
        a == null -> -1
        b == null -> 1
        else -> a.compareTo(b)
    }

fun <T : Comparable<T>> safeEquals(a: T?, b: T?): Boolean =
    if (a == null) b == null else a == b

fun <T> safeEquals(a: T?, b: T?): Boolean =
    if (a == null) b == null else a == b

// ==================== Null-safe string representation ====================

fun toString(value: Any?): String = value?.toString() ?: "null"

fun toStringOrEmpty(value: Any?): String = value?.toString() ?: ""

fun toStringOrDefault(value: Any?, defaultValue: String): String =
    value?.toString() ?: defaultValue

// ==================== Safe lazy evaluation ====================

fun <T> lazyOrNull(supplier: Supplier<T>): T? =
    try { supplier.get() } catch (_: Throwable) { null }

fun <T> lazyOrNull(supplier: () -> T): T? =
    try { supplier() } catch (_: Throwable) { null }

fun <T> lazyOrDefault(supplier: Supplier<T>, defaultValue: T): T =
    try { supplier.get() } catch (_: Throwable) { defaultValue }

fun <T> lazyOrDefault(defaultValue: T, supplier: () -> T): T =
    try { supplier() } catch (_: Throwable) { defaultValue }

fun <T> lazyOrElse(supplier: () -> T, fallback: () -> T): T =
    try { supplier() } catch (_: Throwable) { fallback() }

// ==================== Coalesce (first non-null) ====================

fun <T> coalesce(vararg values: T?): T? =
    values.firstOrNull { it != null }

fun <T> coalesce(values: List<T?>): T? =
    values.firstOrNull { it != null }

fun <T> coalesceOrDefault(vararg values: T?, defaultValue: T): T =
    values.firstOrNull { it != null } ?: defaultValue

fun <T> coalesceOrDefault(values: List<T?>, defaultValue: T): T =
    values.firstOrNull { it != null } ?: defaultValue

fun <T> coalesceOrElse(vararg values: T?, defaultSupplier: () -> T): T =
    values.firstOrNull { it != null } ?: defaultSupplier()

// ==================== Null-safe arrays ====================

fun <T> listOfNotNull(vararg elements: T?): List<T> =
    elements.filterNotNull()

fun <T> listOfNotNull(elements: List<T?>): List<T> =
    elements.filterNotNull()

fun <T> nullIfEmpty(array: Array<T>?): Array<T>? =
    if (array == null || array.isEmpty()) null else array

@Suppress("UNCHECKED_CAST")
fun <T> emptyIfNull(array: Array<T>?): Array<T> {
    return if (array != null) array else emptyArray<Any>() as Array<T>
}

// ==================== Safe exception handling ====================

fun <T> tryOrNull(block: () -> T): T? =
    try { block() } catch (_: Throwable) { null }

fun <T> tryOrNull(block: () -> T, onError: (Throwable) -> Unit): T? =
    try { block() } catch (t: Throwable) { onError(t); null }

fun <T> tryOrDefault(defaultValue: T, block: () -> T): T =
    try { block() } catch (_: Throwable) { defaultValue }

fun <T> tryOrDefault(defaultValue: T, block: () -> T, onError: (Throwable) -> Unit): T =
    try { block() } catch (t: Throwable) { onError(t); defaultValue }

fun <T> tryOrElse(block: () -> T, fallback: () -> T): T =
    try { block() } catch (_: Throwable) { fallback() }

fun <T, R> tryMap(value: T?, transform: (T) -> R): R? =
    if (value == null) null else try { transform(value) } catch (_: Throwable) { null }

fun <T, R> tryMapOrDefault(value: T?, defaultValue: R, transform: (T) -> R): R =
    if (value == null) defaultValue else try { transform(value) } catch (_: Throwable) { defaultValue }

// ==================== Take and takeIf utilities ====================

fun <T> takeIf(value: T?, predicate: Predicate<T>): T? =
    if (value != null && predicate.test(value)) value else null

fun <T> takeIf(value: T?, predicate: (T) -> Boolean): T? =
    if (value != null && predicate(value)) value else null

fun <T> takeUnless(value: T?, predicate: Predicate<T>): T? =
    if (value != null && !predicate.test(value)) value else null

fun <T> takeUnless(value: T?, predicate: (T) -> Boolean): T? =
    if (value != null && !predicate(value)) value else null

fun <T : CharSequence> takeIfNotEmpty(value: T?): T? =
    if (value != null && value.isNotEmpty()) value else null

fun <T : CharSequence> takeIfNotBlank(value: T?): T? =
    if (value != null && value.isNotBlank()) value else null

fun <T> takeIfInRange(value: T?, min: T, max: T): T? where T : Comparable<T>, T : Number =
    if (value != null && value >= min && value <= max) value else null

// ==================== Null-safe boolean logic ====================

fun nullSafeAnd(a: Boolean?, b: Boolean?): Boolean? =
    when {
        a == null || b == null -> null
        else -> a && b
    }

fun nullSafeOr(a: Boolean?, b: Boolean?): Boolean? =
    when {
        a == null || b == null -> null
        else -> a || b
    }

fun nullSafeNot(a: Boolean?): Boolean? =
    a?.let { !it }

fun nullSafeAnd(vararg values: Boolean?): Boolean? =
    if (values.any { it == null }) null else values.all { it == true }

fun nullSafeOr(vararg values: Boolean?): Boolean? =
    if (values.any { it == null }) null else values.any { it == true }

// ==================== Null-safe number operations ====================

fun nullSafePlus(a: Int?, b: Int?): Int? =
    if (a == null || b == null) null else a + b

fun nullSafePlus(a: Long?, b: Long?): Long? =
    if (a == null || b == null) null else a + b

fun nullSafePlus(a: Double?, b: Double?): Double? =
    if (a == null || b == null) null else a + b

fun nullSafeMinus(a: Int?, b: Int?): Int? =
    if (a == null || b == null) null else a - b

fun nullSafeMinus(a: Long?, b: Long?): Long? =
    if (a == null || b == null) null else a - b

fun nullSafeMinus(a: Double?, b: Double?): Double? =
    if (a == null || b == null) null else a - b

fun nullSafeMultiply(a: Int?, b: Int?): Int? =
    if (a == null || b == null) null else a * b

fun nullSafeMultiply(a: Long?, b: Long?): Long? =
    if (a == null || b == null) null else a * b

fun nullSafeMultiply(a: Double?, b: Double?): Double? =
    if (a == null || b == null) null else a * b

fun nullSafeDivide(a: Int?, b: Int?): Int? =
    if (a == null || b == null || b == 0) null else a / b

fun nullSafeDivide(a: Long?, b: Long?): Long? =
    if (a == null || b == null || b == 0L) null else a / b

fun nullSafeDivide(a: Double?, b: Double?): Double? =
    if (a == null || b == null || b == 0.0) null else a / b

// ==================== Wrapping and unwrapping ====================

fun <T> wrap(value: T?): Wrapper<T> = Wrapper(value)

fun <T> wrapIfNotNull(value: T?): Wrapper<T> =
    Wrapper(if (value == null) null else value)

data class Wrapper<T>(val value: T?) {
    fun isPresent(): Boolean = value != null
    fun isEmpty(): Boolean = value == null

    fun get(): T = value ?: throw NoSuchElementException("No value present")
    fun orNull(): T? = value
    fun orElse(other: T): T = value ?: other
    fun orElseGet(supplier: Supplier<T>): T = value ?: supplier.get()
    fun orElseThrow(exceptionSupplier: Supplier<Throwable>): T =
        value ?: throw exceptionSupplier.get()

    fun <R> map(mapper: Function<T, R>): Wrapper<R> =
        Wrapper(if (value == null) null else mapper.apply(value))

    fun <R> map(mapper: (T) -> R): Wrapper<R> =
        Wrapper(value?.let(mapper))

    fun <R> flatMap(mapper: Function<T, Wrapper<R>>): Wrapper<R> =
        if (value == null) Wrapper(null) else mapper.apply(value)

    fun <R> flatMap(mapper: (T) -> Wrapper<R>): Wrapper<R> =
        if (value == null) Wrapper(null) else mapper(value)

    fun filter(predicate: Predicate<T>): Wrapper<T> =
        if (value == null || predicate.test(value)) this else Wrapper(null)

    fun filter(predicate: (T) -> Boolean): Wrapper<T> =
        if (value == null || predicate(value)) this else Wrapper(null)

    fun ifPresent(action: Consumer<T>): Wrapper<T> {
        if (value != null) action.accept(value)
        return this
    }

    fun ifPresent(action: (T) -> Unit): Wrapper<T> {
        value?.let(action)
        return this
    }

    fun ifEmpty(action: Runnable): Wrapper<T> {
        if (value == null) action.run()
        return this
    }

    fun ifEmpty(action: () -> Unit): Wrapper<T> {
        if (value == null) action()
        return this
    }

    fun <R> fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
        if (value == null) ifEmpty() else ifPresent(value)

    fun peek(action: (T?) -> Unit): Wrapper<T> {
        action(value)
        return this
    }
}
