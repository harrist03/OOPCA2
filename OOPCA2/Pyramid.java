package OOPCA2;

public class Pyramid implements IMeasurableContainer{
    public double length, sideLength, weight;

    public Pyramid (double length, double sideLength, double weight) {
        this.length = length;
        this.sideLength = sideLength;
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public double rectangularVolume() {
        return length * length * sideLength;
    }
}
