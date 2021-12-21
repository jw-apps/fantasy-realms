package de.johanneswirth.apps.fantasyrealms.cards.land

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class EarthElemental extends Card {
  override val name: String = "Earth Elemental"
  setSuit(Suit.Land)
  override val basePoints: Int = 4
  override val id: String = "FR05"

  override protected def calcBonus(hand: List[Card]): Int = {
    15 * countSuit(removeSelf(hand), Suit.Land)
  }
}
