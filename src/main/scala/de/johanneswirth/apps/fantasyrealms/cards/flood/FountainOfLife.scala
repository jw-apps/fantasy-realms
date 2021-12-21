package de.johanneswirth.apps.fantasyrealms.cards.flood

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class FountainOfLife extends Card {
  override val name: String = "Fountain of Life"
  setSuit(Suit.Flood)
  override val basePoints: Int = 1
  override val id: String = "FR06"

  override protected def calcBonus(hand: List[Card]): Int = {
    hand.filter(c => !c.blanked && List(Suit.Weapon, Suit.Flood, Suit.Flame, Suit.Land, Suit.Weather).contains(c.getSuit)).map(_.basePoints).max
  }
}
