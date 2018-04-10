package gs.nick

object App {
  
  def foo(x : Array[String]): String = x.foldLeft("")((a, b) => a + " " + b)

  def withDashes(x: => Unit): Unit = {
    val b = "=-" * 30
    println("\n\n\n" + b)
    x
    println(b + "\n\n\n")
  }
  
  def main(args : Array[String]) {
    withDashes {
      println(foo(args))
    }
  }

}
