package graphics

import org.junit.Assert.*
import org.junit.Test

class RectTest {

    @Test
    fun init() {
        val rect = Rect()
        assertEquals(rect.x, 0f)
        assertEquals(rect.y, 0f)
        assertEquals(rect.width, 0f)
        assertEquals(rect.height, 0f)
    }

    @Test
    fun init2() {
        val x = 4f; val y = 65f; val w = 12f; val h = 76f
        val rect = Rect(x, y, w, h)
        assertEquals(rect.x, x)
        assertEquals(rect.y, y)
        assertEquals(rect.width, w)
        assertEquals(rect.height, h)
    }

    @Test
    fun init3() {
        val x = 4f; val y = 65f; val w = 12f; val h = 76f
        val rect = Rect(Point(x, y), Size(w, h))
        assertEquals(rect.x, x)
        assertEquals(rect.y, y)
        assertEquals(rect.width, w)
        assertEquals(rect.height, h)
    }

    @Test
    fun init4() {
        val x = 4f; val y = 65f; val w = 12f; val h = 76f
        val rect = Rect(Point(x, y), Point(x + w, y + h))
        assertEquals(rect.x, x)
        assertEquals(rect.y, y)
        assertEquals(rect.width, w)
        assertEquals(rect.height, h)
    }

    @Test
    fun corners() {
        val x = 4f; val y = 65f; val w = 12f; val h = 76f
        val rect = Rect(Point(x, y), Size(w, h))
        assertEquals(rect.topLeft, Point(x, y))
        assertEquals(rect.topRight, Point(x + w, y))
        assertEquals(rect.bottomRight, Point(x + w, y + h))
        assertEquals(rect.bottomLeft, Point(x, y + h))
    }

    @Test
    fun size() {
        assertEquals(Rect(0f, 0f, 45f, 32f).size, Size(45f, 32f))
    }

    @Test
    fun center() {
        val x = 4f; val y = 65f; val w = 12f; val h = 76f
        val rect = Rect(x, y, w, h)
        assertEquals(rect.center, Point(x + w / 2f, y + h / 2f))
    }

    @Test
    fun adjusted() {
        val x = 4f; val y = 65f; val w = 12f; val h = 76f
        val l = 2f; val t = 3f; val r = 5f; val b = 2f
        val rect = Rect(x, y, w, h)
        assertEquals(rect.adjusted(l, t, r, b), Rect(rect.topLeft + Point(l, t), rect.bottomRight + Point(r, b)))
    }

    @Test
    fun containsPoint() {
        val x1 = 4f; val y1 = 65f; val x2 = 12f; val y2 = 76f
        val rect = Rect(Point(x1, y1), Point(x2, y2))

        assertTrue(rect.contains(x1, y1))
        assertTrue(rect.contains(x2, y2))
        assertTrue(rect.contains(rect.center))
        assertFalse(rect.contains(x1 - 1f, y1 + 1f))
    }

    @Test
    fun containsRect() {
        val x1 = 4f; val y1 = 65f; val x2 = 12f; val y2 = 76f
        val rect = Rect(Point(x1, y1), Point(x2, y2))

        assertTrue(rect.contains(rect))
        assertTrue(rect.contains(Rect(Point(x1 + 1f, y1 + 1f), Point(x2 - 1f, y2 - 1f))))
        assertFalse(rect.contains(Rect(Point(x1 - 1f, y1), Point(x2, y2))))
    }

    @Test
    fun moveCenter() {
        val rect = Rect(0f, 5f, 34f, 76f)
        val pt = Point(67f, 34f)
        assertEquals(rect.moveCenter(pt.x, pt.y).center, pt)
        assertEquals(rect.moveCenter(pt).center, pt)
    }

    @Test
    fun moveLeft() {
        val rect = Rect(0f, 5f, 34f, 76f)
        val x = 45f
        assertEquals(rect.moveLeft(x).x, x)
    }

    @Test
    fun moveTop() {
        val rect = Rect(0f, 5f, 34f, 76f)
        val y = 675f
        assertEquals(rect.moveTop(y).y, y)
    }

    @Test
    fun moveRight() {
        val rc = Rect(0f, 5f, 34f, 76f)
        val x = 34f
        assertEquals(rc.moveRight(x), Rect(x - rc.width, rc.y, rc.width, rc.height))
    }

    @Test
    fun moveBottom() {
        val rc = Rect(0f, 5f, 34f, 76f)
        val y = 675f
        assertEquals(rc.moveBottom(y), Rect(rc.x, y - rc.height, rc.width, rc.height))
    }

    @Test
    fun moveTopLeft() {
        val rc = Rect(0f, 5f, 34f, 76f)
        val pt = Point(23f, 78f)
        assertEquals(rc.moveTopLeft(pt.x, pt.y), Rect(pt, rc.size))
        assertEquals(rc.moveTopLeft(pt), Rect(pt, rc.size))
    }

    @Test
    fun moveTopRight() {
        val rc = Rect(0f, 5f, 34f, 76f)
        val pt = Point(23f, 78f)
        assertEquals(rc.moveTopRight(pt.x, pt.y), rc.moveRight(pt.x).moveTop(pt.y))
        assertEquals(rc.moveTopRight(pt), rc.moveRight(pt.x).moveTop(pt.y))
    }
}