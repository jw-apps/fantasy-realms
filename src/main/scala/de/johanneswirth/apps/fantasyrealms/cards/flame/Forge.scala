package de.johanneswirth.apps.fantasyrealms.cards.flame

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Forge extends Card {
  override val name: String = "Forge"
  setSuit(Suit.Flame)
  override val basePoints: Int = 9
  override val id: String = "FR18"

  override protected def calcBonus(hand: List[Card]): Int = {
    9 * (countSuit(hand, Suit.Weapon) + countSuit(hand, Suit.Artifact))
  }
}
