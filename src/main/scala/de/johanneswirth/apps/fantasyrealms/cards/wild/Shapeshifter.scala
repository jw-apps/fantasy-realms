package de.johanneswirth.apps.fantasyrealms.cards.wild

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.exceptions.{ActionNeededException, UndefinedActionException}

class Shapeshifter extends Card {
  private var _name: String = "Shapeshifter"
  override def name: String = _name
  setSuit(Suit.Wild)
  override val basePoints: Int = 0
  actionNeeded = true
  override val id: String = "FR51"

  var otherCard: Option[Card] = None

  def setUsage(otherCard: Card): Unit = {
    this.otherCard = Some(otherCard)
    actionNeeded = false
  }

  override def doAction(hand: List[Card]): Unit = {
    otherCard match {
      case Some(card) if List(Suit.Artifact, Suit.Leader, Suit.Wizard, Suit.Weapon, Suit.Beast).contains(card.getSuit) =>
        this._name = card.name
        this.setSuit(card.getSuit)
      case Some(_) => throw UndefinedActionException
      case None => throw ActionNeededException
    }
  }
}
