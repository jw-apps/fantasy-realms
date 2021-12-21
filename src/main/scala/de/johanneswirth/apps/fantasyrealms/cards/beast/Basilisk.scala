package de.johanneswirth.apps.fantasyrealms.cards.beast

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Basilisk extends Card {
  override val name: String = "Basilisk"
  setSuit(Suit.Beast)
  override val basePoints: Int = 35
  override val id: String = "FR37"

  def getBlankList: List[Suit] =
    if (clearArmyFromPenalty)
      List(Suit.Leader, Suit.Beast)
    else
      List(Suit.Army, Suit.Leader, Suit.Beast)

  protected override def blanks(hand: List[Card]): Unit = {
    removeSelf(hand).filter(c => getBlankList.contains(c.getSuit)).foreach(_.blanked = true)
  }
}
