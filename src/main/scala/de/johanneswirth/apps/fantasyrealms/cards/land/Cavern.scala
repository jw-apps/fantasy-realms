package de.johanneswirth.apps.fantasyrealms.cards.land

import de.johanneswirth.apps.fantasyrealms.cards.army.DwarvishInfantry.DWARVISH_INFANTRY
import de.johanneswirth.apps.fantasyrealms.cards.beast.Dragon.DRAGON
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Cavern extends Card {
  override val name: String = "Cavern"
  setSuit(Suit.Land)
  override val basePoints: Int = 6
  override val id: String = "FR02"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, DWARVISH_INFANTRY) || containsCard(hand, DRAGON))
      25
    else
      0
  }

  override def doClears(hand: List[Card]): Unit = hand.filter(_.getSuit == Suit.Weather).foreach(_.clearPenalty = true)
}
