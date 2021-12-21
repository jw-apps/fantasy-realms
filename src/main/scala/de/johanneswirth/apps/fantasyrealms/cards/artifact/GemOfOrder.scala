package de.johanneswirth.apps.fantasyrealms.cards.artifact

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

import scala.collection.mutable
import scala.language.postfixOps

class GemOfOrder extends Card {
  override val name: String = "Gem of Order"
  setSuit(Suit.Artifact)
  override val basePoints: Int = 5
  override val id: String = "FR47"

  override protected def calcBonus(hand: List[Card]): Int = {
    val strengths = hand.filter(!_.blanked).map(_.basePoints)
    var currentRun = 0
    val runs = mutable.ListBuffer[Int]()
    for (i <- 0 to 40) {
      if (strengths.contains(i)) {
        currentRun += 1
      } else {
        runs += currentRun
        currentRun = 0
      }
    }
    var bonus = 0
    for (run <- runs) {
      run match {
        case 3 => bonus += 10
        case 4 => bonus += 30
        case 5 => bonus += 60
        case 6 => bonus += 100
        case 7 => bonus += 150
        case _ =>
      }
    }
    bonus
  }
}
