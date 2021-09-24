# northside-io-java
File/Stream comparison utility library for Java

## Usage

There are 2 original methods in this library.

- tokyo.northside.io.FileUtils2.contentEquals(File firstFile, File secondFile, long offset, long length)
- tokyo.northside.io.IOUtils2.contentEquals(InputSteam firstInput, InputStream secondInput, long offset, long length)

These are natural extension from Apache commons IO to add offset and length to be compared.
If you want to compare an entire content of both file, you can use commons-io.
An original methods in Commons-IO are:

- org.apache.commons.io.FileUtils.contentEquals(File firstFile, File secondFile)
- org.apache.commons.io.IOUtils.contentEquals(InputStream firstInput, InputStream secondInput)

Because FileUtils2 and IOUtils2 are extended from commons-io classes,
you can use this library by simple import and use it instead of original one.

```
import tokyo.northside.io.FileUtils2;
import tokyo.northside.io.IOUtils2;
```


## Build

IO utility library for java uses Gradle for build system.

```
$ gradle build
```

You will find a jar file at

```
build/libs/northside-io.jar
```

## Publish
### localMaven
To publish to local maven repository,

```
$ gradle uploadArchive
```

## Contribution

As usual of other projects hosted on GitHub, this project also welcome
forking source and send modification as a Pull Request.
It is recommended to post an issue before sending a patch.


## Copyrights and License

Copyright (C) 2016 Hiroshi Miura

This program distributed under Apache 2.0 license.
