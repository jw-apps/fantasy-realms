package de.johanneswirth.apps.fantasyrealms.cards.weapon

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class MagicWand extends Card {
  override val name: String = "Magic Wand"
  setSuit(Suit.Weapon)
  override val basePoints: Int = 1
  override val id: String = "FR42"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsSuit(hand, Suit.Wizard))
      25
    else
      0
  }
}
