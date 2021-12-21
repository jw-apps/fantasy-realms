package de.johanneswirth.apps.fantasyrealms.cards.army

import de.johanneswirth.apps.fantasyrealms.cards.army.DwarvishInfantry.DWARVISH_INFANTRY
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class DwarvishInfantry extends Card {
  override val name: String = DWARVISH_INFANTRY
  setSuit(Suit.Army)
  override val basePoints: Int = 15
  override val id: String = "FR24"

  override protected def calcPenalty(hand: List[Card]): Int = {
    if (clearArmyFromPenalty) 0
    else 2 * countSuit(removeSelf(hand), Suit.Army)
  }
}

object DwarvishInfantry {
  def DWARVISH_INFANTRY = "Dwarvish Infantry"
}
