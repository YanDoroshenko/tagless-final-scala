package infra

import cats.Monad

trait Logger[M[_]] {
  def info(message: String): M[String]
}
