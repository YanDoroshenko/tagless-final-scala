
import cats.Id
import infra.implicits.*
import service.ProductServiceImpl
import cats.effect.{ExitCode, IO, IOApp}
import dao.{FutureProductDao, IOProductDao, ZIOProductDao}
import infra.{ConsoleLogger, RemoteLogger}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import cats.instances.future
import zio.Task
import zio.interop.catz._

object IOApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    new ProductServiceImpl[Task, Future, IO](
      new ZIOProductDao(),
      new RemoteLogger()
    )
      .findProduct("1")
      .map(_ => ExitCode.Success)
  }
}
