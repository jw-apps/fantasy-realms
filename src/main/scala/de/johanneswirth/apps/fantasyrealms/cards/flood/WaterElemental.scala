package de.johanneswirth.apps.fantasyrealms.cards.flood

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class WaterElemental extends Card {
  override val name: String = "Water Elemental"
  setSuit(Suit.Flood)
  override val basePoints: Int = 4
  override val id: String = "FR10"

  override protected def calcBonus(hand: List[Card]): Int = {
    15 * countSuit(removeSelf(hand), Suit.Flood)
  }
}
