package de.johanneswirth.apps.fantasyrealms.cards.weapon

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Warship extends Card {
  override val name: String = "Warship"
  setSuit(Suit.Weapon)
  override val basePoints: Int = 23
  override val id: String = "FR41"

  protected override def blankSelf(hand: List[Card]): Unit = {
    if (!containsSuit(hand, Suit.Flood)) blanked = true
  }

  override def doClears(hand: List[Card]): Unit = hand.filter(_.getSuit == Suit.Flood).foreach(_.clearArmyFromPenalty = true)
}
