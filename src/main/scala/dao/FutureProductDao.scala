package dao

import cats.effect.IO
import model.Product

import scala.concurrent.Future

class FutureProductDao extends ProductDao[Future] {
  override def findProduct(id: String): Future[Option[Product]] = Future.successful(Some(Product("2", "product2", 2)))
}
