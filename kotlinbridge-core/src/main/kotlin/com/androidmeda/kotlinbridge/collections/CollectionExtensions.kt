@file:JvmName("CollectionExtensions")
package com.androidmeda.kotlinbridge.collections

// ==================== Basic checks ====================

fun isNullOrEmpty(c: Collection<*>?): Boolean = c == null || c.isEmpty()

fun isNullOrEmpty(m: Map<*, *>?): Boolean = m == null || m.isEmpty()

fun isNotNullOrEmpty(c: Collection<*>?): Boolean = !isNullOrEmpty(c)

fun isNotNullOrEmpty(m: Map<*, *>?): Boolean = !isNullOrEmpty(m)

fun sizeOrZero(c: Collection<*>?): Int = c?.size ?: 0

fun sizeOrZero(m: Map<*, *>?): Int = m?.size ?: 0

// ==================== List operations ====================

fun <T> emptyIfNull(list: List<T>?): List<T> = list ?: emptyList()

fun <T> emptyIfNull(set: Set<T>?): Set<T> = set ?: emptySet()

fun <K, V> emptyIfNull(map: Map<K, V>?): Map<K, V> = map ?: emptyMap()

fun <T> nullIfEmpty(list: List<T>?): List<T>? =
    if (list == null || list.isEmpty()) null else list

fun <T> nullIfEmpty(set: Set<T>?): Set<T>? =
    if (set == null || set.isEmpty()) null else set

fun <K, V> nullIfEmpty(map: Map<K, V>?): Map<K, V>? =
    if (map == null || map.isEmpty()) null else map

fun <T> orEmpty(list: List<T>?): List<T> = list ?: emptyList()

fun <T> orEmpty(set: Set<T>?): Set<T> = set ?: emptySet()

fun <K, V> orEmpty(map: Map<K, V>?): Map<K, V> = map ?: emptyMap()

// ==================== List access ====================

fun <T> getOrNull(list: List<T>?, index: Int): T? {
    if (list == null) return null
    return if (index in 0 until list.size) list[index] else null
}

fun <T> getOrDefault(list: List<T>?, index: Int, defaultValue: T): T =
    getOrNull(list, index) ?: defaultValue

fun <T> firstOrNull(list: List<T>?): T? = list?.firstOrNull()

fun <T> firstOrDefault(list: List<T>?, defaultValue: T): T =
    firstOrNull(list) ?: defaultValue

fun <T> lastOrNull(list: List<T>?): T? = list?.lastOrNull()

fun <T> lastOrDefault(list: List<T>?, defaultValue: T): T =
    lastOrNull(list) ?: defaultValue

fun <T> findFirstOrNull(list: List<T>?, predicate: (T) -> Boolean): T? =
    list?.find(predicate)

fun <T> findLastOrNull(list: List<T>?, predicate: (T) -> Boolean): T? =
    list?.findLast(predicate)

fun <T> indexOfOrNull(list: List<T>?, element: T): Int? {
    if (list == null) return null
    val index = list.indexOf(element)
    return if (index >= 0) index else null
}

fun <T> indexOfFirstOrNull(list: List<T>?, predicate: (T) -> Boolean): Int? {
    if (list == null) return null
    val index = list.indexOfFirst(predicate)
    return if (index >= 0) index else null
}

fun <T> indexOfLastOrNull(list: List<T>?, predicate: (T) -> Boolean): Int? {
    if (list == null) return null
    val index = list.indexOfLast(predicate)
    return if (index >= 0) index else null
}

fun <T> getOrElse(list: List<T>?, index: Int, defaultValue: (Int) -> T): T =
    list?.getOrElse(index, defaultValue)
        ?: defaultValue(index)

// ==================== Map access ====================

fun <K, V> getOrNull(map: Map<K, V>?, key: K): V? = map?.get(key)

fun <K, V> getOrDefault(map: Map<K, V>?, key: K, defaultValue: V): V =
    map?.get(key) ?: defaultValue

fun <K, V> getOrPut(mutableMap: MutableMap<K, V>?, key: K, defaultValue: () -> V): V? {
    if (mutableMap == null) return null
    return mutableMap.getOrPut(key, defaultValue)
}

