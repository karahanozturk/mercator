import OfferType.{Buy1_Get1Free, PriceOf2_For3}
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

    it("should calculate checkout cost when all items exist in inventory and items have no offers") {
      val itemInventory = ItemInventory(List(
        InventoryInfo("Apple", 0.6),
        InventoryInfo("Orange", 0.25)
      ))
      val itemNamesAdded = List("Apple", "Apple", "Orange", "Apple")

      val totalCost = ShoppingCart.calculateCheckoutCost(itemInventory, itemNamesAdded)

      totalCost should be(Success(2.05))
    }

    it("should calculate checkout cost when items have offers") {
      val itemInventory = ItemInventory(List(
        InventoryInfo("Apple", 0.6, Some(Buy1_Get1Free)),
        InventoryInfo("Orange", 0.25, Some(PriceOf2_For3))
      ))
      val totalCostOf3Apple1Orange = ShoppingCart.calculateCheckoutCost(itemInventory,
        List("Apple", "Apple", "Orange", "Apple"))

      totalCostOf3Apple1Orange should be(Success(1.45))

      val totalCostOf2Apple4Orange = ShoppingCart.calculateCheckoutCost(itemInventory,
        List("Orange", "Apple", "Apple", "Orange", "Orange", "Orange"))

      totalCostOf2Apple4Orange should be(Success(1.35))
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
