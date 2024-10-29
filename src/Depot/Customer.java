package Depot;

public class Customer {
  private String name, parcelId;
  private int queueSequenceNumber;

  public Customer(String name, String parcelId)
  {
    this.name = name;
    this.parcelId = parcelId;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getParcelId() {
    return parcelId;
  }
  public void setParcelId(String parcelId) {
    this.parcelId = parcelId;
  }
  public int getQueueSequenceNumber() {
    return queueSequenceNumber;
  }
  public void setQueueSequenceNumber(int queueSequenceNumber) {
    this.queueSequenceNumber = queueSequenceNumber;
  }
}
