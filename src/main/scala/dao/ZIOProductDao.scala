package dao

import zio.Task
import zio.ZIO
import model.Product

class ZIOProductDao extends ProductDao[Task] {
  override def findProduct(id: String): Task[Option[Product]] = ZIO.succeed(Some(Product("3", "product3", 3)))
}
