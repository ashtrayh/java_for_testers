package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(3,5,10);
        Assertions.assertEquals(18, s.perimeter());
    }
    @Test
void canCalculateArea() {
        var s = new Triangle(6,8,10);
        Assertions.assertEquals(24, s.area());
}
}
