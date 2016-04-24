# Change Log
All notable changes to this project will be documented in this file.

## [Unreleased]

## [0.2.0]

New API.

### Add
- Gradle publish script: github release, bintray upload and maven.
- README: Explanation for publish

### Changed
- API change: Package is now 'tokyo.northside.io'
- API change: class names are FileUtils2 and IOUtils2
- Now northside-io classes are extends commons-io.
- Project configuration is placed on gradle.properties
- Private configuration is required in ~/.gradle/gradle.properties
  for github account, bintray account etc.

## [0.1.1]
### Changed
- Project name is now northside-io and package is tokyo.northside.

### Fixed
- Update license preamble section in source file.
- (missing) expose methods as public.

## 0.1.0
### Add
- Start project to provide two convenient methods.

[0.1.1]: https://github.com/miurahr/northside-io-java/compare/v0.1.0...v0.1.1
[0.2.0]: https://github.com/miurahr/northside-io-java/compare/v0.1.1...v0.2.0
[Unreleased]: https://github.com/miurahr/northside-io-java/compare/v0.2.0...HEAD
