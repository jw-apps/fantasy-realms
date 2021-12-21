package de.johanneswirth.apps.fantasyrealms.cards.weather

import de.johanneswirth.apps.fantasyrealms.cards.weather.Smoke.SMOKE
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Smoke extends Card {
  override val name: String = SMOKE
  setSuit(Suit.Weather)
  override val basePoints: Int = 27
  override val id: String = "FR13"

  protected override def blankSelf(hand: List[Card]): Unit = {
    if (!containsSuit(hand, Suit.Flame)) blanked = true
  }
}

object Smoke {
  def SMOKE = "Smoke"
}
