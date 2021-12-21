package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Collector extends Card {
  override val name: String = "Collector"
  setSuit(Suit.Wizard)
  override val basePoints: Int = 7
  override val id: String = "FR26"

  override protected def calcBonus(hand: List[Card]): Int = {
    var bonus = 0
    hand.groupBy(_.getSuit).filter(_._1 != Suit.None).values.map(_.length).foreach{
      case 3 => bonus += 10
      case 4 => bonus += 40
      case 5 => bonus += 100
      case _ => bonus += 0
    }
    bonus
  }
}
