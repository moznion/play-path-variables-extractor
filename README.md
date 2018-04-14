# play-path-variables-extractor [![Build Status](https://travis-ci.org/moznion/play-path-variables-extractor.svg?branch=master)](https://travis-ci.org/moznion/play-path-variables-extractor)

A path extractor of routing pattern for Play framework.

## Description

Play framework doesn't provide any way to acquire correspondence(s) of path placeholder and the value (as of version 2.6.13).
This library provides the function by `PathVariablesExtractor`.

For example, the routing definition is `/foo/:bar/:buz` and the path of actual request is `/foo/Bar/BUZ`, then this library's method returns:

```
Map[String, String](
  "bar" -> "Bar",
  "buz" -> "BUZ",
)
```

## Synopsis

Example of Play framework 2.6.x:

```scala
import play.api.mvc.RequestHeader
import play.api.routing.Router

def handle(requestHeader: RequestHeader) {
  val placeholder2value = PathVariablesExtractor.extract(
    requestHeader.attrs(Router.Attrs.HandlerDef).path,
    requestHeader.path
  )
  // do something
}
```

## Note

Original discussion and solution: https://github.com/playframework/playframework/issues/3378

## Author

moznion (<moznion@gmail.com>)

## License

Apache License 2.0

