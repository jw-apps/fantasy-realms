package de.johanneswirth.apps.fantasyrealms.cards.flood

import de.johanneswirth.apps.fantasyrealms.cards.flood.Island.ISLAND
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.exceptions.{ActionNeededException, UndefinedActionException}

class Island extends Card {
  override val name: String = ISLAND
  setSuit(Suit.Flood)
  override val basePoints: Int = 14
  actionNeeded = true
  override val id: String = "FR09"

  var otherCard: Option[Card] = None

  def setUsage(otherCard: Card): Unit = {
    this.otherCard = Some(otherCard)
    actionNeeded = false
  }

  def noUsage(): Unit = actionNeeded = false

  override def doClears(hand: List[Card]): Unit = {
    otherCard match {
      case Some(card) if !hand.contains(card) => throw UndefinedActionException
      case Some(card) if card.getSuit == Suit.Flame || card.getSuit == Suit.Flame => card.clearPenalty = true
      case Some(_) => throw UndefinedActionException
      case None => throw ActionNeededException
    }
  }
}

object Island {
  def ISLAND = "Island"
}
