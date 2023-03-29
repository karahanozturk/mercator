case class InventoryInfo(name: String, priceInPounds: Double)

case class CartItem(inventoryInfo: InventoryInfo, count: Int)