package Depot;

//reference - https://www.geeksforgeeks.org/queue-linked-list-implementation/

public class QueueOfCustomers {
  private QueueNode head, rear;

  public QueueOfCustomers()
  {
    head = rear = null;
  }

  public String toString()
  {
    QueueNode currentNode = head;
    String queueString = "";
    while(currentNode.getNextQueueNode() != null){
      queueString += currentNode.getCustomer().getName();
      queueString += " ";
      queueString += currentNode.getCustomer().getParcelId();
      queueString += "\n";
      currentNode = currentNode.getNextQueueNode();
    }
    queueString += currentNode.getCustomer().getName();
    queueString += " ";
    queueString += currentNode.getCustomer().getParcelId();
    return queueString;
  }

  public void enqueue(int position, Customer customer)
  {
    QueueNode new_Queue_Node = new QueueNode(position, customer);

    if (rear == null) {
      rear = new_Queue_Node;
      head = rear;
      return;
    }

    rear.setNextQueueNode(new_Queue_Node);
    rear = new_Queue_Node;
  }

  public void dequeue()
  {
    if (isEmpty()) {
      return;
    }

    head = head.getNextQueueNode();

    if (head == null) {
      rear = null;
    }
  }

  public int getHeadPosition()
  {
    if (isEmpty()) {
      return 0;
    }
    return head.getPosition();
  }

  public int getRearPosition()
  {
    if (isEmpty()) {
      return 0;
    }
    return rear.getPosition();
  }

  public Customer getHeadCustomer()
  {
    if (isEmpty()) {
      return null;
    }
    return head.getCustomer();
  }

  private boolean isEmpty()
  {
    return head == null && rear == null;
  }
}
