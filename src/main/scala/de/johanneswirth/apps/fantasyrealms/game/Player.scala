package de.johanneswirth.apps.fantasyrealms.game

import de.johanneswirth.apps.fantasyrealms.cards.Card

import scala.collection.mutable

class Player(id: Int, name: String) {
  private val hand = mutable.ListBuffer[Card]()

  def getHand: List[Card] = hand.toList
  def addToHand(card: Card): Unit = hand += card
  def removeFromHand(idx: Int): Card = hand.remove(idx)
  def getID: Int = id

  def actionNeeded: Boolean = hand.exists(_.actionNeeded)

  def resetBlanks(): Unit = hand.foreach(_.blanked = false)

  def calcScore(): Unit = {
    hand.foreach{card =>
      card.doAction(hand.toList)
    }
    hand.foreach{card =>
      card.doClears(hand.toList)
    }
    hand.foreach{card =>
      card.doBlanks(hand.toList)
    }
    val order = hand.toList.filter(!_.blanked) ::: hand.toList.filter(_.blanked)
    resetBlanks()
    order.foreach{card =>
      card.doBlanks(hand.toList)
    }
    hand.sortBy(_.id).foreach(_.doSelfBlank(hand.toList))
    hand.foreach{card =>
      val base = card.getBasePoints
      val mod = card.calculateModifier(hand.toList)
      card.score = Some((base, mod))
    }
  }

  def getScore: Int = {
    hand.map(_.score).flatMap(o => o.map(s => s._1 + s._2)).sum
  }
}
