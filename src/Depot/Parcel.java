package Depot;

public class Parcel {
  private String id;
  private float length, width, height, weight;
  private int daysInCollection = 0;
  private boolean collected = false;

  public Parcel(float length, float width, float height, float weight)
  {
    this.length = length;
    this.width = width;
    this.height = height;
    this.weight = weight;
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public float getLength() {
    return length;
  }

  public void setLength(float length) {
    this.length = length;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public int getDaysInCollection() {
    return daysInCollection;
  }

  public void setDaysInCollection(int daysInCollection) {
    this.daysInCollection = daysInCollection;
  }

  public boolean isCollected() {
    return collected;
  }

  public void setCollected(boolean collected) {
    this.collected = collected;
  }
}
