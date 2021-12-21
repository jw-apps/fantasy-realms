package de.johanneswirth.apps.fantasyrealms.cards.army

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Knights extends Card {
  override val name: String = "Knights"
  setSuit(Suit.Army)
  override val basePoints: Int = 20
  override val id: String = "FR21"

  override protected def calcPenalty(hand: List[Card]): Int = {
    if (!containsSuit(hand, Suit.Leader))
      8
    else
      0
  }
}
