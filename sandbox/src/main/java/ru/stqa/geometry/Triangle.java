package ru.stqa.geometry;

public record Triangle(double a, double b, double c)  {

    public Triangle {
    if (a < 0 || b < 0 || c < 0) {
        throw new IllegalArgumentException("Triangle side should be non-negative");
    }
    if ( (a > b+c) || (b > a+c) || (c > a+b)) {
        throw new IllegalArgumentException("The triangle inequality is violated");
    }
}

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}