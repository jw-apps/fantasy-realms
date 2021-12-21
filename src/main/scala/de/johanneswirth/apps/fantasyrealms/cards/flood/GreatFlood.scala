package de.johanneswirth.apps.fantasyrealms.cards.flood

import de.johanneswirth.apps.fantasyrealms.cards.flame.Lightning.LIGHTNING
import de.johanneswirth.apps.fantasyrealms.cards.flood.GreatFlood.GREAT_FLOOD
import de.johanneswirth.apps.fantasyrealms.cards.land.Mountain.MOUNTAIN
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class GreatFlood extends Card {
  override val name: String = GREAT_FLOOD
  setSuit(Suit.Flood)
  override val basePoints: Int = 32
  override val id: String = "FR08"

  private def blankCondition(card: Card): Boolean =
    card.getSuit == Suit.Army ||
      (card.getSuit == Suit.Land && card.name != MOUNTAIN) &&
        (card.getSuit == Suit.Flame && card.name != LIGHTNING)

  protected override def blanks(hand: List[Card]): Unit = hand.filter(blankCondition).foreach(_.blanked = true)
}

object GreatFlood {
  def GREAT_FLOOD = "Great Flood"
}
