package de.johanneswirth.apps.fantasyrealms.cards.leader

import de.johanneswirth.apps.fantasyrealms.cards.leader.Princess.PRINCESS
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Princess extends Card {
  override val name: String = PRINCESS
  setSuit(Suit.Leader)
  override val basePoints: Int = 2
  override val id: String = "FR33"

  override protected def calcBonus(hand: List[Card]): Int = {
    8 * (countSuit(hand, Suit.Army) + countSuit(hand, Suit.Wizard) + countSuit(removeSelf(hand), Suit.Leader))
  }
}

object Princess {
  def PRINCESS = "Princess"
}
