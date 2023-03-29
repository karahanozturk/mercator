case class InventoryInfo(name: String, priceInPounds: Double, offer: Option[OfferType] = None)

case class CartItem(inventoryInfo: InventoryInfo, count: Int)