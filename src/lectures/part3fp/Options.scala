package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOptions: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOptions)

  // WORK with unsafe APIs
  def unsafeMethod(): String = null
//  val result = Some(null) // WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainResult = betterUnsafeMethod() orElse betterBackupMethod()

  // function on Options
  println(myFirstOptions.isEmpty)
//  println(myFirstOptions.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOptions.map(_ * 2))
  println(myFirstOptions.filter(x => x > 10))
  println(myFirstOptions.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
     Exercise.
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    if (c != null)
       return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))
  println(connectionStatus)
  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
