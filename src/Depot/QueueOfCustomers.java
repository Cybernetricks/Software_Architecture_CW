package Depot;

//reference - https://www.geeksforgeeks.org/queue-linked-list-implementation/

public class QueueOfCustomers {
  private QueueNode head, rear;

  public QueueOfCustomers()
  {
    head = rear = null;
  }

  public boolean isEmpty()
  {
    return head == null && rear == null;
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

  public Customer getHeadCustomer()
  {
    if (isEmpty()) {
      return null;
    }
    return head.getCustomer();
  }
}
