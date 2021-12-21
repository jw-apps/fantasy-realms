package de.johanneswirth.apps.fantasyrealms.cards.weather

import de.johanneswirth.apps.fantasyrealms.cards.flood.GreatFlood.GREAT_FLOOD
import de.johanneswirth.apps.fantasyrealms.cards.weather.Blizzard.BLIZZARD
import de.johanneswirth.apps.fantasyrealms.cards.weather.Rainstorm.RAINSTORM
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Whirlwind extends Card {
  override val name: String = "Whirlwind"
  setSuit(Suit.Weather)
  override val basePoints: Int = 13
  override val id: String = "FR14"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, RAINSTORM)  && (containsCard(hand, BLIZZARD) || containsCard(hand, GREAT_FLOOD)))
      40
    else
      0
  }
}
