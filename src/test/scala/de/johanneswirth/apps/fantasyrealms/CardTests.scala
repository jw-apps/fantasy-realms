package de.johanneswirth.apps.fantasyrealms

import de.johanneswirth.apps.fantasyrealms.cards.artifact.BookOfChanges
import de.johanneswirth.apps.fantasyrealms.cards.artifact.BookOfChanges.BOOK_OF_CHANGES
import de.johanneswirth.apps.fantasyrealms.cards.flood.Island.ISLAND
import de.johanneswirth.apps.fantasyrealms.cards.wild.Doppelgänger.DOPPELGÄNGER
import de.johanneswirth.apps.fantasyrealms.cards.wild.Mirage.MIRAGE
import de.johanneswirth.apps.fantasyrealms.cards.wild.Shapeshifter.SHAPESHIFTER
import de.johanneswirth.apps.fantasyrealms.cards.wizard.Necromancer.NECROMANCER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.game.Player
import me.tongfei.progressbar.{InteractiveConsoleProgressBarConsumer, PBRenderer, ProgressBar, ProgressBarStyle}
import org.jline.terminal.TerminalBuilder
import org.reflections.Reflections
import org.scalatest.funsuite.AnyFunSuite

import java.io.{FileDescriptor, FileOutputStream, PrintStream}
import java.text.DecimalFormat
import java.time.Duration
import java.time.temporal.ChronoUnit
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.util.Random



class CardTests extends AnyFunSuite {

  type Action = List[Card] => List[Card]
  def noAction: Action = (hand: List[Card]) => hand

  val js = new JSLauncher
  val reflections = new Reflections("de.johanneswirth.apps.fantasyrealms.cards")
  js.init()
  val classes = reflections.getSubTypesOf(classOf[Card])
  val cards = mutable.ListBuffer[Card]()
  val terminal = TerminalBuilder.builder().dumb(true).build()
  classes.forEach{card =>
    cards += card.getDeclaredConstructor().newInstance()
  }
  val numCards = cards.length.toLong
  val count = ((numCards - 6) to numCards).product / (1 to 7).product / 1000
  val real = new PrintStream(new FileOutputStream(FileDescriptor.err))
  private val pb = new ProgressBar("Test", count, 1000, 0, Duration.ZERO, new PBRenderer(ProgressBarStyle.ASCII, "", 1, true, new DecimalFormat("##0.###"), ChronoUnit.SECONDS), new InteractiveConsoleProgressBarConsumer(real, 200))
  var passedAsserts = 0
  var runningMean: Double  = 1
  var numTerms = 0

  test("Score should be equal to reference") {
    val list = classes.asScala.toList
    iterate(List(), list)
    println(s"Passed asserts: $passedAsserts")
    pb.close()
  }

  def iterate(hand: List[Class[_ <: Card]], deck: List[Class[_ <: Card]]): Unit = {
    if (hand.length == 7) {
      checkCards(hand)
    } else if (deck.nonEmpty) {
      val first = deck.head

      iterate(first :: hand, deck.tail)

      iterate(hand, deck.tail)
    }
  }



  /*def checkCards(cards: List[Class[_ <: Card]]): Unit = {
    val hand = cards.map(_.getDeclaredConstructor().newInstance())
    var options = List[Action](noAction)
    options = addToOptions(options, optionsNecromancer(hand))
    options = addToOptions(options, optionsDoppelganger(hand))
    options = addToOptions(options, optionsMirage(hand))
    options = addToOptions(options, optionsShapeshifter(hand))
    options = addToOptions(options, optionsBookOfChanges(hand))
    options = addToOptions(options, optionsIsland(hand))
    numTerms += 1
    runningMean = runningMean + ((options.length - runningMean) / numTerms)
    options.foreach { o =>
      val hand = cards.map(_.getDeclaredConstructor().newInstance())
      checkHand(hand, o)
      passedAsserts += 1
      if (passedAsserts % 1000 == 0) {
        pb.step()
        pb.maxHint((count * runningMean).round)
        pb.setExtraMessage(f" Average Options: $runningMean%.2f")
      }
    }
  }*/

