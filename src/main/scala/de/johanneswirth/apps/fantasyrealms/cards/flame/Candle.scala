package de.johanneswirth.apps.fantasyrealms.cards.flame

import de.johanneswirth.apps.fantasyrealms.cards.artifact.BookOfChanges.BOOK_OF_CHANGES
import de.johanneswirth.apps.fantasyrealms.cards.land.BellTower.BELL_TOWER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Candle extends Card {
  override val name: String = "Candle"
  setSuit(Suit.Flame)
  override val basePoints: Int = 2
  override val id: String = "FR17"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, BOOK_OF_CHANGES) && containsCard(hand, BELL_TOWER) && containsSuit(hand, Suit.Wizard))
      100
    else
      0
  }
}
