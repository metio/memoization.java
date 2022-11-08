<!--
SPDX-FileCopyrightText: The memoization.java Authors
SPDX-License-Identifier: 0BSD
 -->

# memoization.java [![Chat](https://img.shields.io/badge/matrix-%23talk.metio:matrix.org-brightgreen.svg?style=social&label=Matrix)](https://matrix.to/#/#talk.metio:matrix.org)

Java [memoization](https://en.wikipedia.org/wiki/Memoization) library - trade space for time

## Features

* Memoize calls to `Consumer`, `Function`, `Predicate`, `Supplier` and other functional interfaces in `java.util.function`
* Cache values using [Caffeine](https://github.com/ben-manes/caffeine), [Guava](https://github.com/google/guava/wiki/CachesExplained), [cache2k](https://cache2k.org/), or any [`ConcurrentMap`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ConcurrentMap.html).
* Customize caches and the cache keys

## Usage

Memoize any of the supported types by using the static factory methods supplied by:

* `Cache2kMemoize` if you want to use [cache2k](https://cache2k.org/) caches.
* `CaffeineMemoize` if you want to use [Caffeine](https://github.com/ben-manes/caffeine) caches.
* `GuavaMemoize` if you want to use [Guava](https://github.com/google/guava/wiki/CachesExplained) caches.
* `MapMemoize` if you want to use any [`ConcurrentMap`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ConcurrentMap.html) as cache.

### Default cache with default cache keys

```java
wtf.metio.memoization.cache2k.Cache2kMemoize;
wtf.metio.memoization.caffeine.CaffeineMemoize;
wtf.metio.memoization.guava.GuavaMemoize;
wtf.metio.memoization.map.MapMemoize;

// memoize in cache2k cache
Consumer<INPUT> predicate                = ...;
Consumer<INPUT> memoizedPredicate        = Cache2kMemoize.predicate(predicate);

// memoize in Caffeine cache
Consumer<INPUT> consumer                 = ...;
Consumer<INPUT> memoizedConsumer         = CaffeineMemoize.consumer(consumer);

// memoize in Guava cache
Function<INPUT, OUTPUT> function         = ...;
Function<INPUT, OUTPUT> memoizedFunction = GuavaMemoize.function(function);

// memoize in ConcurrentMap
Supplier<OUTPUT> supplier                = ...;
Supplier<OUTPUT> memoizedSupplier        = MapMemoize.supplier(supplier);
```

### Default cache with custom cache keys

```java
wtf.metio.memoization.cache2k.Cache2kMemoize;
wtf.metio.memoization.caffeine.CaffeineMemoize;
wtf.metio.memoization.guava.GuavaMemoize;
wtf.metio.memoization.map.MapMemoize;

// memoize in cache2k cache
Consumer<INPUT> predicate                = ...;
Function<INPUT, KEY> keyFunction         = ...;
Consumer<INPUT> memoizedPredicate        = Cache2kMemoize.predicate(predicate, keyFunction);

// memoize in Caffeine cache
Consumer<INPUT> consumer                 = ...;
Function<INPUT, KEY> keyFunction         = ...;
Consumer<INPUT> memoizedConsumer         = CaffeineMemoize.consumer(consumer, keyFunction);

// memoize in Guava cache
Function<INPUT, OUTPUT> function         = ...;
Function<INPUT, KEY> keyFunction         = ...;
Function<INPUT, OUTPUT> memoizedFunction = GuavaMemoize.function(function, keyFunction);

// memoize in ConcurrentMap
Supplier<OUTPUT> supplier                = ...;
Supplier<KEY> keySupplier                = ...;
Supplier<OUTPUT> memoizedSupplier        = MapMemoize.supplier(supplier, keySupplier);
```

### Custom cache with default cache keys

```java
wtf.metio.memoization.cache2k.Cache2kMemoize;
wtf.metio.memoization.caffeine.CaffeineMemoize;
wtf.metio.memoization.guava.GuavaMemoize;
wtf.metio.memoization.map.MapMemoize;

// memoize in cache2k cache
Consumer<INPUT> predicate                = ...;
Cache<INPUT, INPUT> cache                = ...; // org.cache2k.Cache
Consumer<INPUT> memoizedPredicate        = Cache2kMemoize.predicate(predicate, cache);

// memoize in Caffeine cache
Consumer<INPUT> consumer                 = ...;
Cache<INPUT, INPUT> cache                = ...; // com.github.benmanes.caffeine.cache.Cache
Consumer<INPUT> memoizedConsumer         = CaffeineMemoize.consumer(consumer, cache);

// memoize in Guava cache
Function<INPUT, OUTPUT> function         = ...;
Cache<INPUT, OUTPUT> cache               = ...; // com.google.common.cache.Cache
Function<INPUT, OUTPUT> memoizedFunction = GuavaMemoize.function(function, cache);

// memoize in ConcurrentMap
Supplier<OUTPUT> supplier                = ...;
Map<String, OUTPUT> cache                = ...;
Supplier<OUTPUT> memoizedSupplier        = MapMemoize.supplier(supplier, cache);
```

### Custom cache with custom cache keys

```java
wtf.metio.memoization.cache2k.Cache2kMemoize;
wtf.metio.memoization.caffeine.CaffeineMemoize;
wtf.metio.memoization.guava.GuavaMemoize;
wtf.metio.memoization.map.MapMemoize;

// memoize in cache2k cache
Consumer<INPUT> predicate                = ...;
Function<INPUT, KEY> keyFunction         = ...;
Cache<KEY, INPUT> cache                  = ...; // org.cache2k.Cache
Consumer<INPUT> memoizedPredicate        = Cache2kMemoize.predicate(predicate, keyFunction, cache);

// memoize in Caffeine cache
Consumer<INPUT> consumer                 = ...;
Function<INPUT, KEY> keyFunction         = ...;
Cache<KEY, INPUT> cache                  = ...; // com.github.benmanes.caffeine.cache.Cache
Consumer<INPUT> memoizedConsumer         = CaffeineMemoize.consumer(consumer, keyFunction, cache);

// memoize in Guava cache
Function<INPUT, OUTPUT> function         = ...;
Function<INPUT, KEY> keyFunction         = ...;
Cache<KEY, OUTPUT> cache                 = ...; // com.google.common.cache.Cache
Function<INPUT, OUTPUT> memoizedFunction = GuavaMemoize.function(function, keyFunction, cache);

// memoize in ConcurrentMap
Supplier<OUTPUT> supplier                = ...;
Supplier<KEY> keySupplier                = ...;
Map<KEY, OUTPUT> cache                   = ...;
Supplier<OUTPUT> memoizedSupplier        = MapMemoize.supplier(supplier, keySupplier, cache);
```

Note that `MapMemoize` does accept any `Map`, however copies the entries in the map to a new `ConcurrentHashMap` in case the provided `Map` is not a `ConcurrentMap` as well. This is done in order to ensure atomic `computeIfAbsent` behavior.

## Integration

In order to use this project, declare the following dependencies in your project:

```xml
<dependencies>
    <!-- ConcurrentMap ONLY -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-map</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <!-- ConcurrentMap ONLY -->

    <!-- cache2k ONLY -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-cache2k</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <dependency>
        <groupId>org.cache2k</groupId>
        <artifactId>cache2k-api</artifactId>
        <version>${version.cache2k}</version>
    </dependency>
    <dependency>
        <groupId>org.cache2k</groupId>
        <artifactId>cache2k-core</artifactId>
        <version>${version.cache2k}</version>
        <scope>runtime</scope>
    </dependency>
    <!-- cache2k ONLY -->

    <!-- Caffeine ONLY -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-caffeine</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <dependency>
        <groupId>com.github.ben-manes.caffeine</groupId>
        <artifactId>caffeine</artifactId>
        <version>${version.caffeine}</version>
    </dependency>
    <!-- Caffeine ONLY -->

    <!-- Guava ONLY -->
    <dependency>
        <groupId>wtf.metio.memoization</groupId>
        <artifactId>memoization-guava</artifactId>
        <version>${version.memoization}</version>
    </dependency>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>${version.guava}</version>
    </dependency>
    <!-- Guava ONLY -->

</dependencies>
```

Replace `${version.memoization}` with the latest release. Use [ExpiringMap](https://github.com/jhalterman/expiringmap), [ConcurrentLinkedHashMap](https://github.com/ben-manes/concurrentlinkedhashmap), [Chronicle-Map](https://github.com/OpenHFT/Chronicle-Map), [Cacheonix](http://www.cacheonix.org/) or other `ConcurrentMap` implementations as alternatives to the default `ConcurrentHashMap` used in the `MapMemoize` factory.

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
