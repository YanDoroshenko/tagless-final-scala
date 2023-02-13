import cats.effect.IO
import dao.IOProductDao
import infra.RemoteLogger
import service.ProductServiceImpl
import zio.{Task, ZIO, ZIOAppDefault}
import zio.interop.catz._
import infra.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ZIOApp extends ZIOAppDefault {
  override def run: ZIO[Any, Any, Any] = {
    new ProductServiceImpl[IO, Future, Task](
      new IOProductDao(),
      new RemoteLogger()
    )
      .findProduct("1")
  }
}
