package ru.stqa.geometry;

public class Main {
    public static void main(String[] args) {
        Triangle t = new Triangle(10,15,20);
        Printer.printTriangleArea(t);
        Printer.printTrianglePerimeter(t);
    }
}
