
import cats.Id
import cats.effect.{ExitCode, IO, IOApp}
import cats.instances.future
import dao.{FutureProductDao, IOProductDao}
import infra.implicits.*
import infra.{ConsoleLogger, RemoteLogger}
import service.ProductServiceImpl

import scala.annotation.unused
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.Future
import scala.concurrent.duration.*

object FutureApp {

  @main
  def main(): Unit = {
    Await.result({
      new ProductServiceImpl[Future, Id, Future](
        new FutureProductDao(),
        new ConsoleLogger()
      )
        .findProduct("1")
    }, 5.seconds)
  }
}
