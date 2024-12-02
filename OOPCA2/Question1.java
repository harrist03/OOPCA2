package OOPCA2;

import java.util.ArrayList;

/**
 * Your Name: Harris Teh Kai Ze
 * Class Group: SD2B
 */
public class Question1 { // Interfaces
    public static void main(String[] args) {
        System.out.println("Question 1");

        ContainerManager manager = new ContainerManager();

        // create instances of box, pyramid and cylinder and add to manager
        manager.add(new Box(10, 5, 8, 2.5));
        manager.add(new Pyramid(3.8, 8, 5));
        manager.add(new Cylinder(20, 8, 7.3));
        manager.add(new Box(15, 8, 10, 10));
        manager.add(new Pyramid(8.8, 12, 6.7));
        manager.add(new Cylinder(5, 2, 3));

        // Call totalWeight() and totalRectangularVolume() and display the values
        System.out.println("Total Weight: " + manager.totalWeight());
        System.out.println("Total Rectangular Volume: " + manager.totalRectangularVolume());
        
        ArrayList<IMeasurableContainer> containers = manager.getAllContainers();

        // print out all the fields
        for (IMeasurableContainer container : containers) {
            if (container instanceof Box box) {
                System.out.println("Box - Length: " + box.getLength() +
                        ", Width: " + box.getWidth() +
                        ", Depth: " + box.getDepth() +
                        ", Weight: " + box.weight());
            } else if (container instanceof Pyramid pyramid) {
                System.out.println("Pyramid - Length: " + pyramid.getLength() +
                        ", Side Length: " + pyramid.getSideLength() +
                        ", Weight: " + pyramid.weight());
            } else if (container instanceof Cylinder cylinder) {
                System.out.println("Cylinder - Height: " + cylinder.getHeight() +
                        ", Diameter: " + cylinder.getDiameter() +
                        ", Weight: " + cylinder.weight());
            }
        }

    }
}
