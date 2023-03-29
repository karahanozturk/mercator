object Main {
  def main(args: Array[String]): Unit = {

    val itemInventory = ItemInventory(List(
      InventoryInfo("Apple", 0.6),
      InventoryInfo("Orange", 0.25)
    ))

    val itemNamesAdded = List("Apple", "Apple", "Orange", "Apple")

    val result = ShoppingCart.calculateCheckoutCost(itemInventory, itemNamesAdded)
    result.fold[Unit](
      t => println(s"Error: ${t.getMessage}"),
      cost => println(s"Total checkout cost: $cost")
    )

  }
}