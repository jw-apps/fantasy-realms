package de.johanneswirth.apps.fantasyrealms.cards

import de.johanneswirth.apps.fantasyrealms.exceptions.UndefinedActionException

trait Card {
  def name: String

  val id: String
  private var suit: Suit = Suit.None
  val basePoints: Int
  var blanked = false
  var clearPenalty = false
  var clearArmyFromPenalty = false
  var actionNeeded = false

  var score: Option[(Int, Int)] = None

  def getSuit: Suit = if (blanked) Suit.None else suit

  def setSuit(suit: Suit): Unit = this.suit = suit

  protected def calcBonus(hand: List[Card]): Int = 0

  protected def calcPenalty(hand: List[Card]): Int = 0

  def getBasePoints: Int = if (blanked) 0 else basePoints

  def calculateModifier(hand: List[Card]): Int = {
    if (blanked)
      0
    else if (clearPenalty)
      calcBonus(hand)
    else
      calcBonus(hand) - calcPenalty(hand)
  }

  def doAction(hand: List[Card]): Unit = {}

  def doBlanks(hand: List[Card]): Unit = if (!clearPenalty && !blanked) blanks(hand)

  protected def blanks(hand: List[Card]): Unit = {}

  def doSelfBlank(hand: List[Card]): Unit = if (!clearPenalty) blankSelf(hand)
  protected def blankSelf(hand: List[Card]): Unit = {}

  def doClears(hand: List[Card]): Unit = {}

  override def toString: String = if (score.isDefined) s"$name ${score.get}" else name

  def setUsage(otherCard: Card): Unit = throw UndefinedActionException
  def noUsage(): Unit = actionNeeded = false

  protected def removeSelf(hand: List[Card]): List[Card] = {
    hand.filter(this != _)
  }
  protected def countSuit(hand: List[Card], suit: Suit): Int = {
    hand.count(c => !c.blanked && c.getSuit == suit)
  }
  protected def containsSuit(hand: List[Card], suit: Suit): Boolean = {
    hand.exists(c => !c.blanked && c.getSuit == suit)
  }
  protected def containsCard(hand: List[Card], card: String): Boolean = {
    hand.exists(c => !c.blanked && c.name == card)
  }
  protected def countSuits(hand: List[Card], suits: List[Suit]): Int = {
    hand.count(c => !c.blanked && suits.contains(c.getSuit))
  }
}
