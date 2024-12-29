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
    return getHeadCustomer().getParcelId();
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

  public static void main(String args[]){
		Worker worker = new Worker();
    
    Customer customer = new Customer("Dick McFarts", null);
    worker.addCustomerToQueue(customer);
    System.out.println("Addition");
    System.out.print(worker.customerQueueToString());
    worker.removeCurrentCustomerInQueue();
    System.out.println("Subtraction");
    System.out.print(worker.customerQueueToString());
	}
}
