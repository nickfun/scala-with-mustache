package gs.nick

import scala.collection.JavaConverters.mapAsJavaMap
import java.io.{OutputStreamWriter, StringReader, StringWriter, Writer}

import com.github.mustachejava.{DefaultMustacheFactory, MustacheFactory}


object App {

  class Feature(description: String) {
    def getDescription() = description
  }

  val scopes = Map(
    "name" -> "Mustache",
    "feature" -> mapAsJavaMap(Map("description" -> "Perfect!"))
  )

  val template: String = "{{name}}, {{feature.description}}!"

  def main(args : Array[String]) {

    val jmap = mapAsJavaMap(scopes)

    val writer: Writer = new StringWriter()
    val mf: MustacheFactory = new DefaultMustacheFactory()
    val mustache = mf.compile(new StringReader(template), "example")
    mustache.execute(writer, jmap)
    println("\ntemplate: " + template + "\n  output: " + writer.toString)
  }

}

