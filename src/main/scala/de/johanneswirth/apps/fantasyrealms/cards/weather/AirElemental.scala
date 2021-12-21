package de.johanneswirth.apps.fantasyrealms.cards.weather

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class AirElemental extends Card {
  override val name: String = "Air Elemental"
  setSuit(Suit.Weather)
  override val basePoints: Int = 4
  override val id: String = "FR15"

  override protected def calcBonus(hand: List[Card]): Int = {
    15 * countSuit(removeSelf(hand), Suit.Weather)
  }
}
