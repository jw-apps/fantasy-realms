package de.johanneswirth.apps.fantasyrealms.cards.weather

import de.johanneswirth.apps.fantasyrealms.cards.weather.Blizzard.BLIZZARD
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Blizzard extends Card {
  override val name: String = BLIZZARD
  setSuit(Suit.Weather)
  override val basePoints: Int = 30
  override val id: String = "FR12"

  def penaltyList: List[Suit] =
    if (clearArmyFromPenalty)
      List(Suit.Leader, Suit.Beast, Suit.Flame)
    else
      List(Suit.Army, Suit.Leader, Suit.Beast, Suit.Flame)

  override protected def calcPenalty(hand: List[Card]): Int = {
    5 * countSuits(hand, penaltyList)
  }

  protected override def blanks(hand: List[Card]): Unit = {
    hand.filter(_.getSuit == Suit.Flood).foreach(_.blanked = true)
  }
}

object Blizzard {
  def BLIZZARD = "Blizzard"
}
