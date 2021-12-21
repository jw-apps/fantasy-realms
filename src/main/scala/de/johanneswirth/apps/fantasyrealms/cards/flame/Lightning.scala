package de.johanneswirth.apps.fantasyrealms.cards.flame

import de.johanneswirth.apps.fantasyrealms.cards.flame.Lightning.LIGHTNING
import de.johanneswirth.apps.fantasyrealms.cards.weather.Rainstorm.RAINSTORM
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Lightning extends Card {
  override val name: String = LIGHTNING
  setSuit(Suit.Flame)
  override val basePoints: Int = 11
  override val id: String = "FR19"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, RAINSTORM))
      30
    else
      0
  }
}

object Lightning {
  def LIGHTNING = "Lightning"
}
