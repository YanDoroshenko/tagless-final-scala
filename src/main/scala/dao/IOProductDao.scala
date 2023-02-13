package dao

import model.Product
import cats.effect.IO

class IOProductDao extends ProductDao[IO] {
  override def findProduct(id: String): IO[Option[Product]] = IO.pure(Some(Product("1", "product1", 1)))
}
