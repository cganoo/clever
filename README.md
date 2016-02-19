# clever

An easy way to access [Clever API](https://clever.com/developers/docs/explorer#api_data) in Java

## How to use this?

Satisfy dependencies on the following:

* [Git](http://git-scm.com/downloads)
* [Java SE 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Gradle](https://gradle.org/)

Checkout the code and run it:

1. `git clone https://github.com/cganoo/clever.git`
2. `cd clever/`
3. `gradle run`: This will query the Clever API every 60 seconds, and print out the average number of students per section across all districts

## Highlights
* Uses [Retrofit](http://square.github.io/retrofit/) with an underlying [OkHttp](http://square.github.io/okhttp/) client to expose the Clever API as easy-to-use Java interfaces
* Guice used for managing object graph and DI

## Limitations
* Currently only implements the <code>GET /districts</code> and <code>GET /districts/{id}/sections</code> APIs

## Todos
* Unit tests, integration tests
* Add more error handling and flexible retry strategies

## License

This work is licensed under the MIT license. It is primarily intended for fun and instructive purposes.
Use this at your own risk.
