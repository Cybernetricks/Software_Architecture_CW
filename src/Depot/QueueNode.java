package Depot;

public class QueueNode {
  private int position;
  private Customer customer;
  private QueueNode next;

  public QueueNode(int position, Customer customer)
  {
    this.position = position;
    this.customer = customer;
    this.next = null;
  }

  public int getPosition() {
    return position;
  }

  public Customer getCustomer() {
    return customer;
  }

  public QueueNode getNextQueueNode() {
    return next;
  }

  public void setNextQueueNode(QueueNode queueNode) {
    this.next = queueNode;
  }
}
