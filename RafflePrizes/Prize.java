
package RafflePrizes;

public class Prize implements Comparable<Prize> {
    int id;
    String name;
    int distributionWeight;
    public Prize(int id, String name, int distributionWeight) {
        this.id = id;
        this.name = name;
        this.distributionWeight = distributionWeight;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getDistributionWeight() {
        return this.distributionWeight;
    }
    @Override
    public String toString()
    {
        return "{ id: " + this.id + " name: " + this.name + " DW: " + this.distributionWeight + " }";
    }
    @Override
    public int compareTo(Prize other) {
        return Integer.compare(distributionWeight, other.distributionWeight);
    }
}
