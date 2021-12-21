package de.johanneswirth.apps.fantasyrealms.cards.weapon

import de.johanneswirth.apps.fantasyrealms.cards.army.ElvenArchers.ELVEN_ARCHERS
import de.johanneswirth.apps.fantasyrealms.cards.leader.Warlord.WARLORD
import de.johanneswirth.apps.fantasyrealms.cards.wizard.Beastmaster.BEASTMASTER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class ElvenLongbow extends Card {
  override val name: String = "Elven Longbow"
  setSuit(Suit.Weapon)
  override val basePoints: Int = 3
  override val id: String = "FR44"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, ELVEN_ARCHERS) || containsCard(hand, WARLORD) || containsCard(hand, BEASTMASTER))
      30
    else
      0
  }
}
