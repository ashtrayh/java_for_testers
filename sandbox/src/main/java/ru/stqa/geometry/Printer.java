package ru.stqa.geometry;

public class Printer {
    public static void printTriangleArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f = %.2f%n", t.a(), t.b(), t.c(), t.area());
        System.out.println(text);
    }
    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f = %.2f%n", t.a(), t.b(), t.c(), t.perimeter());
        System.out.println(text);
    }
}
