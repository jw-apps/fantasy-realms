package de.johanneswirth.apps.fantasyrealms.game

import de.johanneswirth.apps.fantasyrealms.cards.Card
import de.johanneswirth.apps.fantasyrealms.exceptions.{IncorrectActionException, IncorrectPlayerException, UndefinedActionException}
import org.reflections.Reflections

import scala.collection.mutable
import scala.util.Random

class Game(players: List[Player]) {
  private val openCards = mutable.ListBuffer[Card]()
  private val deck = mutable.Stack[Card]()
  private var currentPlayerIndex = 0
  private var currentPhase: GamePhase = GamePhase.Draw

  private def currentPlayer = players(currentPlayerIndex)

  init()

  private def init(): Unit = {
    val reflections = new Reflections("de.johanneswirth.apps.fantasyrealms.cards")
    val classes = reflections.getSubTypesOf(classOf[Card])
    val cards = mutable.ListBuffer[Card]()
    classes.forEach{card =>
      cards += card.getDeclaredConstructor().newInstance()
    }
    deck.pushAll(Random.shuffle(cards.toList))
    for (a <- 1 to 7) {
      players.foreach(p => p.addToHand(deck.pop()))
    }
  }

  private def nextPlayer(): Unit = {
    currentPlayerIndex += 1
    currentPlayerIndex %= players.length
    currentPhase = GamePhase.Draw
  }

  private def calcScores(): Unit = {
    players.foreach(_.calcScore())
  }

  def drawCardFromStack(playerID: Int): Unit = {
    if (currentPlayer.getID != playerID) throw IncorrectPlayerException
    if (currentPhase != GamePhase.Draw) throw IncorrectActionException
    val card = deck.pop()
    currentPlayer.addToHand(card)
    currentPhase = GamePhase.Discard
  }

  def drawOpenCard(playerID: Int, index: Int): Unit = {
    if (currentPlayer.getID != playerID) throw IncorrectPlayerException
    if (currentPhase != GamePhase.Draw) throw IncorrectActionException
    if (index >= openCards.length) throw UndefinedActionException
    val card = openCards.remove(index)
    currentPlayer.addToHand(card)
    currentPhase = GamePhase.Discard
  }

  def discardCard(playerID: Int, index: Int): Unit = {
    if (currentPlayer.getID != playerID) throw IncorrectPlayerException
    if (currentPhase != GamePhase.Discard) throw IncorrectActionException
    if (index >= currentPlayer.getHand.length) throw UndefinedActionException
    val card = currentPlayer.removeFromHand(index)
    openCards += card
    if (openCards.length < 10)
      nextPlayer()
    else {
      if (actionNeeded)
        currentPhase = GamePhase.PreScoring
      else
        calcScores()
    }
  }

  private def actionNeeded: Boolean = players.exists(_.actionNeeded)
}
