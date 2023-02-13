package service

import cats.Monad
import dao.ProductDao
import infra.Logger
import model.Product
import cats.syntax.monad._
import cats.~>
import cats.implicits._

trait ProductService[M[_]] {
  def findProduct(id: String): M[Option[Product]]
}

class ProductServiceImpl[
  M1[_]: Monad,
  M2[_]: Monad,
  M[_] : Monad
](dao: ProductDao[M1], logger: Logger[M2])(implicit m1ToM: M1 ~> M, m2ToM: M2 ~> M) extends ProductService[M] {

  override def findProduct(id: String): M[Option[Product]] =
    for {
      _ <- m2ToM(logger.info(s"Getting product - id: $id"))
      maybeProduct <- m1ToM(dao.findProduct(id))
      _ <- m2ToM(logger.info(s"Found: $maybeProduct"))
    } yield maybeProduct
}