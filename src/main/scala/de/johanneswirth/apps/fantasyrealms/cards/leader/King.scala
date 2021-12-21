package de.johanneswirth.apps.fantasyrealms.cards.leader

import de.johanneswirth.apps.fantasyrealms.cards.leader.King.KING
import de.johanneswirth.apps.fantasyrealms.cards.leader.Queen.QUEEN
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class King extends Card {
  override val name: String = KING
  setSuit(Suit.Leader)
  override val basePoints: Int = 8
  override val id: String = "FR31"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, QUEEN))
      20 * countSuit(hand, Suit.Army)
    else
      5 * countSuit(hand, Suit.Army)
  }
}

object King {
  def KING = "King"
}
