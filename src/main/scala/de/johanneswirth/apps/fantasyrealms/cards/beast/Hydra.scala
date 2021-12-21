package de.johanneswirth.apps.fantasyrealms.cards.beast

import de.johanneswirth.apps.fantasyrealms.cards.flood.Swamp.SWAMP
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Hydra extends Card {
  override val name: String = "Hydra"
  setSuit(Suit.Beast)
  override val basePoints: Int = 12
  override val id: String = "FR40"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, SWAMP))
      28
    else
      0
  }
}
