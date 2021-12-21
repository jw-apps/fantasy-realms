package de.johanneswirth.apps.fantasyrealms.cards.artifact

import de.johanneswirth.apps.fantasyrealms.cards.artifact.ShieldOfKeth.SHIELD_OF_KETH
import de.johanneswirth.apps.fantasyrealms.cards.weapon.SwordOfKeth.SWORD_OF_KETH
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class ShieldOfKeth extends Card {
  override val name: String = SHIELD_OF_KETH
  setSuit(Suit.Artifact)
  override val basePoints: Int = 4
  override val id: String = "FR46"

  override protected def calcBonus(hand: List[Card]): Int = {
    val leader = containsSuit(hand, Suit.Leader)
    if (leader && containsCard(hand, SWORD_OF_KETH))
      40
    else if (leader)
      15
    else
      0
  }
}

object ShieldOfKeth {
  def SHIELD_OF_KETH = "Shield of Keth"
}
