package net.moznion.play.pathvariables

import org.scalatest.FlatSpec

class PathVariablesExtractorTest extends FlatSpec {
  "path that has single placeholder" should "be extract a value" in {
    val got = PathVariablesExtractor.extract("/foo/bar/$buz<[^/]+>", "/foo/bar/XXX")

    assert(got == Map("buz" -> "XXX"))
  }

  "path that has multiple placeholders" should "be extract multi values" in {
    val got = PathVariablesExtractor.extract("/foo/$bar<[^/]+>/$buz<[^/]+>/$foo_bar-buz<[^/]+>", "/foo/bar/buz/foo_bar-buz")

    assert(
      got == Map(
        "bar" -> "bar",
        "buz" -> "buz",
        "foo_bar-buz" -> "foo_bar-buz"
      )
    )
  }
}
