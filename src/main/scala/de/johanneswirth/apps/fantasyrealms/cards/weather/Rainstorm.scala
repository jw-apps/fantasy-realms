package de.johanneswirth.apps.fantasyrealms.cards.weather

import de.johanneswirth.apps.fantasyrealms.cards.flame.Lightning.LIGHTNING
import de.johanneswirth.apps.fantasyrealms.cards.weather.Rainstorm.RAINSTORM
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Rainstorm extends Card {
  override val name: String = RAINSTORM
  setSuit(Suit.Weather)
  override val basePoints: Int = 8
  override val id: String = "FR11"

  override protected def calcBonus(hand: List[Card]): Int = {
    10 * countSuit(hand, Suit.Flood)
  }

  protected override def blanks(hand: List[Card]): Unit = {
    hand.filter(c => c.getSuit == Suit.Flame && c.name != LIGHTNING).foreach(_.blanked = true)
  }
}

object Rainstorm {
  def RAINSTORM = "Rainstorm"
}
