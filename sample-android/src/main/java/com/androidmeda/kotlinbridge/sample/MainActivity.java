package com.androidmeda.kotlinbridge.sample;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;
import java.util.regex.Pattern;

import com.androidmeda.kotlinbridge.KotlinBridge;
import com.androidmeda.kotlinbridge.nullsafety.NullSafety;
import com.androidmeda.kotlinbridge.strings.StringExtensions;
import com.androidmeda.kotlinbridge.collections.CollectionExtensions;

public class MainActivity extends AppCompatActivity {

    private StringBuilder report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        report = new StringBuilder();

        demonstrateStringFunctions();
        demonstrateNullSafetyFunctions();
        demonstrateCollectionFunctions();

        TextView tv = new TextView(this);
        tv.setText(report.toString());
        tv.setTextSize(14f);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        tv.setPadding(pad, pad, pad, pad);
        tv.setLineSpacing(0, 1.2f);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(tv);
        setContentView(scrollView);
    }

    private void demonstrateStringFunctions() {
        section("=== StringExtensions Functions ===");

        // Checks
        log("KotlinBridge.isNullOrBlank(\"   \")", KotlinBridge.isNullOrBlank("   "));
        log("isNullOrEmpty(\"\")", KotlinBridge.isNullOrEmpty(""));
        log("isNotNullOrEmpty(\"hello\")", StringExtensions.isNotNullOrEmpty("hello"));
        log("isNotNullOrBlank(\"   \")", StringExtensions.isNotNullOrBlank("   "));
        log("startsWith(\"hello world\", \"hello\", false)", StringExtensions.startsWith("hello world", "hello", false));
        log("endsWith(\"hello world\", \"world\", false)", StringExtensions.endsWith("hello world", "world", false));
        log("contains(\"hello world\", \"llo\", false)", StringExtensions.contains("hello world", "llo", false));
        log("matches(\"123\", \"\\d+\")", StringExtensions.matches("123", "\\d+"));

        // Transformations
        log("orEmpty(null)", StringExtensions.orEmpty(null));
        log("orDefault(null, \"default\")", StringExtensions.orDefault(null, "default"));
        log("trim(\"  hello  \")", StringExtensions.trim("  hello  "));
        log("toLowerCase(\"HELLO\", Locale.US)", StringExtensions.toLowerCase("HELLO", Locale.US));
        log("toUpperCase(\"hello\", Locale.US)", StringExtensions.toUpperCase("hello", Locale.US));
        log("capitalize(\"hello\", Locale.US)", StringExtensions.capitalize("hello", Locale.US));
        log("decapitalize(\"Hello\", Locale.US)", StringExtensions.decapitalize("Hello", Locale.US));

        // Substring operations
        log("take(\"hello\", 3)", StringExtensions.take("hello", 3));
        log("takeLast(\"hello\", 2)", StringExtensions.takeLast("hello", 2));
        log("drop(\"hello\", 2)", StringExtensions.drop("hello", 2));
        log("substringBefore(\"hello.world\", '.', \"default\")", StringExtensions.substringBefore("hello.world", '.', "default"));
        log("substringAfter(\"hello.world\", '.', \"default\")", StringExtensions.substringAfter("hello.world", '.', "default"));
        log("substringBetween(\"[hello]\", \"[\", \"]\")", StringExtensions.substringBetween("[hello]", "[", "]"));

        // Padding
        log("padStart(\"5\", 3, '0')", StringExtensions.padStart("5", 3, '0'));
        log("padEnd(\"5\", 3, '0')", StringExtensions.padEnd("5", 3, '0'));
        log("center(\"hi\", 6, '*')", StringExtensions.center("hi", 6, '*'));

        // Truncation
        log("truncate(\"hello world\", 8, \"...\")", StringExtensions.truncate("hello world", 8, "..."));
        log("ellipsize(\"abcdef\", 4)", StringExtensions.ellipsize("abcdef", 4));
        log("ellipsizeMiddle(\"abcdefgh\", 5, \"...\")", StringExtensions.ellipsizeMiddle("abcdefgh", 5, "..."));

        // Cleaning
        log("removePrefix(\"test.txt\", \"test.\")", StringExtensions.removePrefix("test.txt", "test."));
        log("removeSuffix(\"test.txt\", \".txt\")", StringExtensions.removeSuffix("test.txt", ".txt"));
        log("removeWhitespace(\"h e l l o\")", StringExtensions.removeWhitespace("h e l l o"));
        log("collapseWhitespace(\"  hello   world  \")", StringExtensions.collapseWhitespace("  hello   world  "));

        // Splitting and joining
        log("split(\"a,b,c\", ',', 0)", StringExtensions.split("a,b,c", ',', 0));
        log("lines(\"line1\\nline2\")", StringExtensions.lines("line1\nline2"));
        log("joinToString(items, \", \", \"[\", \"]\")", StringExtensions.joinToString(Arrays.asList(1, 2, 3), ", ", "[", "]", -1, "...", null));
        log("repeat(\"*\", 5)", StringExtensions.repeat("*", 5));

        // Regex
        Pattern pattern = Pattern.compile("\\d+");
        log("matches(\"123\", pattern)", StringExtensions.matches("123", pattern));
        log("find(\"abc123def\", pattern)", StringExtensions.find("abc123def", pattern));
        log("findAll(\"a1b2c3\", pattern)", StringExtensions.findAll("a1b2c3", pattern));

        // Case conversions
        log("toSlug(\"Hello World!!!\")", StringExtensions.toSlug("Hello World!!!"));
        log("toCamelCase(\"hello_world\", \"[_\\\\s-]+\")", StringExtensions.toCamelCase("hello_world", "[_\\s-]+"));
        log("toSnakeCase(\"helloWorld\")", StringExtensions.toSnakeCase("helloWorld"));
        log("toKebabCase(\"hello_world\")", StringExtensions.toKebabCase("hello_world"));
        log("toTitleCase(\"hello world\", Locale.US)", StringExtensions.toTitleCase("hello world", Locale.US));

        // Parsing
        log("toIntOrNull(\"42\")", StringExtensions.toIntOrNull("42"));
        log("toLongOrNull(\"9999999999\")", StringExtensions.toLongOrNull("9999999999"));
        log("toDoubleOrNull(\"3.14\")", StringExtensions.toDoubleOrNull("3.14"));
        log("toFloatOrNull(\"2.5\")", StringExtensions.toFloatOrNull("2.5"));
        log("toBooleanOrNull(\"yes\")", StringExtensions.toBooleanOrNull("yes"));
        log("toBooleanOrNull(\"TRUE\")", StringExtensions.toBooleanOrNull("TRUE"));
        log("toBooleanOrNull(\"maybe\")", StringExtensions.toBooleanOrNull("maybe"));

        // Validation
        log("isAlphanumeric(\"abc123\")", StringExtensions.isAlphanumeric("abc123"));
        log("isNumeric(\"123\")", StringExtensions.isNumeric("123"));
        log("isAlpha(\"abc\")", StringExtensions.isAlpha("abc"));
        log("hasLength(\"hello\", 3, 10)", StringExtensions.hasLength("hello", 3, 10));
        log("equals(\"Hello\", \"hello\", true)", StringExtensions.equals("Hello", "hello", true));
        log("compareTo(\"a\", \"b\", false)", StringExtensions.compareTo("a", "b", false));

        blank();
    }

    private void demonstrateNullSafetyFunctions() {
        section("=== NullSafety Functions ===");

        // Basic null checks
        log("isNull(null)", NullSafety.isNull(null));
        log("isNotNull(\"hello\")", NullSafety.isNotNull("hello"));
        log("requireNotNull(\"value\", \"was null\")", NullSafety.requireNotNull("value", "was null"));
        log("orElseValue(null, \"default\")", NullSafety.orElseValue(null, "default"));
        log("orElseGet(null, () -> \"computed\")", NullSafety.orElseGet(null, () -> "computed"));
        log("orDefault(\"value\", \"default\")", NullSafety.orDefault("value", "default"));

        // Default value helpers
        log("orZero((Integer)null)", NullSafety.orZero((Integer) null));
        log("orZero(42)", NullSafety.orZero(42));
        log("orFalse(null)", NullSafety.orFalse(null));
        log("orTrue(null)", NullSafety.orTrue(null));

        // Safe chaining - use explicit interface to disambiguate
        log("safeLet(\"hello\", (Function<String,String>) s -> s.toUpperCase(Locale.US))", NullSafety.safeLet("hello", (java.util.function.Function<String, String>) s -> s.toUpperCase(Locale.US)));
        log("letOrDefault(null, (Function<String,String>) s -> s.toUpperCase(Locale.US), \"DEFAULT\")", NullSafety.letOrDefault(null, (java.util.function.Function<String, String>) s -> s.toUpperCase(Locale.US), "DEFAULT"));
        log("coalesce(null, null, \"first\", null)", NullSafety.coalesce(null, null, "first", null));

        // Safe casting
        log("safeCast(\"hello\", String.class)", NullSafety.safeCast("hello", String.class));
        log("castOrDefault(123, String.class, \"default\")", NullSafety.castOrDefault(123, String.class, "default"));

        // Null-aware comparison
        log("safeEquals(\"a\", \"a\")", NullSafety.safeEquals("a", "a"));
        log("safeCompareTo(1, 2)", NullSafety.safeCompareTo(1, 2));

        // Null-safe string representation
        log("toString(null)", NullSafety.toString(null));
        log("toStringOrEmpty(null)", NullSafety.toStringOrEmpty(null));

        // Lazy evaluation
        log("lazyOrDefault(() -> 42, 0)", NullSafety.lazyOrDefault((java.util.function.Supplier<Integer>) () -> 42, 0));
        log("lazyOrNull(() -> Integer.parseInt(\"abc\"))", NullSafety.lazyOrNull((java.util.function.Supplier<Integer>) () -> Integer.parseInt("abc")));

        // Try helpers
        log("tryOrDefault(\"default\", () -> Integer.parseInt(\"abc\"))", NullSafety.tryOrDefault("default", () -> Integer.parseInt("abc")));
        log("tryMap(\"123\", Integer::parseInt)", NullSafety.tryMap("123", Integer::parseInt));
        log("tryMap(\"abc\", Integer::parseInt)", NullSafety.tryMap("abc", Integer::parseInt));

        // Take utilities
        log("takeIfNotEmpty(\"hello\")", NullSafety.takeIfNotEmpty("hello"));
        log("takeIfNotEmpty(\"\")", NullSafety.takeIfNotEmpty(""));
        log("takeIfNotBlank(\"   \")", NullSafety.takeIfNotBlank("   "));
        log("takeIfInRange(5, 0, 10)", NullSafety.takeIfInRange(5, 0, 10));
        log("takeIfInRange(15, 0, 10)", NullSafety.takeIfInRange(15, 0, 10));

        // Safe math
        log("nullSafePlus(5, 3)", NullSafety.nullSafePlus(5, 3));
        log("nullSafePlus(5, null)", NullSafety.nullSafePlus(5, (Integer) null));
        log("nullSafeDivide(10, 2)", NullSafety.nullSafeDivide(10, 2));
        log("nullSafeDivide(10, 0)", NullSafety.nullSafeDivide(10, 0));

        // Null-safe boolean logic
        log("nullSafeAnd(true, false)", NullSafety.nullSafeAnd(true, false));
        log("nullSafeOr(true, null)", NullSafety.nullSafeOr(true, null));
        log("nullSafeNot(true)", NullSafety.nullSafeNot(true));

        // Wrapper - use explicit interface to disambiguate
        log("wrap(\"value\").isPresent()", NullSafety.wrap("value").isPresent());
        log("wrap(null).isEmpty()", NullSafety.wrap(null).isEmpty());
        log("wrap(\"hello\").map(s -> s.toUpperCase()).orNull()", NullSafety.wrap("hello").map((java.util.function.Function<String, String>) s -> s.toUpperCase(Locale.US)).orNull());

        blank();
    }

    private void demonstrateCollectionFunctions() {
        section("=== CollectionExtensions Functions ===");

        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        List<Integer> nums = Arrays.asList(5, 2, 8, 1, 9);
        List<String> emptyList = Collections.emptyList();
        List<String> nullList = null;

        // Basic checks
        log("isNullOrEmpty([a,b,c])", CollectionExtensions.isNullOrEmpty(list));
        log("isNullOrEmpty([])", CollectionExtensions.isNullOrEmpty(emptyList));
        log("isNullOrEmpty(null)", CollectionExtensions.isNullOrEmpty(nullList));
        log("isNotNullOrEmpty([a,b,c])", CollectionExtensions.isNotNullOrEmpty(list));
        log("sizeOrZero([a,b,c])", CollectionExtensions.sizeOrZero(list));
        log("sizeOrZero(null)", CollectionExtensions.sizeOrZero(nullList));

        // Safe access
        log("getOrNull([a,b,c], 1)", CollectionExtensions.getOrNull(list, 1));
        log("getOrNull([a,b,c], 10)", CollectionExtensions.getOrNull(list, 10));
        log("getOrDefault([a,b,c], 10, \"default\")", CollectionExtensions.getOrDefault(list, 10, "default"));
        log("firstOrNull([a,b,c])", CollectionExtensions.firstOrNull(list));
        log("firstOrNull(null)", CollectionExtensions.firstOrNull(nullList));
        log("firstOrDefault(null, \"default\")", CollectionExtensions.firstOrDefault(nullList, "default"));
        log("lastOrNull([a,b,c])", CollectionExtensions.lastOrNull(list));
        log("lastOrDefault([a,b,c], \"none\")", CollectionExtensions.lastOrDefault(list, "none"));

        // Safe transformations
        log("emptyIfNull(null)", CollectionExtensions.emptyIfNull(nullList));
        log("orEmpty([a,b,c])", CollectionExtensions.orEmpty(list));
        log("orEmpty(null)", CollectionExtensions.orEmpty(nullList));
        log("nullIfEmpty([a,b,c])", CollectionExtensions.nullIfEmpty(list));
        log("nullIfEmpty([])", CollectionExtensions.nullIfEmpty(emptyList));

        // Filtering
        log("filter([a,b,c,d], s -> s.length() > 4)", CollectionExtensions.filter(list, s -> s.length() > 4));
        log("take([a,b,c,d], 2)", CollectionExtensions.take(list, 2));
        log("takeLast([a,b,c,d], 2)", CollectionExtensions.takeLast(list, 2));
        log("drop([a,b,c,d], 1)", CollectionExtensions.drop(list, 1));
        log("filterNotNull([a,null,b,null])", CollectionExtensions.filterNotNull(Arrays.asList("a", null, "b", null)));

        // Mapping
        log("map([a,b,c], String::toUpperCase)", CollectionExtensions.map(list, s -> s.toUpperCase(Locale.US)));
        log("mapIndexed([a,b,c], (i,s) -> i + \":\" + s)", CollectionExtensions.mapIndexed(list, (i, s) -> i + ":" + s));
        log("mapNotNull([a,b,c,null], s -> s != null ? s.toUpperCase() : null)",
            CollectionExtensions.mapNotNull(Arrays.asList("a", "b", "c", null), s -> s != null ? s.toUpperCase(Locale.US) : null));

        // Sorting
        log("sorted([5,2,8,1,9])", CollectionExtensions.sorted(nums));
        log("sortedDescending([5,2,8,1,9])", CollectionExtensions.sortedDescending(nums));
        log("sortedBy([a,b,c,d], String::length)", CollectionExtensions.sortedBy(list, String::length));

        // Aggregation
        log("distinct([a,a,b,b,c])", CollectionExtensions.distinct(Arrays.asList("a", "a", "b", "b", "c")));
        log("count([a,b,c,d])", CollectionExtensions.count(list));
        log("count([a,b,c,d], s -> s.length() > 4)", CollectionExtensions.count(list, s -> s.length() > 4));
        log("maxOrNull([5,2,8,1,9])", CollectionExtensions.maxOrNull(nums));
        log("minOrNull([5,2,8,1,9])", CollectionExtensions.minOrNull(nums));
        log("sumOfInt([1,2,3,4], x -> x * x)", CollectionExtensions.sumOfInt(nums, x -> x * x));

        // Grouping
        log("groupBy([a,b,c,aa,bb], s -> s.length())", CollectionExtensions.groupBy(list, String::length));
        log("associateBy([a,b,c], s -> s.charAt(0))", CollectionExtensions.associateBy(list, s -> s.charAt(0)));
        log("partition([a,b,c,d], s -> s.length() > 4)", CollectionExtensions.partition(list, s -> s.length() > 4));

        // Map operations
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        log("getOrNull(map, \"one\")", CollectionExtensions.getOrNull(map, "one"));
        log("getOrNull(map, \"missing\")", CollectionExtensions.getOrNull(map, "missing"));
        log("getOrDefault(map, \"missing\", 0)", CollectionExtensions.getOrDefault(map, "missing", 0));
        log("keysOrEmpty(map)", CollectionExtensions.keysOrEmpty(map));
        log("valuesOrEmpty(map)", CollectionExtensions.valuesOrEmpty(map));
        log("isNullOrEmpty(map)", CollectionExtensions.isNullOrEmpty(map));

        // List utilities
        log("reversed([a,b,c])", CollectionExtensions.reversed(list));
        log("chunked([a,b,c,d], 2)", CollectionExtensions.chunked(list, 2));
        log("windowed([a,b,c,d,e], 3, 1, false)", CollectionExtensions.windowed(Arrays.asList("a", "b", "c", "d", "e"), 3, 1, false));
        log("slice([a,b,c,d], [0,2])", CollectionExtensions.slice(list, Arrays.asList(0, 2)));

        // Set operations
        Set<String> set1 = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> set2 = new HashSet<>(Arrays.asList("b", "c", "d"));

        log("union([a,b,c], [b,c,d])", CollectionExtensions.union(set1, set2));
        log("intersect([a,b,c], [b,c,d])", CollectionExtensions.intersect(set1, set2));
        log("subtract([a,b,c], [b,c,d])", CollectionExtensions.subtract(set1, set2));

        // List creation
        log("listOfNotNull(a, null, b, null, c)", CollectionExtensions.listOfNotNull("a", null, "b", null, "c"));
        log("setOfNotNull(a, null, b, null, c)", CollectionExtensions.setOfNotNull("a", null, "b", null, "c"));

        // Functional utilities
        log("all([a,b,c], s -> !s.isEmpty())", CollectionExtensions.all(list, s -> !s.isEmpty()));
        log("any([a,b,c], s -> s.startsWith(\"a\"))", CollectionExtensions.any(list, s -> s.startsWith("a")));
        log("none([a,b,c], s -> s.startsWith(\"z\"))", CollectionExtensions.none(list, s -> s.startsWith("z")));

        // Finding
        log("findFirstOrNull([a,b,c,d], s -> s.startsWith(\"b\"))", CollectionExtensions.findFirstOrNull(list, s -> s.startsWith("b")));
        log("findLastOrNull([a,b,c,d], s -> s.length() > 4))", CollectionExtensions.findLastOrNull(list, s -> s.length() > 4));
        log("indexOfOrNull([a,b,c,d], \"b\")", CollectionExtensions.indexOfOrNull(list, "b"));
        log("indexOfFirstOrNull([a,b,c,d], s -> s.startsWith(\"c\"))", CollectionExtensions.indexOfFirstOrNull(list, s -> s.startsWith("c")));

        // Folding
        log("fold([1,2,3,4], 0, (acc,x) -> acc + x)", CollectionExtensions.fold(nums, 0, (acc, x) -> acc + x));
        log("reduce([1,2,3,4], (a,b) -> a + b)", CollectionExtensions.reduce(nums, (a, b) -> a + b));

        // Head/tail
        log("head([a,b,c])", CollectionExtensions.head(list));
        log("tail([a,b,c])", CollectionExtensions.tail(list));
        log("init([a,b,c])", CollectionExtensions.init(list));
        log("last([a,b,c])", CollectionExtensions.last(list));

        // Zipping
        List<String> list2 = Arrays.asList("1", "2", "3");
        log("zip([a,b,c], [1,2,3])", CollectionExtensions.zip(list, list2));
        log("zipWithNext([a,b,c,d], (a,b) -> a + b)", CollectionExtensions.zipWithNext(list, (a, b) -> a + b));

        // Joining
        log("joinToString([a,b,c], \", \", \"[\", \"]\")", CollectionExtensions.joinToString(list, ", ", "[", "]", -1, "...", null));

        // Shuffling
        log("shuffled([1,2,3,4,5])", CollectionExtensions.shuffled(nums));

        section("=== Demo Complete ===");
    }

    private void section(String title) {
        report.append("\n").append(title).append("\n");
        int len = Math.min(title.length(), 50);
        for (int i = 0; i < len; i++) {
            report.append("-");
        }
        report.append("\n\n");
    }

    private void log(String expression, Object result) {
        report.append(expression).append("\n");
        String resultStr = result == null ? "null" : result.toString();
        // Truncate very long results
        if (resultStr.length() > 60) {
            resultStr = resultStr.substring(0, 57) + "...";
        }
        report.append("  → ").append(resultStr).append("\n\n");
    }

    private void blank() {
        report.append("\n");
    }
}
