package dao

import cats.Monad
import model.Product

trait ProductDao[M[_]] {
  def findProduct(id: String): M[Option[Product]]
}
