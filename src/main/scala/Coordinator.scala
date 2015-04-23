import akka.actor.{Actor, Props}

object Coordinator extends Actor{

  def receive = {
    case Start => {
      val test = context.actorOf(Props[Calculator], "cal")
      test ! Calculate(34)
    }
    case Set(x: Int, y: Int, c: Colour) => {
      image(x, y) = c
      waiting -= 1
    }
  }

  def init(im: Image, of: String) = {
    image = im
    outfile = of
    waiting = im.width * im.height
  }

  // Number of pixels we're waiting for to be set.
  var waiting = 0
  var outfile: String = null
  var image: Image = null

  def print = {
    assert(waiting == 0)
    image.print(outfile)
  }
}

case class Start()
case class Calculate(row: Int)
case class Set(x: Int, y: Int, c: Colour)
