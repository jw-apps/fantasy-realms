package de.johanneswirth.apps.fantasyrealms.cards.flame

import de.johanneswirth.apps.fantasyrealms.cards.beast.Dragon.DRAGON
import de.johanneswirth.apps.fantasyrealms.cards.beast.Unicorn.UNICORN
import de.johanneswirth.apps.fantasyrealms.cards.flame.Wildfire.WILDFIRE
import de.johanneswirth.apps.fantasyrealms.cards.flood.GreatFlood.GREAT_FLOOD
import de.johanneswirth.apps.fantasyrealms.cards.flood.Island.ISLAND
import de.johanneswirth.apps.fantasyrealms.cards.land.Mountain.MOUNTAIN
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Wildfire extends Card {
  override val name: String = WILDFIRE
  setSuit(Suit.Flame)
  override val basePoints: Int = 40
  override val id: String = "FR16"

  private def blankCondition(card: Card): Boolean =
    card.getSuit != Suit.Flame &&
      card.getSuit != Suit.Wizard &&
      card.getSuit != Suit.Weather &&
      card.getSuit != Suit.Weapon &&
      card.getSuit != Suit.Artifact &&
      card.name != MOUNTAIN &&
      card.name != GREAT_FLOOD &&
      card.name != ISLAND &&
      card.name != UNICORN &&
      card.name != DRAGON

  protected override def blanks(hand: List[Card]): Unit = hand.filter(blankCondition).foreach(_.blanked = true)
}

object Wildfire {
  def WILDFIRE = "Wildfire"
}
