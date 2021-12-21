package de.johanneswirth.apps.fantasyrealms.cards.leader

import de.johanneswirth.apps.fantasyrealms.cards.leader.King.KING
import de.johanneswirth.apps.fantasyrealms.cards.leader.Queen.QUEEN
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Queen extends Card {
  override val name: String = QUEEN
  setSuit(Suit.Leader)
  override val basePoints: Int = 6
  override val id: String = "FR32"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, KING))
      20 * countSuit(hand, Suit.Army)
    else
      5 * countSuit(hand, Suit.Army)
  }
}

object Queen {
  def QUEEN = "Queen"
}
