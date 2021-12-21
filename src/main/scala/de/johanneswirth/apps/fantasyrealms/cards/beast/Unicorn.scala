package de.johanneswirth.apps.fantasyrealms.cards.beast

import de.johanneswirth.apps.fantasyrealms.cards.beast.Unicorn.UNICORN
import de.johanneswirth.apps.fantasyrealms.cards.leader.Empress.EMPRESS
import de.johanneswirth.apps.fantasyrealms.cards.leader.Princess.PRINCESS
import de.johanneswirth.apps.fantasyrealms.cards.leader.Queen.QUEEN
import de.johanneswirth.apps.fantasyrealms.cards.wizard.Enchantress.ENCHANTRESS
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Unicorn extends Card {
  override val name: String = UNICORN
  setSuit(Suit.Beast)
  override val basePoints: Int = 9
  override val id: String = "FR36"

  override protected def calcBonus(hand: List[Card]): Int = {
    if (containsCard(hand, PRINCESS))
      30
    else if (containsCard(hand, EMPRESS) || containsCard(hand, QUEEN) || containsCard(hand, ENCHANTRESS))
      15
    else
      0
  }
}

object Unicorn {
  def UNICORN = "Unicorn"
}
