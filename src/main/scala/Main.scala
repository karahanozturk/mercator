import OfferType.{Buy1_Get1Free, PriceOf2_For3}

object Main {
  def main(args: Array[String]): Unit = {

    val itemInventory = ItemInventory(List(
      InventoryInfo("Apple", 0.6, Some(Buy1_Get1Free)),
      InventoryInfo("Orange", 0.25, Some(PriceOf2_For3))
    ))

    val itemNamesAdded = List("Apple", "Apple", "Orange", "Apple")

    val result = ShoppingCart.calculateCheckoutCost(itemInventory, itemNamesAdded)
    result.fold[Unit](
      t => println(s"Error: ${t.getMessage}"),
      cost => println(s"Total checkout cost: $cost")
    )

  }
}