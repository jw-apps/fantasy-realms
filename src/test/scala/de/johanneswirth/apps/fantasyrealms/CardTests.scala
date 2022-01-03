package de.johanneswirth.apps.fantasyrealms

import de.johanneswirth.apps.fantasyrealms.cards.artifact.BookOfChanges.BOOK_OF_CHANGES
import de.johanneswirth.apps.fantasyrealms.cards.wild.Doppelgänger.DOPPELGÄNGER
import de.johanneswirth.apps.fantasyrealms.cards.{Card, Suit}
import de.johanneswirth.apps.fantasyrealms.game.Player
import me.tongfei.progressbar.{InteractiveConsoleProgressBarConsumer, PBRenderer, ProgressBar, ProgressBarStyle}
import org.jline.terminal.TerminalBuilder
import org.jline.utils.InfoCmp
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
  cards.length.toLong
  val real = new PrintStream(new FileOutputStream(FileDescriptor.err))
  println(terminal.getStringCapability(InfoCmp.Capability.cursor_down))
  println(terminal.getStringCapability(InfoCmp.Capability.cursor_up))
  private val pb = new ProgressBar("Test", count, 1000, 0, Duration.ZERO, new PBRenderer(ProgressBarStyle.ASCII, "", 1, true, new DecimalFormat("##0.###"), ChronoUnit.SECONDS), new InteractiveConsoleProgressBarConsumer(real, 100))
  var passedAsserts = 0

  test("Score should be equal to reference") {
    val list = classes.asScala.toList
    iterate(List(), list)
    println(s"Passed asserts: $passedAsserts")
    pb.close()
  }

  def iterate(hand: List[Class[_ <: Card]], deck: List[Class[_ <: Card]]): Unit = {
    if (hand.length == 7) {
      val random = Random.between(0, 1)
      if (random == 0) {
        val cards = hand.map(_.getDeclaredConstructor().newInstance())
        if (!cards.exists(_.actionNeeded)) {
          js.clearHand()
          val player = new Player(0, "")
          cards.foreach(player.addToHand)
          cards.map(_.id).foreach(js.addCard)

          val reference = js.getJSScore

          val f = Future {player.calcScore()}
          f.map(_ => assert(player.getScore == reference, s"#${passedAsserts+1}: Hand $cards [https://fantasy-realms.github.io/index.html?hand=${cards.map(_.id).mkString(",")}+]"))

//        player.calcScore()
//        val reference = js.getJSScore
//        assert(player.getScore == reference, s"#${passedAsserts+1}: Hand $cards [https://fantasy-realms.github.io/index.html?hand=${cards.map(_.id).mkString(",")}+]")

        }
      }
      passedAsserts += 1
      if (passedAsserts % 1000 == 0) pb.step()
    } else if (deck.nonEmpty) {
      val first = deck.head

      iterate(first :: hand, deck.tail)

      iterate(hand, deck.tail)
    }
  }

  def chooseDoppelgangerCard(hand: List[Card]): Card = {
    val idx = Random.between(0, hand.length-1)
    hand.filter(_.name != DOPPELGÄNGER)(idx)
  }

  def chooseShapeshifterCard: Card = {
    val avail = cards.filter(c => List(Suit.Artifact, Suit.Leader, Suit.Wizard, Suit.Weapon, Suit.Beast).contains(c.getSuit))
    val idx = Random.between(0, avail.length)
    avail(idx)
  }

  def chooseMirageCard: Card = {
    val avail = cards.filter(c => List(Suit.Army, Suit.Land, Suit.Weather, Suit.Flood, Suit.Flame).contains(c.getSuit))
    val idx = Random.between(0, avail.length)
    avail(idx)
  }

  def chooseIslandCard(hand: List[Card]): Option[Card] = {
    val avail = hand.filter(c => c.getSuit == Suit.Flood || c.getSuit == Suit.Flame)
    if (avail.isEmpty) None
    else {
      val idx = Random.between(0, avail.length)
      Some(avail(idx))
    }
  }

  def chooseBookOfChangesCard(hand: List[Card]): Card = {
    val idx = Random.between(0, hand.length-1)
    hand.filter(_.name != BOOK_OF_CHANGES)(idx)
  }

  def chooseSuit: Suit = {
    val idx = Random.between(0, Suit.values().length)
    Suit.values()(idx)
  }

  def chooseNecromancerCard(hand: List[Card]): Card = {
    val avail = cards.filter(!hand.contains(_))
    val idx = Random.between(0, avail.length)
    avail(idx)
  }
}
