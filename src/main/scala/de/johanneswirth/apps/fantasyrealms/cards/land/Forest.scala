package de.johanneswirth.apps.fantasyrealms.cards.land

import de.johanneswirth.apps.fantasyrealms.cards.army.ElvenArchers.ELVEN_ARCHERS
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Forest extends Card {
  override val name: String = "Forest"
  setSuit(Suit.Land)
  override val basePoints: Int = 7
  override val id: String = "FR04"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, ELVEN_ARCHERS))
      12 + 12 * countSuit(hand, Suit.Beast)
    else
      12 * countSuit(hand, Suit.Beast)
  }
}
