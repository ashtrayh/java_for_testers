package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(3,5,6);
        Assertions.assertEquals(14, s.perimeter());
    }
    @Test
void canCalculateArea() {
        var s = new Triangle(6,8,10);
        Assertions.assertEquals(24, s.area());
}
@Test
    void cannotCreateTriangleWithNegativeSide() {
    try {
        new Triangle(-5, 7, 8);
        Assertions.fail();
    } catch (IllegalArgumentException exception) {
        //OK
    }
    try {
        new Triangle(5, -7, 8);
        Assertions.fail();
    } catch (IllegalArgumentException exception) {
        //OK
    }
    try {
        new Triangle(5, 7, -8);
        Assertions.fail();
    } catch (IllegalArgumentException exception) {
        //OK
    }
}
    @Test
            void cannotCreateTriangleIfInequalityIsViolated() {
        try {
            new Triangle(15, 7, 7);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(5, 15, 7);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(5, 7, 17);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
}

