<!--
SPDX-FileCopyrightText: The memoization.java Authors
SPDX-License-Identifier: 0BSD
 -->

# memoization.java [![Chat](https://img.shields.io/badge/matrix-%23talk.metio:matrix.org-brightgreen.svg?style=social&label=Matrix)](https://matrix.to/#/#talk.metio:matrix.org)

Java [memoization](https://en.wikipedia.org/wiki/Memoization) library - trade space for time

## Features

* Memoize calls to JDK interfaces like `Consumer`, `Function`, `Predicate`, `Supplier`, and more
* Memoize calls to [jOOL](https://github.com/jOOQ/jOOL) interfaces like `Consumer0..16` and `Function0..16`
* Memoize calls to [lambda](https://github.com/palatable/lambda) interfaces like `Fn0..8`
* Use custom caches like [Caffeine](https://github.com/ben-manes/caffeine), [Guava](https://github.com/google/guava/wiki/CachesExplained), [cache2k](https://cache2k.org/), or any other [`ConcurrentMap`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ConcurrentMap.html).
* Use custom cache keys for fine-tuning

## Usage

Memoize any of the supported types by using the static factory methods supplied by:

* `Memoize` if you want to memoize JDK interfaces.
* `MemoizeJool` if you want to memoize jOOL interfaces.
* `MemoizeLambda` if you want to memoize lambda interfaces.

### Default cache with default cache keys

```java
wtf.metio.memoization.jdk.Memoize;
wtf.metio.memoization.jool.MemoizeJool;
wtf.metio.memoization.jool.MemoizeLambda;

Function<INPUT, OUTPUT> function         = ...;
Function<INPUT, OUTPUT> memoizedFunction = Memoize.function(function);

Supplier<OUTPUT> supplier                = ...;
Supplier<OUTPUT> memoizedSupplier        = Memoize.supplier(supplier);

Function3<T1, T2, T3, OUTPUT> function         = ...;
Function3<T1, T2, T3, OUTPUT> memoizedFunction = MemoizeJool.function3(function);

Fn4<T1, T2, T3, T4, OUTPUT> function         = ...;
Fn4<T1, T2, T3, T4, OUTPUT> memoizedFunction = MemoizeLambda.fn4(function);
```

### Default cache with custom cache keys

```java
wtf.metio.memoization.jdk.Memoize;
wtf.metio.memoization.jool.MemoizeJool;
wtf.metio.memoization.jool.MemoizeLambda;

Function<INPUT, OUTPUT> function         = ...;
Function<INPUT, KEY> keyFunction         = ...;
Function<INPUT, OUTPUT> memoizedFunction = Memoize.function(function, keyFunction);

Supplier<OUTPUT> supplier                = ...;
Supplier<KEY> keySupplier                = ...;
Supplier<OUTPUT> memoizedSupplier        = Memoize.supplier(supplier, keySupplier);

Function3<T1, T2, T3, OUTPUT> function         = ...;
Function3<T1, T2, T3, KEY> keyFunction         = ...;
Function3<T1, T2, T3, OUTPUT> memoizedFunction = MemoizeJool.function3(function, keyFunction);

Fn4<T1, T2, T3, T4, OUTPUT> function         = ...;
Fn4<T1, T2, T3, T4, KEY> keyFunction         = ...;
Fn4<T1, T2, T3, T4, OUTPUT> memoizedFunction = MemoizeLambda.fn4(function, keyFunction);
```

### Custom cache with default cache keys

```java
wtf.metio.memoization.jdk.Memoize;
wtf.metio.memoization.jool.MemoizeJool;
wtf.metio.memoization.jool.MemoizeLambda;

// memoize in cache2k cache
Function<INPUT, OUTPUT> function         = ...;
Cache<INPUT, OUTPUT> cache               = ...; // org.cache2k.Cache
Function<INPUT, OUTPUT> memoizedFunction = Memoize.function(function, cache.asMap());

// memoize in Caffeine cache
Supplier<OUTPUT> supplier                = ...;
Cache<String, OUTPUT> cache              = ...; // com.github.benmanes.caffeine.cache.Cache
Supplier<OUTPUT> memoizedSupplier        = Memoize.supplier(supplier, cache.asMap());

// memoize in Guava cache
Function3<T1, T2, T3, OUTPUT> function         = ...;
Cache<String, OUTPUT> cache                    = ...; // com.google.common.cache.Cache
Function3<T1, T2, T3, OUTPUT> memoizedFunction = MemoizeJool.function3(function, cache.asMap());

// memoize in ConcurrentMap
Fn4<T1, T2, T3, T4, OUTPUT> function         = ...;
Map<String, OUTPUT> cache                    = ...;
Fn4<T1, T2, T3, T4, OUTPUT> memoizedFunction = MemoizeLambda.fn4(function, cache);
```

### Custom cache with custom cache keys

```java
wtf.metio.memoization.jdk.Memoize;
wtf.metio.memoization.jool.MemoizeJool;
wtf.metio.memoization.jool.MemoizeLambda;

// memoize in cache2k cache
Function<INPUT, OUTPUT> function         = ...;
Function<INPUT, KEY> keyFunction         = ...;
Cache<KEY, OUTPUT> cache                 = ...; // org.cache2k.Cache
Function<INPUT, OUTPUT> memoizedFunction = Memoize.function(function, keyFunction, cache.asMap());

// memoize in Caffeine cache
Supplier<OUTPUT> supplier                = ...;
Supplier<KEY> keySupplier                = ...;
Cache<KEY, OUTPUT> cache                 = ...; // com.github.benmanes.caffeine.cache.Cache
Supplier<OUTPUT> memoizedSupplier        = Memoize.supplier(supplier, keySupplier, cache.asMap());

// memoize in Guava cache
Function3<T1, T2, T3, OUTPUT> function         = ...;
Function3<T1, T2, T3, KEY> keyFunction         = ...;
Cache<KEY, OUTPUT> cache                       = ...; // com.google.common.cache.Cache
Function3<T1, T2, T3, OUTPUT> memoizedFunction = MemoizeJool.function3(function, keyFunction, cache.asMap());

// memoize in ConcurrentMap
Fn4<T1, T2, T3, T4, OUTPUT> function         = ...;
Fn4<T1, T2, T3, T4, KEY> keyFunction         = ...;
Map<KEY, OUTPUT> cache                       = ...;
Fn4<T1, T2, T3, T4, OUTPUT> memoizedFunction = MemoizeLambda.fn4(function, keyFunction, cache);
```

Note that `Memoize` and `MemoizeJool` do accept any `Map`, however they copy the entries in the map to a new `ConcurrentHashMap` in case the provided `Map` is not a `ConcurrentMap`. This is done in order to ensure atomic `computeIfAbsent` behavior.

## Integration

In order to use this project, declare the following dependencies in your project:

```xml
<dependencies>
    <!-- support for JDK interfaces -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-jdk</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <!-- support for JDK interfaces -->

    <!-- support for jOOL interfaces -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-jool</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <!-- support for jOOL interfaces -->

    <!-- support for lambda interfaces -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-lambda</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <!-- support for lambda interfaces -->
</dependencies>
```

Replace `${version.memoization}` with the latest release.

## Alternatives

* [Tek271 Memoizer](http://www.tek271.com/software/java/memoizer)
* [GitMemoizer](https://github.com/kelvinguu/gitmemoizer)
* [Spring's `@Cacheable`](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html#cache-annotations-cacheable)
* [Chili's `@Memoize`](https://github.com/marmelo/chili#memoize)
* [Clojure's `(memoize f)`](https://clojuredocs.org/clojure.core/memoize)
* [Groovy's `@Memoized`](http://docs.groovy-lang.org/latest/html/gapi/groovy/transform/Memoized.html)
* [ScalaCache's `memoize`](https://github.com/cb372/scalacache#memoization-of-method-results)
* [Cyclops' `Memoize`](https://github.com/aol/cyclops/tree/master/cyclops)
* [RxMemoization](https://github.com/pakoito/RxMemoization)
* [memoized](https://github.com/jmorwick/memoized)
* [memoizer](https://github.com/ggrandes/memoizer)
* [jcabi's `@Cacheable`](http://aspects.jcabi.com/annotation-cacheable.html)
* [crache](https://github.com/strongh/crache#memoization-client)

## License

```
Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
PERFORMANCE OF THIS SOFTWARE.
```

## Mirrors

- https://github.com/metio/memoization.java
- https://gitlab.com/metio.wtf/memoization.java
- https://bitbucket.org/metio-wtf/memoization.java
- https://codeberg.org/metio.wtf/memoization.java
