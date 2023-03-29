import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ItemInventoryTest extends AnyFunSpec with Matchers {

  private val apple = InventoryInfo("Apple", 0.6)
  private val orange = InventoryInfo("Orange", 0.25)
  val itemInventory = ItemInventory(List(apple, orange))

  describe("ItemInventoryTest") {

    it("should find item inventory info by name when item exists in the inventory") {
      itemInventory.findByName("Apple") should be(Some(apple))
    }

    it("should find item for any upper/lower case in the name") {
      itemInventory.findByName("aPPle") should be(Some(apple))
    }

    it("should return None if the item name is not in the inventory") {
      itemInventory.findByName("Banana") should be(None)
    }
  }
}
