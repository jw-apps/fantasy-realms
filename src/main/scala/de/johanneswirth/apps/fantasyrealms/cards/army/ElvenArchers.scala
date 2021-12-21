package de.johanneswirth.apps.fantasyrealms.cards.army

import de.johanneswirth.apps.fantasyrealms.cards.army.ElvenArchers.ELVEN_ARCHERS
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class ElvenArchers extends Card {
  override val name: String = ELVEN_ARCHERS
  setSuit(Suit.Army)
  override val basePoints: Int = 10
  override val id: String = "FR22"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (!containsSuit(hand, Suit.Weather))
      5
    else
      0
  }
}

object ElvenArchers {
  def ELVEN_ARCHERS = "Elven Archers"
}
