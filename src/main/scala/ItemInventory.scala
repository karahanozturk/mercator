
case class ItemInventory(items: List[InventoryInfo]) {

  def findByName(name: String): Option[InventoryInfo] = items.find(_.name.toLowerCase == name.toLowerCase)

}
