package gs.nick

import scala.collection.JavaConverters.mapAsJavaMap
import java.io.{OutputStreamWriter, StringReader, StringWriter, Writer}

import java.util.{Map => JMap}
import scala.collection.JavaConverters._

import com.github.mustachejava.{DefaultMustacheFactory, MustacheFactory}


object App {

  class Feature(description: String) {
    def getDescription() = description
  }

  def mapToJava[K,V](m: Map[K,V]): JMap[K,Any] = {
    val n = m.mapValues {
      case v: Map[String, Any] => mapToJava(v)
      case v: Any => v
    }.asJava
    n
  }

  val scopes = Map(
    "name" -> "Mustache",
    "feature" -> Map("description" -> "Perfect!"),
    "deep" -> Map(
      "level" -> Map(
        "depth" -> "three",
        "last" -> "true"
      )
    )
  )

  val template: String = "{{name}}, {{feature.description}}! {{deep.level.depth}} and {{deep.level.last}}"

  def main(args : Array[String]) {

    val jmap = mapToJava(scopes)

    val writer: Writer = new StringWriter()
    val mf: MustacheFactory = new DefaultMustacheFactory()
    val mustache = mf.compile(new StringReader(template), "example")
    mustache.execute(writer, jmap)
    println("\ntemplate: " + template + "\n  output: " + writer.toString)
  }

}

