package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.wizard.Necromancer.NECROMANCER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Necromancer extends Card {
  override val name: String = NECROMANCER
  setSuit(Suit.Wizard)
  override val basePoints: Int = 3
  actionNeeded = false
  override val id: String = "FR28"

  //TODO

  //def setUsage(): Unit = actionNeeded = true
}

object Necromancer {
  def NECROMANCER = "Necromancer"
}