  def checkCards(cards: List[Class[_ <: Card]]): Unit = {
    val hand = cards.map(_.getDeclaredConstructor().newInstance())
    var options = List[Action](noAction)
    options = addToOptions(options, optionsNecromancer(hand))
    options = addToOptions(options, optionsDoppelganger(hand))
    options = addToOptions(options, optionsMirage(hand))
    options = addToOptions(options, optionsShapeshifter(hand))
    options = addToOptions(options, optionsBookOfChanges(hand))
    options = addToOptions(options, optionsIsland(hand))
    val min = Math.min(3, options.length)
    numTerms += 1
    runningMean = runningMean + ((min - runningMean) / numTerms)
    for (i <- 0 until min) {
      val index = Random.between(0, options.length)

      val hand = cards.map(_.getDeclaredConstructor().newInstance())
      checkHand(hand, options(index))
      passedAsserts += 1
      if (passedAsserts % 1000 == 0) {
        pb.step()
        pb.maxHint((count * runningMean).round)
        pb.setExtraMessage(f" Average Options: $runningMean%.2f")
      }
    }
  }

  def addToOptions(options: List[Action], add: List[Action]): List[Action] = {
    val list = mutable.ListBuffer[Action]()
    options.foreach{o => {
      add.foreach(a => {
        list += (hand => {o(hand); a(hand)})
      })
    }}
    list.toList
  }

  def checkHand(hand: List[Card], options: Action): Unit = {
    js.clearHand()
    val player = new Player(0, "")
    hand.foreach(player.addToHand)
    hand.map(_.id).foreach(js.addCard)

    options(hand)

    val reference = js.getJSScore

    val f = Future {player.calcScore()}
    f.map(_ => assert(player.getScore == reference, s"#${passedAsserts+1}: Hand $cards [https://fantasy-realms.github.io/index.html?hand=${cards.map(_.id).mkString(",")}+]"))

  }

  def optionsDoppelganger(hand: List[Card]): List[Action] = {
    hand.find(_.name == DOPPELGÄNGER) match {
      case Some(value) =>
        val idx = hand.indexOf(value)
        hand.filter(_.name != DOPPELGÄNGER).map(c => hand => {hand(idx).setUsage(c); js.actionDoppelganger(c.id); hand})
      case None => List(noAction)
    }
  }

  def optionsShapeshifter(hand: List[Card]): List[Action] = {
    hand.find(_.name == SHAPESHIFTER) match {
      case Some(value) =>
        val idx = hand.indexOf(value)
        val avail = cards.filter(c => List(Suit.Artifact, Suit.Leader, Suit.Wizard, Suit.Weapon, Suit.Beast).contains(c.getSuit)).toList
        avail.map(c => hand => {hand(idx).setUsage(c); js.actionShapeshifter(c.id); hand})
      case None => List(noAction)
    }
  }

  def optionsMirage(hand: List[Card]): List[Action] = {
    hand.find(_.name == MIRAGE) match {
      case Some(value) =>
        val idx = hand.indexOf(value)
        val avail = cards.filter(c => List(Suit.Army, Suit.Land, Suit.Weather, Suit.Flood, Suit.Flame).contains(c.getSuit)).toList
        avail.map(c => hand => {hand(idx).setUsage(c); js.actionMirage(c.id); hand})
      case None => List(noAction)
    }
  }

  def optionsIsland(hand: List[Card]): List[Action] = {
    hand.find(_.name == ISLAND) match {
      case Some(value) =>
        val idx = hand.indexOf(value)
        val avail = hand.filter(_.name != ISLAND).filter(c => c.getSuit == Suit.Flood || c.getSuit == Suit.Flame)
        if (avail.isEmpty)
          List(hand => {hand(idx).noUsage(); hand})
        else
          avail.map(c => hand => {hand(idx).setUsage(c); js.actionIsland(c.id); hand})
      case None => List(noAction)
    }
  }

  def optionsBookOfChanges(hand: List[Card]): List[Action] = {
    hand.find(_.name == BOOK_OF_CHANGES) match {
      case Some(value) =>
        val idx = hand.indexOf(value)
        val cards = hand.filter(_.name != BOOK_OF_CHANGES)
        val suits = Suit.values().toList.filter(_ != Suit.None)
        cards.flatMap(c => suits.map(s => hand => {hand(idx).asInstanceOf[BookOfChanges].setUsage(c, s); js.actionBookOfChanges(c.id, s.name().toLowerCase); hand}))
      case None => List(noAction)
    }
  }

  def optionsNecromancer(hand: List[Card]): List[Action] = {
    hand.find(_.name == NECROMANCER) match {
      case Some(_) =>
        val avail = cards.filter(!hand.contains(_)).toList
        avail.map(c => hand => c :: hand)
      case None => List(noAction)
    }
  }
}
