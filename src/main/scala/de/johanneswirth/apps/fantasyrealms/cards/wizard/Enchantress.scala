package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.wizard.Enchantress.ENCHANTRESS
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Enchantress extends Card {
  override val name: String = ENCHANTRESS
  setSuit(Suit.Wizard)
  override val basePoints: Int = 5
  override val id: String = "FR30"

  override protected def calcBonus(hand: List[Card]): Int = {
    5 * countSuits(hand, List(Suit.Land, Suit.Weather, Suit.Flood, Suit.Flame))
  }
}

object Enchantress {
  def ENCHANTRESS = "Enchantress"
}
