# KotlinBridge

A tiny Kotlin library that exposes Kotlin-stdlib-like helper functions to **Java** in a clean way.

Java usage:

```java
import static com.androidmeda.kotlinbridge.KotlinBridge.*;

if (isNullOrBlank(name)) return;
int x = tryOrDefault(0, () -> Integer.parseInt(name));
```

This repo contains:
- `:kotlinbridge-core` — the library (pure JVM, Android-friendly).
- `:sample-android` — a small Android app to manually verify behavior on-device.

## Build
- Open the project in Android Studio
- Run the `sample-android` app

## Notes
The library avoids `FooKt` by compiling all top-level functions into a single Java facade class named `KotlinBridge`
(using `@file:JvmName("KotlinBridge")`).