fun <K, V> putIfAbsent(mutableMap: MutableMap<K, V>?, key: K, value: V): V? {
    if (mutableMap == null) return null
    return mutableMap.putIfAbsent(key, value)
}

fun <K, V> removeOrNull(mutableMap: MutableMap<K, V>?, key: K): V? =
    mutableMap?.remove(key)

fun <K, V> computeIfAbsent(mutableMap: MutableMap<K, V>?, key: K, mappingFunction: (K) -> V): V? {
    if (mutableMap == null) return null
    return mutableMap.computeIfAbsent(key, mappingFunction)
}

fun <K, V> computeIfPresent(mutableMap: MutableMap<K, V>?, key: K, remappingFunction: (K, V) -> V): V? {
    if (mutableMap == null) return null
    return mutableMap.computeIfPresent(key, remappingFunction)
}

fun <K, V> compute(mutableMap: MutableMap<K, V>?, key: K, remappingFunction: (K, V?) -> V?): V? {
    if (mutableMap == null) return null
    return mutableMap.compute(key, remappingFunction)
}

// ==================== Map transformations ====================

fun <K, V> keysOrEmpty(map: Map<K, V>?): Set<K> = map?.keys ?: emptySet()

fun <K, V> valuesOrEmpty(map: Map<K, V>?): Collection<V> = map?.values ?: emptyList()

fun <K, V> entriesOrEmpty(map: Map<K, V>?): Set<Map.Entry<K, V>> = map?.entries ?: emptySet()

fun <K, V> containsKey(map: Map<K, V>?, key: K): Boolean = map?.containsKey(key) ?: false

fun <K, V> containsValue(map: Map<K, V>?, value: V): Boolean = map?.containsValue(value) ?: false

fun <K, V, R> mapKeys(map: Map<K, V>?, transform: (Map.Entry<K, V>) -> R): Map<R, V> =
    map?.mapKeys(transform) ?: emptyMap()

fun <K, V, R> mapValues(map: Map<K, V>?, transform: (Map.Entry<K, V>) -> R): Map<K, R> =
    map?.mapValues(transform) ?: emptyMap()

fun <K, V> filterKeys(map: Map<K, V>?, predicate: (K) -> Boolean): Map<K, V> =
    map?.filterKeys(predicate) ?: emptyMap()

fun <K, V> filterValues(map: Map<K, V>?, predicate: (V) -> Boolean): Map<K, V> =
    map?.filterValues(predicate) ?: emptyMap()

fun <K, V> filterEntries(map: Map<K, V>?, predicate: (Map.Entry<K, V>) -> Boolean): Map<K, V> =
    map?.filter(predicate) ?: emptyMap()

// ==================== List transformations ====================

fun <T> reversed(list: List<T>?): List<T> = list?.reversed() ?: emptyList()

fun <T> sorted(list: List<T>?): List<T> where T : Comparable<T> =
    list?.sorted() ?: emptyList()

fun <T> sortedDescending(list: List<T>?): List<T> where T : Comparable<T> =
    list?.sortedDescending() ?: emptyList()

fun <T, R : Comparable<R>> sortedBy(list: List<T>?, selector: (T) -> R?): List<T> =
    list?.sortedBy(selector) ?: emptyList()

fun <T, R : Comparable<R>> sortedByDescending(list: List<T>?, selector: (T) -> R?): List<T> =
    list?.sortedByDescending(selector) ?: emptyList()

fun <T> sortedWith(list: List<T>?, comparator: Comparator<in T>): List<T> =
    list?.sortedWith(comparator) ?: emptyList()

fun <T> distinct(list: List<T>?): List<T> = list?.distinct() ?: emptyList()

fun <T, K> distinctBy(list: List<T>?, selector: (T) -> K): List<T> =
    list?.distinctBy(selector) ?: emptyList()

fun <T> shuffled(list: List<T>?): List<T> = list?.shuffled() ?: emptyList()

fun <T> take(list: List<T>?, n: Int): List<T> =
    list?.take(n.coerceAtLeast(0)) ?: emptyList()

fun <T> takeLast(list: List<T>?, n: Int): List<T> =
    list?.takeLast(n.coerceAtLeast(0)) ?: emptyList()

