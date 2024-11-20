package OOPCA2;

import java.util.ArrayList;

public class ContainerManager {
    public ArrayList<IMeasurableContainer> containers = new ArrayList<IMeasurableContainer>();

    public void addContainer(IMeasurableContainer container) {
        containers.add(container);
    }

    public double totalWeight() {
        double totalWeight = 0;
        for (IMeasurableContainer container : containers) {
            totalWeight += container.weight();
        }
        return totalWeight;
    }

    public double totalRectangularVolume() {
        double totalRectangularVolume = 0;
        for (IMeasurableContainer container : containers) {
            totalRectangularVolume += container.rectangularVolume();
        }
        return totalRectangularVolume;
    }

    public void clearAll() {
        containers.clear();
    }

    public ArrayList<IMeasurableContainer> getAllContainers() {
        return containers;
    }
}
