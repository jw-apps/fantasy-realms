package de.johanneswirth.apps.fantasyrealms.cards.artifact

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class ProtectionRune extends Card {
  override val name: String = "Protection Rune"
  setSuit(Suit.Artifact)
  override val basePoints: Int = 1
  override val id: String = "FR50"

  override def doClears(hand: List[Card]): Unit = hand.foreach(_.clearPenalty = true)
}
