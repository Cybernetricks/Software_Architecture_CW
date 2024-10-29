package Depot;

public class QueueNode {
  private int position;
  private Customer customer;

  public QueueNode(int position, Customer customer)
  {
    this.position = position;
    this.customer = customer;
  }

  public int getPosition() {
    return position;
  }

  public Customer getCustomer() {
    return customer;
  } 
}
