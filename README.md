# Gatling-UDP

Unofficial [Gatling](http://gatling.io/) Udp Sender.
A very simple fire-and-forget UDP sender for Gatling.

Tested on versions

* Gatling: **2.2.2**
* Scala: **2.11.8**

## Restrictions
* The only "measurement" that is being captured is the time from send datagram -> close socket.
* Will not take care of UDP responses, simply fire and forget.
* Not optimised for performance.
* Will always return OK to Gatling.
* Send method will only be able to process String or Array[Byte]

## Usage

### Creating a jar file

    $ sbt assembly

To change the Gatling version, change the following line in [`build.sbt`](build.sbt):

```scala
"io.gatling" % "gatling-core" % "2.2.2" % "provided"
```

and run `sbt assembly`.

### Publish to local m2 repository

    $ sbt publishM2

### Publish to local ivy repository

    $ sbt publishLocal

### Scenario Example

* udp("RequestName") RequestName that will be shown in the report.
* sendUnconnected("Message") UDP Message to be sent as a String or Array[Byte].

```scala
import io.github.gatling.udp.Predef._

val udpConf = udp
    .address("localhost")
    .port(1234)

val scn = scenario("Udp Test")
    .exec(
        udp("Send").sendUnconnected("Testing")
    )

setUp(scn.inject(atOnceUsers(50))).protocols(udpConf)
```

## TODO

* Add Checks
* Implement Bind (and Send)
* Implement Connected UDP