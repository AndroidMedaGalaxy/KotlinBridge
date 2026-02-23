# KotlinBridge

A tiny Kotlin library that exposes Kotlin-stdlib-like helper functions to **Java** in a clean way.

## Features

KotlinBridge provides Java-friendly static methods organized into logical categories:

- **Null Safety** — null checks, elvis operators, and safe accessors
- **String Extensions** — string checks, transformations, and utilities
- **Collection Extensions** — list, set, and map operations

## Installation

### Maven (GitHub Packages)

Add the GitHub Packages repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/AndroidmedaGalaxy/KotlinBridge</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.androidmeda.kotlinbridge</groupId>
    <artifactId>kotlinbridge-core</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

### Null Safety

```java
import static com.androidmeda.kotlinbridge.nullsafety.NullSafety.*;

// Null checks
if (isNull(value)) return;
String name = requireNotNull(maybeName);

// Elvis-style defaults
String value = orElseValue(maybeValue, "default");
String lazy = orElseGet(maybeValue, () -> computeDefault());
String orThrow = orElseThrow(maybeValue, () -> new IllegalStateException());
```

### String Extensions

```java
import static com.androidmeda.kotlinbridge.strings.StringExtensions.*;

// Null-safe checks
if (isNotNullOrBlank(name)) return;
if (startsWith(name, "prefix")) return;

// Transformations
String trimmed = orEmpty(trim(maybeString));
String lower = toLowerCase(name, Locale.US);
```

### Collection Extensions

```java
import static com.androidmeda.kotlinbridge.collections.CollectionExtensions.*;

// Safe collection operations
List<String> safe = emptyIfNull(maybeList);
String item = getOrDefault(list, 0, "default");
int size = sizeOrZero(maybeList);
```

## Project Structure

- `:kotlinbridge-core` — the library (pure JVM, Android-friendly)
- `:sample-android` — a small Android app to manually verify behavior on-device

## Development

### Build

```bash
./gradlew build
```

### Code Quality

The project uses [Detekt](https://detekt.dev/) and [ktlint](https://pinterest.github.io/ktlint/) for code quality:

```bash
# Run Detekt
java -jar detekt-cli.jar --config config/detekt/detekt.yml --input kotlinbridge-core/src/main/kotlin

# Run ktlint
ktlint kotlinbridge-core/src/**/*.kt

# Auto-format with ktlint
ktlint -F kotlinbridge-core/src/**/*.kt
```

### Publishing

Publish to GitHub Packages:

```bash
./gradlew publish
```

Requires `GITHUB_ACTOR` and `GITHUB_TOKEN` environment variables, or `gpr.user`/`gpr.key` Gradle properties.

## Notes

Each extension category uses its own `@file:JvmName` annotation to provide clean static import targets:
- `NullSafety` — null safety helpers
- `StringExtensions` — string utilities
- `CollectionExtensions` — collection operations

This avoids the messy `FooKt` naming that Kotlin generates by default.
