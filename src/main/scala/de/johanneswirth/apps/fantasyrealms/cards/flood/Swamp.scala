package de.johanneswirth.apps.fantasyrealms.cards.flood

import de.johanneswirth.apps.fantasyrealms.cards.flood.Swamp.SWAMP
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Swamp extends Card {
  override val name: String = SWAMP
  setSuit(Suit.Flood)
  override val basePoints: Int = 18
  override val id: String = "FR07"

  override protected def calcPenalty(hand: List[Card]): Int = {
    3 * (countSuit(hand, Suit.Army) + countSuit(hand, Suit.Flame))
  }
}

object Swamp {
  def SWAMP = "Swamp"
}