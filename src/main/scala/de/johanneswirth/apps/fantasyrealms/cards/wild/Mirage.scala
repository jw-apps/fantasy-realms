package de.johanneswirth.apps.fantasyrealms.cards.wild

import de.johanneswirth.apps.fantasyrealms.cards.wild.Mirage.MIRAGE
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.exceptions.{ActionNeededException, UndefinedActionException}

class Mirage extends Card {
  private var _name: String = MIRAGE
  override def name: String = _name
  setSuit(Suit.Wild)
  override val basePoints: Int = 0
  actionNeeded = true
  override val id: String = "FR52"

  var otherCard: Option[Card] = None

  override def setUsage(otherCard: Card): Unit = {
    this.otherCard = Some(otherCard)
    actionNeeded = false
  }

  override def doAction(hand: List[Card]): Unit = {
    otherCard match {
      case Some(card) if List(Suit.Army, Suit.Land, Suit.Weather, Suit.Flood, Suit.Flame).contains(card.getSuit) =>
        this._name = card.name
        this.setSuit(card.getSuit)
      case Some(_) => throw UndefinedActionException
      case None => throw ActionNeededException
    }
  }
}

object Mirage {
  def MIRAGE = "Mirage"
}
