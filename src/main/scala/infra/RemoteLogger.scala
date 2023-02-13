package infra

import scala.concurrent.{ExecutionContext, Future}

class RemoteLogger(implicit ec: ExecutionContext) extends Logger[Future] {
  override def info(message: String): Future[String] = Future {
    println(s"Remote logger: $message")
    message
  }
}
