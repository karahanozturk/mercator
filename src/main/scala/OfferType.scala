sealed abstract class OfferType(val calc: (Int, Double) => Double)

object OfferType {
  case object Buy1_Get1Free extends OfferType((count, price) => (count / 2 + count % 2) * price)
  case object PriceOf2_For3 extends OfferType((count, price) => (count / 3 * 2 + count % 3) * price)
}