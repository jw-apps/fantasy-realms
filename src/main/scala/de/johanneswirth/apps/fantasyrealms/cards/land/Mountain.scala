package de.johanneswirth.apps.fantasyrealms.cards.land

import de.johanneswirth.apps.fantasyrealms.cards.flame.Wildfire.WILDFIRE
import de.johanneswirth.apps.fantasyrealms.cards.land.Mountain.MOUNTAIN
import de.johanneswirth.apps.fantasyrealms.cards.weather.Smoke.SMOKE
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Mountain extends Card {
  override val name: String = MOUNTAIN
  setSuit(Suit.Land)
  override val basePoints: Int = 9
  override val id: String = "FR01"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, SMOKE) && containsCard(hand, WILDFIRE))
      50
    else
      0
  }

  override def doClears(hand: List[Card]): Unit = hand.filter(_.getSuit == Suit.Flood).foreach(_.clearPenalty = true)
}

object Mountain {
  def MOUNTAIN = "Mountain"
}
