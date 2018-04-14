package net.moznion.play.pathvariables

import scala.util.matching.Regex

/**
  * PathVariablesExtractor owes the responsibility to extract path variables from path according to path pattern.
  */
object PathVariablesExtractor {
  private val placeholderLeafPattern = """\$([^<]+)<([^>]+)>""".r

  /**
    * Extracts path variables from path according to path patterns.
    *
    * Usage example of Play 2.6:
    *
    * {{{
    * import play.api.mvc.RequestHeader
    * import play.api.routing.Router
    *
    * def handle(requestHeader: RequestHeader) {
    *   val placeholder2value = PathVariablesExtractor.extract(
    *     requestHeader.attrs(Router.Attrs.HandlerDef).path,
    *     requestHeader.path
    *   )
    *   // do something
    * }
    * }}}
    *
    * @param routePattern A path pattern of the Play framework that contains placeholder(s) (e.g. `/foo/$bar<[^/]+>/$buz<[^/]+>`)
    * @param actualPath   An actual path that is contained in request (e.g. `/foo/Bar/BUZ`)
    * @return A map of placeholder to the value
    */
  def extract(routePattern: String, actualPath: String): Map[String, String] = {
    var names: Array[String] = Array()
    val pattern = placeholderLeafPattern.replaceAllIn(routePattern, (m) => {
      names :+= m.group(1) // NOTE: this is not a functional way, but for reducing redundant iteration
      s"(${m.group(2)})"
    })

    val regex = new Regex(pattern, names: _*)
    regex.findFirstMatchIn(actualPath).map(m => names.zip(m.subgroups).toMap).getOrElse(Map.empty)
  }
}
