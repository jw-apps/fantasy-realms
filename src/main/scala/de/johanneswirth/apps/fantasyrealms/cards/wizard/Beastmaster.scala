package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.wizard.Beastmaster.BEASTMASTER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Beastmaster extends Card {
  override val name: String = BEASTMASTER
  setSuit(Suit.Wizard)
  override val basePoints: Int = 9
  override val id: String = "FR27"

  override protected def calcBonus(hand: List[Card]): Int = {
    9 * countSuit(hand, Suit.Beast)
  }

  override def doClears(hand: List[Card]): Unit = hand.filter(_.getSuit == Suit.Beast).foreach(_.clearPenalty = true)
}

object Beastmaster {
  def BEASTMASTER = "Beastmaster"
}