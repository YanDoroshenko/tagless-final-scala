package infra

import cats.Id

class ConsoleLogger extends Logger[Id] {
  override def info(message: String): Id[String] = {
    println(s"Console logger: $message")
    message
  }
}
