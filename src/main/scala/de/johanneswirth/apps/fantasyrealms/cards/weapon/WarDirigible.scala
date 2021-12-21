package de.johanneswirth.apps.fantasyrealms.cards.weapon

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class WarDirigible extends Card {
  override val name: String = "War Dirigible"
  setSuit(Suit.Weapon)
  override val basePoints: Int = 35
  override val id: String = "FR45"

  protected override def blankSelf(hand: List[Card]): Unit = {
    if (!(clearArmyFromPenalty || containsSuit(hand, Suit.Army)) || containsSuit(hand, Suit.Weather)) blanked = true
  }
}
