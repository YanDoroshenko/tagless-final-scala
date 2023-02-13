package infra

import cats.effect.IO
import cats.Monad
import cats.Id
import cats.~>
import cats.instances.future

import scala.concurrent.Future
import scala.language.implicitConversions
import cats.effect.unsafe.implicits.global
import zio.{Task, Unsafe, ZIO}
import zio.interop.catz.*

object implicits {
  implicit val idToIO: Id ~> IO = new~>[Id, IO] {
    override def apply[A](fa: Id[A]): IO[A] = IO.pure(fa)
  }

  implicit val ioToIO: IO ~> IO = new~>[IO, IO] {
    override def apply[A](fa: IO[A]): IO[A] = fa
  }

  implicit val futureToIO: Future ~> IO = new~>[Future, IO] {
    override def apply[A](fa: Future[A]): IO[A] = IO.fromFuture(IO(fa))
  }

  implicit val futureToFuture: Future ~> Future = new~>[Future, Future] {
    override def apply[A](fa: Future[A]): Future[A] = fa
  }

  implicit val idToFuture: Id ~> Future = new~>[Id, Future] {
    override def apply[A](fa: Id[A]): Future[A] = Future.successful(fa)
  }

  implicit val ioToTask: IO ~> Task = new~>[IO, Task] {
    override def apply[A](fa: IO[A]): Task[A] = ZIO.fromFuture(_ => fa.unsafeToFuture())
  }

  implicit val taskToIO: Task ~> IO = new~>[Task, IO] {
    override def apply[A](fa: Task[A]): IO[A] = IO.fromFuture(IO {
      Unsafe.unsafely {
        zio.Runtime.default.unsafe.runToFuture(fa).future
      }
    })
  }

  implicit val futureToTask: Future ~> Task = new~>[Future, Task] {
    override def apply[A](fa: Future[A]): Task[A] = ZIO.fromFuture(_ => fa)
  }
}
