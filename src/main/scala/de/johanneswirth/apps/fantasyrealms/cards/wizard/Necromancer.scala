package de.johanneswirth.apps.fantasyrealms.cards.wizard

import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}

class Necromancer extends Card {
  override val name: String = "Necromancer"
  setSuit(Suit.Wizard)
  override val basePoints: Int = 3
  actionNeeded = true
  override val id: String = "FR28"

  //TODO

  def setUsage(): Unit = actionNeeded = false
}
