import OfferType.{Buy1_Get1Free, PriceOf2_For3}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class OfferTypeTest extends AnyFunSpec with Matchers {

  describe("OfferTypeTest") {

    describe("Buy1_Get1Free") {
      it("should count every 2nd item free") {
        val price = 1

        Buy1_Get1Free.calc(0, price) should be(0)
        Buy1_Get1Free.calc(2, price) should be(1)
        Buy1_Get1Free.calc(4, price) should be(2)
        Buy1_Get1Free.calc(10, price) should be(5)
      }

      it("should add price of remaining item when the count cannot be divided by 2") {
        val price = 1

        Buy1_Get1Free.calc(1, price) should be(1)
        Buy1_Get1Free.calc(3, price) should be(2)
        Buy1_Get1Free.calc(5, price) should be(3)
      }
    }

    describe("PriceOf2_For3") {
      it("should count every 3rd item free") {
        val price = 1

        PriceOf2_For3.calc(0, price) should be(0)
        PriceOf2_For3.calc(3, price) should be(2)
        PriceOf2_For3.calc(6, price) should be(4)
        PriceOf2_For3.calc(12, price) should be(8)
      }

      it("should add price of remaining items when the count cannot be divided by 3") {
        val price = 1

        PriceOf2_For3.calc(1, price) should be(1)
        PriceOf2_For3.calc(2, price) should be(2)
        PriceOf2_For3.calc(5, price) should be(4)
      }
    }
  }
}
