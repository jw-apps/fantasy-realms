package de.johanneswirth.apps.fantasyrealms.cards.beast

import de.johanneswirth.apps.fantasyrealms.cards.beast.Dragon.DRAGON
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Dragon extends Card {
  override val name: String = DRAGON
  setSuit(Suit.Beast)
  override val basePoints: Int = 30
  override val id: String = "FR39"

  override protected def calcPenalty(hand: List[Card]): Int = {
    if (!containsSuit(hand, Suit.Wizard))
      40
    else
      0
  }
}

object Dragon {
  def DRAGON = "Dragon"
}
