package de.johanneswirth.apps.fantasyrealms.cards.artifact

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class WorldTree extends Card {
  override val name: String = "World Tree"
  setSuit(Suit.Artifact)
  override val basePoints: Int = 2
  override val id: String = "FR48"

  override protected def calcBonus(hand: List[Card]): Int = {
    val nonBlanked = hand.filter(!_.blanked).map(_.getSuit)
    val distinct = nonBlanked.distinct
    if (nonBlanked.length == distinct.length)
      50
    else
      0
  }
}
