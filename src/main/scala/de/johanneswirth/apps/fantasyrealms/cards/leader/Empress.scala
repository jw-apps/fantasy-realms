package de.johanneswirth.apps.fantasyrealms.cards.leader

import de.johanneswirth.apps.fantasyrealms.cards.leader.Empress.EMPRESS
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Empress extends Card {
  override val name: String = EMPRESS
  setSuit(Suit.Leader)
  override val basePoints: Int = 15
  override val id: String = "FR35"

  override protected def calcBonus(hand: List[Card]): Int = 10 * countSuit(hand, Suit.Army)

  override protected def calcPenalty(hand: List[Card]): Int = 5 * countSuit(removeSelf(hand), Suit.Leader)
}

object Empress {
  def EMPRESS = "Empress"
}