fun <T> drop(list: List<T>?, n: Int): List<T> =
    list?.drop(n.coerceAtLeast(0)) ?: emptyList()

fun <T> dropLast(list: List<T>?, n: Int): List<T> =
    list?.dropLast(n.coerceAtLeast(0)) ?: emptyList()

fun <T> slice(list: List<T>?, indices: IntRange): List<T> =
    list?.slice(indices) ?: emptyList()

fun <T> slice(list: List<T>?, indices: List<Int>): List<T> =
    list?.slice(indices) ?: emptyList()

fun <T> chunked(list: List<T>?, size: Int): List<List<T>> =
    list?.chunked(size.coerceAtLeast(1)) ?: emptyList()

fun <T> windowed(list: List<T>?, size: Int, step: Int = 1, partialWindows: Boolean = false): List<List<T>> =
    list?.windowed(size.coerceAtLeast(1), step.coerceAtLeast(1), partialWindows) ?: emptyList()

fun <T> flatten(listOfLists: List<List<T>?>?): List<T> {
    if (listOfLists == null) return emptyList()
    return listOfLists.filterNotNull().flatten()
}

// ==================== Filtering ====================

fun <T> filter(list: List<T>?, predicate: (T) -> Boolean): List<T> =
    list?.filter(predicate) ?: emptyList()

fun <T> filterNot(list: List<T>?, predicate: (T) -> Boolean): List<T> =
    list?.filterNot(predicate) ?: emptyList()

fun <T : Any> filterNotNull(list: List<T?>?): List<T> =
    list?.filterNotNull() ?: emptyList()

fun <T> filterIndexed(list: List<T>?, predicate: (Int, T) -> Boolean): List<T> =
    list?.filterIndexed(predicate) ?: emptyList()

fun <T> takeWhile(list: List<T>?, predicate: (T) -> Boolean): List<T> =
    list?.takeWhile(predicate) ?: emptyList()

fun <T> takeLastWhile(list: List<T>?, predicate: (T) -> Boolean): List<T> =
    list?.takeLastWhile(predicate) ?: emptyList()

fun <T> dropWhile(list: List<T>?, predicate: (T) -> Boolean): List<T> =
    list?.dropWhile(predicate) ?: emptyList()

fun <T> dropLastWhile(list: List<T>?, predicate: (T) -> Boolean): List<T> =
    list?.dropLastWhile(predicate) ?: emptyList()

// ==================== Mapping ====================

fun <T, R> map(list: List<T>?, transform: (T) -> R): List<R> =
    list?.map(transform) ?: emptyList()

fun <T, R> mapIndexed(list: List<T>?, transform: (Int, T) -> R): List<R> =
    list?.mapIndexed(transform) ?: emptyList()

fun <T, R> mapNotNull(list: List<T>?, transform: (T) -> R?): List<R> =
    list?.mapNotNull(transform) ?: emptyList()

fun <T, R> flatMap(list: List<T>?, transform: (T) -> Iterable<R>): List<R> =
    list?.flatMap(transform) ?: emptyList()

fun <T, R> flatMapIndexed(list: List<T>?, transform: (Int, T) -> Iterable<R>): List<R> =
    list?.flatMapIndexed(transform) ?: emptyList()

fun <T, K> groupBy(list: List<T>?, keySelector: (T) -> K): Map<K, List<T>> =
    list?.groupBy(keySelector) ?: emptyMap()

