package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Jester extends Card {
  override val name: String = "Jester"
  setSuit(Suit.Wizard)
  override val basePoints: Int = 3
  override val id: String = "FR54"

  override protected def calcBonus(hand: List[Card]): Int = {
    val oddCards = removeSelf(hand).filter(!_.blanked).map(_.basePoints).count(_ % 2 == 1)
    if (oddCards == hand.length-1)
      50
    else
      3 * oddCards
  }
}
