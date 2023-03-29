import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success}

class ShoppingCartTest extends AnyFunSpec with Matchers {

  describe("ShoppingCartTest") {
    it("should should calculate checkout cost as 0 when the cart is empty") {
      val itemInventory = ItemInventory(List(
        InventoryInfo("Apple", 0.6),
        InventoryInfo("Orange", 0.25)
      ))
      val totalCost = ShoppingCart.calculateCheckoutCost(itemInventory, List.empty)

      totalCost should be(Success(0))
    }

    it("should calculate checkout cost when all items exist in inventory") {
      val itemInventory = ItemInventory(List(
        InventoryInfo("Apple", 0.6),
        InventoryInfo("Orange", 0.25)
      ))
      val itemNamesAdded = List("Apple", "Apple", "Orange", "Apple")

      val totalCost = ShoppingCart.calculateCheckoutCost(itemInventory, itemNamesAdded)

      totalCost should be(Success(2.05))
    }

    it("should fail if a non existing item passed to the cart which is not in the inventory") {
      val itemInventory = ItemInventory(List(
        InventoryInfo("Apple", 0.6),
        InventoryInfo("Orange", 0.25)
      ))
      val itemNamesAdded = List("Apple", "Apple", "Orange", "Apple", "Banana", "Sugar")

      val result = ShoppingCart.calculateCheckoutCost(itemInventory, itemNamesAdded)

      result shouldBe a[Failure[Error]]
      result.failed.get.getMessage should be("These added items don't exist in the inventory: [Banana, Sugar]")
    }
  }
}
