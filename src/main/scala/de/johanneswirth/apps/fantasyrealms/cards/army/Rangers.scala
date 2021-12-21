package de.johanneswirth.apps.fantasyrealms.cards.army

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Rangers extends Card {
  override val name: String = "Rangers"
  setSuit(Suit.Army)
  override val basePoints: Int = 5
  override val id: String = "FR25"


  override protected def calcBonus(hand: List[Card]): Int = {
    10 * countSuit(hand, Suit.Land)
  }

  override def doClears(hand: List[Card]): Unit = {
    hand.foreach(_.clearArmyFromPenalty = true)
  }
}
