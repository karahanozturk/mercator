import scala.util.{Failure, Success, Try}

object ShoppingCart {

  private def findItemsExistInInventory(itemInventory: ItemInventory, itemNamesAdded: List[String]) = {
    itemNamesAdded
      .distinct
      .flatMap(itemInventory.findByName)
  }

  private def filterNonExistingItems(itemNamesAdded: List[String], itemsExistInInventory: List[InventoryInfo]) = {
    itemNamesAdded
      .distinct
      .diff(itemsExistInInventory.map(_.name))
  }

  private def createShoppingCart(itemNamesAdded: List[String], itemsExistInInventory: List[InventoryInfo]) = {
    itemsExistInInventory
      .map(inventoryItem =>
        CartItem(inventoryItem, itemNamesAdded.count(_ == inventoryItem.name))
      )
  }

  private def calculateCartCost(cart: List[CartItem]) = {
    cart
      .map(cartItem => cartItem.count * cartItem.inventoryInfo.priceInPounds)
      .sum
  }

  def calculateCheckoutCost(itemInventory: ItemInventory, itemNamesAdded: List[String]): Try[Double] = {
    val itemsExistInInventory = findItemsExistInInventory(itemInventory, itemNamesAdded)
    val nonExistingItems = filterNonExistingItems(itemNamesAdded, itemsExistInInventory)
    val cart = createShoppingCart(itemNamesAdded, itemsExistInInventory)

    if (nonExistingItems.isEmpty) {
      Success(calculateCartCost(cart))
    } else {
      Failure(new Error(s"These added items don't exist in the inventory: [${nonExistingItems.mkString(", ")}]"))
    }
  }
}
