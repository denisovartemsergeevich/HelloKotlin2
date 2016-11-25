package graphics

data class Rect(val x: Float, val y: Float, val width: Float, val height: Float) {
    val topLeft: Point
        get() = Point(x, y)

    val topRight: Point
        get() = Point(x + width, y)

    val bottomRight: Point
        get() = Point(x + width, y + height)

    val bottomLeft: Point
        get() = Point(x, y + height)

    val size: Size
        get() = Size(width, height)

    val center: Point
        get() = Point(x + width / 2f, y + height / 2f)

    constructor() : this(0f, 0f, 0f, 0f)

    constructor(topLeft: Point, size: Size):
            this(topLeft.x, topLeft.y, size.width, size.height)

    constructor(topLeft: Point, bottomRight: Point):
            this(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y)

    fun adjusted(dx1: Float, dy1: Float, dx2: Float, dy2: Float)
            = Rect(x + dx1, y + dy1, width + dx2 - dx1, height + dy2 - dy1)

    fun contains(x: Float, y: Float)
            = (x >= topLeft.x) && (y >= topLeft.y)  && (x <= bottomRight.x) && (x <= bottomRight.y)

    fun contains(p:Point)
            = contains(p.x, p.y)

    fun contains(rc:Rect)
            = contains(rc.topLeft) && contains(rc.bottomLeft)

    fun moveCenter(x: Float, y: Float)
            = Rect(x - width / 2f, y - height / 2f, width, height)

    fun moveCenter(pt: Point)
            = moveCenter(pt.x, pt.y)

    fun moveLeft(x: Float)
            = Rect(x, y, width, height)

    fun moveTop(y: Float)
            = Rect(x, y, width, height)

    fun moveRight(x: Float)
            = Rect(x - width, y, width, height)

    fun moveBottom(y: Float)
            = Rect(x, y - height, width, height)

    fun moveTopLeft(x: Float, y: Float)
            = Rect(x, y, width, height)

    fun moveTopLeft(pt: Point)
            = moveTopLeft(pt.x, pt.y)

    fun moveTopRight(x: Float, y: Float)
            = Rect(x - width, y, width, height)

    fun moveTopRight(pt: Point)
            = moveTopRight(pt.x, pt.y)
}