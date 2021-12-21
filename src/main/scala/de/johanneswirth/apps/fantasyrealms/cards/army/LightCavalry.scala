package de.johanneswirth.apps.fantasyrealms.cards.army

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class LightCavalry extends Card {
  override val name: String = "Light Cavalry"
  setSuit(Suit.Army)
  override val basePoints: Int = 17
  override val id: String = "FR23"

  override protected def calcPenalty(hand: List[Card]): Int = {
    2 * countSuit(hand, Suit.Land)
  }
}
