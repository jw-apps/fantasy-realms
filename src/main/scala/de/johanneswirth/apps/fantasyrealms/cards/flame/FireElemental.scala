package de.johanneswirth.apps.fantasyrealms.cards.flame

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class FireElemental extends Card {
  override val name: String = "Fire Elemental"
  setSuit(Suit.Flame)
  override val basePoints: Int = 4
  override val id: String = "FR20"

  override protected def calcBonus(hand: List[Card]): Int = {
    15 * countSuit(removeSelf(hand), Suit.Flame)
  }
}
