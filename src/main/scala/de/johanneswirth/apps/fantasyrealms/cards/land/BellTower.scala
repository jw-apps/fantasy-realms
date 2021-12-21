package de.johanneswirth.apps.fantasyrealms.cards.land

import de.johanneswirth.apps.fantasyrealms.cards.land.BellTower.BELL_TOWER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class BellTower extends Card {
  override val name: String = BELL_TOWER
  setSuit(Suit.Land)
  override val basePoints: Int = 8
  override val id: String = "FR03"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsSuit(hand, Suit.Wizard))
      15
    else
      0
  }
}

object BellTower {
  def BELL_TOWER = "Bell Tower"
}