fun <T, K, V> groupBy(list: List<T>?, keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> =
    list?.groupBy(keySelector, valueTransform) ?: emptyMap()

fun <T, K> associateBy(list: List<T>?, keySelector: (T) -> K): Map<K, T> =
    list?.associateBy(keySelector) ?: emptyMap()

fun <T, K, V> associateBy(list: List<T>?, keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V> =
    list?.associateBy(keySelector, valueTransform) ?: emptyMap()

fun <T, K, V> associate(list: List<T>?, transform: (T) -> Pair<K, V>): Map<K, V> =
    list?.associate(transform) ?: emptyMap()

fun <T> zip(list1: List<T>?, list2: List<T>?): List<Pair<T, T>> {
    if (list1 == null || list2 == null) return emptyList()
    return list1.zip(list2)
}

fun <T, R, V> zip(list1: List<T>?, list2: List<R>?, transform: (T, R) -> V): List<V> {
    if (list1 == null || list2 == null) return emptyList()
    return list1.zip(list2, transform)
}

fun <T, R> zipWithNext(list: List<T>?, transform: (T, T) -> R): List<R> =
    list?.zipWithNext(transform) ?: emptyList()

// ==================== Aggregation ====================

fun <T> forEach(list: List<T>?, action: (T) -> Unit): Unit {
    list?.forEach(action)
}

fun <T> forEachIndexed(list: List<T>?, action: (Int, T) -> Unit): Unit {
    list?.forEachIndexed(action)
}

fun <T> onEach(list: List<T>?, action: (T) -> Unit): List<T> =
    list?.onEach(action) ?: emptyList()

fun <T> onEachIndexed(list: List<T>?, action: (Int, T) -> Unit): List<T> =
    list?.onEachIndexed(action) ?: emptyList()

fun <T> reduce(list: List<T>?, operation: (T, T) -> T): T? =
    list?.reduce(operation)

fun <T, R> reduce(list: List<T>?, initial: R, operation: (R, T) -> R): R =
    list?.fold(initial, operation) ?: initial

fun <T, R> fold(list: List<T>?, initial: R, operation: (R, T) -> R): R =
    list?.fold(initial, operation) ?: initial

fun <T, R> foldIndexed(list: List<T>?, initial: R, operation: (Int, R, T) -> R): R =
    list?.foldIndexed(initial, operation) ?: initial

fun <T, R> foldRight(list: List<T>?, initial: R, operation: (T, R) -> R): R =
    list?.foldRight(initial, operation) ?: initial

fun <T, R> foldRightIndexed(list: List<T>?, initial: R, operation: (Int, T, R) -> R): R =
    list?.foldRightIndexed(initial, operation) ?: initial

fun <T> all(list: List<T>?, predicate: (T) -> Boolean): Boolean =
    list?.all(predicate) ?: true

fun <T> any(list: List<T>?, predicate: (T) -> Boolean): Boolean =
    list?.any(predicate) ?: false

fun <T> none(list: List<T>?, predicate: (T) -> Boolean): Boolean =
    list?.none(predicate) ?: true

fun <T> count(list: List<T>?): Int = list?.size ?: 0

fun <T> count(list: List<T>?, predicate: (T) -> Boolean): Int =
    list?.count(predicate) ?: 0

fun <T : Comparable<T>> maxOrNull(list: List<T>?): T? = list?.maxOrNull()

fun <T, R : Comparable<R>> maxByOrNull(list: List<T>?, selector: (T) -> R): T? =
    list?.maxByOrNull(selector)

fun <T : Comparable<T>> minOrNull(list: List<T>?): T? = list?.minOrNull()

fun <T, R : Comparable<R>> minByOrNull(list: List<T>?, selector: (T) -> R): T? =
    list?.minByOrNull(selector)

fun <T : Number> sumOfInt(list: List<T>?, selector: (T) -> Int): Int =
    list?.sumOf(selector) ?: 0

fun <T : Number> sumOfLong(list: List<T>?, selector: (T) -> Long): Long =
    list?.sumOf(selector) ?: 0

fun <T : Number> sumOfDouble(list: List<T>?, selector: (T) -> Double): Double =
    list?.sumOf(selector) ?: 0.0

fun <T> averageOfInt(list: List<T>?, selector: (T) -> Int): Double =
    if (list.isNullOrEmpty()) 0.0 else list.map(selector).average()

// ==================== Joining ====================

fun <T> joinToString(
    list: List<T>?,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    limit: Int = -1,
    truncated: String = "...",
    transform: ((T) -> String)? = null
): String =
    list?.joinToString(separator, prefix, postfix, limit, truncated, transform) ?: ""

// ==================== Set operations ====================

fun <T> union(set1: Set<T>?, set2: Set<T>?): Set<T> {
    if (set1 == null) return set2 ?: emptySet()
    if (set2 == null) return set1
    return set1.union(set2)
}

fun <T> intersect(set1: Set<T>?, set2: Set<T>?): Set<T> {
    if (set1 == null || set2 == null) return emptySet()
    return set1.intersect(set2)
}

fun <T> subtract(set1: Set<T>?, set2: Set<T>?): Set<T> {
    if (set1 == null) return emptySet()
    if (set2 == null) return set1
    return set1.subtract(set2)
}

fun <T> minus(set: Set<T>?, element: T): Set<T> = set?.minus(element) ?: emptySet()

fun <T> plus(set: Set<T>?, element: T): Set<T> =
    if (set == null) setOf(element) else set.plus(element)

fun <T> plus(set: Set<T>?, elements: Set<T>?): Set<T> {
    if (set == null) return elements ?: emptySet()
    if (elements == null) return set
    return set.plus(elements)
}

// ==================== Sublist operations ====================

fun <T> subList(list: List<T>?, fromIndex: Int, toIndex: Int): List<T> =
    list?.subList(fromIndex.coerceIn(0, list.size), toIndex.coerceIn(0, list.size)) ?: emptyList()

fun <T> head(list: List<T>?): T? = list?.firstOrNull()

fun <T> tail(list: List<T>?): List<T> =
    list?.drop(1) ?: emptyList()

fun <T> init(list: List<T>?): List<T> =
    list?.dropLast(1) ?: emptyList()

fun <T> last(list: List<T>?): T? = list?.lastOrNull()

// ==================== List creation helpers ====================

fun <T> listOfNotNull(vararg elements: T?): List<T> =
    elements.filterNotNull()

fun <T> setOfNotNull(vararg elements: T?): Set<T> =
    elements.filterNotNull().toSet()

fun <K, V> mapOfNotNull(vararg pairs: Pair<K, V>?): Map<K, V> =
    pairs.filterNotNull().toMap()

fun <T> mutableListOfNotNull(vararg elements: T?): MutableList<T> =
    elements.filterNotNull().toMutableList()

fun <T> mutableSetOfNotNull(vararg elements: T?): MutableSet<T> =
    elements.filterNotNull().toMutableSet()

fun <K, V> mutableMapOfNotNull(vararg pairs: Pair<K, V>?): MutableMap<K, V> =
    pairs.filterNotNull().toMap().toMutableMap()

fun <T> immutableList(list: List<T>?): List<T> =
    list?.toList() ?: emptyList()

fun <T> immutableSet(set: Set<T>?): Set<T> =
    set?.toSet() ?: emptySet()

fun <K, V> immutableMap(map: Map<K, V>?): Map<K, V> =
    map?.toMap() ?: emptyMap()

// ==================== Partitioning ====================

fun <T> partition(list: List<T>?, predicate: (T) -> Boolean): Pair<List<T>, List<T>> =
    list?.partition(predicate) ?: (emptyList<T>() to emptyList<T>())

fun <T, K> groupByToMutable(list: List<T>?, destination: MutableMap<K, MutableList<T>>, keySelector: (T) -> K): MutableMap<K, MutableList<T>> {
    if (list == null) return destination
    return list.groupByTo(destination, keySelector)
}

fun <T, C : MutableCollection<in T>> filterTo(list: List<T>?, destination: C, predicate: (T) -> Boolean): C {
    if (list == null) return destination
    return list.filterTo(destination, predicate)
}

fun <T, C : MutableCollection<in T>> filterNotTo(list: List<T>?, destination: C, predicate: (T) -> Boolean): C {
    if (list == null) return destination
    return list.filterNotTo(destination, predicate)
}

fun <T : Any, C : MutableCollection<in T>> filterNotNullTo(list: List<T?>?, destination: C): C {
    if (list == null) return destination
    return list.filterNotNullTo(destination)
}

fun <T, R, C : MutableCollection<in R>> mapTo(list: List<T>?, destination: C, transform: (T) -> R): C {
    if (list == null) return destination
    return list.mapTo(destination, transform)
}

fun <T, R : Any, C : MutableCollection<in R>> mapNotNullTo(list: List<T>?, destination: C, transform: (T) -> R?): C {
    if (list == null) return destination
    return list.mapNotNullTo(destination, transform)
}

fun <T, R, C : MutableCollection<in R>> flatMapTo(list: List<T>?, destination: C, transform: (T) -> Iterable<R>): C {
    if (list == null) return destination
    return list.flatMapTo(destination, transform)
}
