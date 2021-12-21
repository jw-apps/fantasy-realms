package de.johanneswirth.apps.fantasyrealms.cards.artifact

import de.johanneswirth.apps.fantasyrealms.cards.artifact.BookOfChanges.BOOK_OF_CHANGES
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.exceptions.{ActionNeededException, UndefinedActionException}

class BookOfChanges extends Card {
  override val name: String = BOOK_OF_CHANGES
  setSuit(Suit.Artifact)
  override val basePoints: Int = 3
  actionNeeded = true
  override val id: String = "FR49"

  private var change: Option[(Card, Suit)] = None

  def setUsage(otherCard: Card, newSuit: Suit): Unit = {
    change = Some((otherCard, newSuit))
    actionNeeded = false
  }

  override def doAction(hand: List[Card]): Unit = {
    change match {
      case Some((card, suit)) if hand.contains(card) => card.setSuit(suit)
      case Some(_) => throw UndefinedActionException
      case None => throw ActionNeededException
    }
  }
}

object BookOfChanges {
  def BOOK_OF_CHANGES = "Book of Changes"
}
