package de.johanneswirth.apps.fantasyrealms.cards.beast

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Warhorse extends Card {
  override val name: String = "Warhorse"
  setSuit(Suit.Beast)
  override val basePoints: Int = 6
  override val id: String = "FR38"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsSuit(hand, Suit.Leader) || containsSuit(hand, Suit.Wizard))
      14
    else
      0
  }
}
