package de.johanneswirth.apps.fantasyrealms.cards.wild

import de.johanneswirth.apps.fantasyrealms.cards.wild.Doppelgänger.DOPPELGÄNGER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.exceptions.{ActionNeededException, UndefinedActionException}

class Doppelgänger extends Card {
  private var _name: String = DOPPELGÄNGER
  override def name: String = _name
  setSuit(Suit.Wild)
  override val basePoints: Int = 0
  actionNeeded = true
  override val id: String = "FR53"

  var otherCard: Option[Card] = None

  def setUsage(otherCard: Card): Unit = {
    this.otherCard = Some(otherCard)
    actionNeeded = false
  }

  override def doAction(hand: List[Card]): Unit = {
    otherCard match {
      case Some(card) if !hand.contains(card) => throw UndefinedActionException
      case Some(card) =>
        this._name = card.name
        this.setSuit(card.getSuit)
      case None => throw ActionNeededException
    }
  }
}

object Doppelgänger {
  def DOPPELGÄNGER = "Doppelgänger"
}
