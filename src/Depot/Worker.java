package Depot;

public class Worker {
  private QueueOfCustomers queueOfCustomers;

  public Worker()
  {
    this.queueOfCustomers = new QueueOfCustomers();
  }

  public float processCurrentCustomerInQueue(Parcel parcel)
  {
    float collectionFee = calculateFee(parcel);
    releaseParcel(parcel);
    removeCurrentCustomerInQueue();

    return collectionFee;
  }

  public String getCurrentCustomerParcelId()
  {
    if (getHeadCustomer() != null) {
      return getHeadCustomer().getParcelId();
    }
    return null;
  }

  public float calculateFee(Parcel parcel)
  {
    return parcel.getWeight() * 0.2f * parcel.getDaysInCollection();
  }

  public void releaseParcel(Parcel parcel)
  {
    parcel.setCollected(true);
  }

  public String customerQueueToString()
  {
    return queueOfCustomers.toString();
  }

  public void addCustomerToQueue(Customer customer)
  {
    int newPosition = queueOfCustomers.getRearPosition() + 1;
    queueOfCustomers.enqueue(newPosition, customer);
  }

  public void removeCurrentCustomerInQueue()
  {
    queueOfCustomers.dequeue();
  }

  private Customer getHeadCustomer()
  {
    return queueOfCustomers.getHeadCustomer();
  }
}
