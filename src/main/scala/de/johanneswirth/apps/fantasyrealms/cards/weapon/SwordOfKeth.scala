package de.johanneswirth.apps.fantasyrealms.cards.weapon

import de.johanneswirth.apps.fantasyrealms.cards.artifact.ShieldOfKeth.SHIELD_OF_KETH
import de.johanneswirth.apps.fantasyrealms.cards.weapon.SwordOfKeth.SWORD_OF_KETH
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class SwordOfKeth extends Card {
  override val name: String = SWORD_OF_KETH
  setSuit(Suit.Weapon)
  override val basePoints: Int = 7
  override val id: String = "FR43"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, SHIELD_OF_KETH) && containsSuit(hand, Suit.Leader))
      40
    else if (containsSuit(hand, Suit.Leader))
      10
    else
      0
  }
}

object SwordOfKeth {
  def SWORD_OF_KETH = "Sword of Keth"
}
