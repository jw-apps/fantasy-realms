package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class WarlockLord extends Card {
  override val name: String = "Warlock Lord"
  setSuit(Suit.Wizard)
  override val basePoints: Int = 25
  override val id: String = "FR29"

  override protected def calcPenalty(hand: List[Card]): Int = {
    10 * countSuits(removeSelf(hand), List(Suit.Leader, Suit.Wizard))
  }
}
