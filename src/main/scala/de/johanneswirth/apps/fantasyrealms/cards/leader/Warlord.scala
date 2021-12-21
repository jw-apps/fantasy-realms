package de.johanneswirth.apps.fantasyrealms.cards.leader

import de.johanneswirth.apps.fantasyrealms.cards.leader.Warlord.WARLORD
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Warlord extends Card {
  override val name: String = WARLORD
  setSuit(Suit.Leader)
  override val basePoints: Int = 4
  override val id: String = "FR34"

  override protected def calcBonus(hand: List[Card]): Int = {
    hand.filter(c => !c.blanked && c.getSuit == Suit.Army).map(_.basePoints).sum
  }
}

object Warlord {
  def WARLORD = "Warlord"
}
