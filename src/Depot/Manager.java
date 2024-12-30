package Depot;

import java.io.BufferedReader;
import java.io.FileReader;

public class Manager {
  private Worker worker = new Worker();
  private ParcelMap parcelMap = new ParcelMap();
  private Log log = new Log(500);
  private Gui gui = new Gui(this);

  public Manager() {}

  public void initiateCustomerQueue(String filename)
  {
    try{
      FileReader file_Reader = new FileReader(filename);
      BufferedReader buffered_Reader = new BufferedReader(file_Reader);
      String line;
      while(buffered_Reader.ready()){
          line = buffered_Reader.readLine();
          String[] array = line.split(",");
          worker.addCustomerToQueue(new Customer(array[0], array[1].trim()));
       } // end of outer while loop
      buffered_Reader.close();
      addToEventLog("Customer Queue has been initialised from file: " + filename);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public boolean addCustomerToQueue(Customer customer)
  {
    if (parcelMap.getParcel(customer.getParcelId()) == null) {
      addToEventLog("Customer's Parcel " + customer.getParcelId() + " does not exist");
      return false;
    } else {
      worker.addCustomerToQueue(customer);
    }
    return true;
  }

  public String getHeadParcel()
  {
    String currentParcelId = worker.getCurrentCustomerParcelId();
    if (currentParcelId != null){
      Parcel parcel = parcelMap.getParcel(worker.getCurrentCustomerParcelId());
      String parcelDetails = parcel.toString() + "\nCollection Fee - £" + String.format("%.02f", worker.calculateFee(parcel));
      return parcelDetails;
    }
    return "";
  }

  public void processCurrentCustomerInQueue()
  {
    String currentParcelId = worker.getCurrentCustomerParcelId();
    if (currentParcelId != null){
      Parcel parcel = parcelMap.getParcel(worker.getCurrentCustomerParcelId());
      parcelMap.changeParcelState(parcel.getId());
      worker.processCurrentCustomerInQueue(parcel);
      addToEventLog("Parcel with ID " + parcel.getId() + " has been collected");
    }
  }

  public String customerQueueToString()
  {
    addToEventLog("All customers: " + worker.customerQueueToString());
    return worker.customerQueueToString();
  }

  public void initiateParcelMap(String filename)
  {
    try{
      FileReader file_Reader = new FileReader(filename);
      BufferedReader buffered_Reader = new BufferedReader(file_Reader);
      String line;
      while(buffered_Reader.ready()){
          line = buffered_Reader.readLine();
          String[] array = line.split(",");
          parcelMap.addParcel(new Parcel(array[0], Float.parseFloat(array[1]),
                                         Float.parseFloat(array[2]), Float.parseFloat(array[3]),
                                         Integer.parseInt(array[4]), Float.parseFloat(array[5])));
       }
      buffered_Reader.close();
      addToEventLog("Parcel collection has been initialised from file: " + filename);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void addParcelToParcelMap(Parcel parcel)
  {
    addToEventLog("Parcel " + parcel.getId() + " has been added to parcel collection");
    parcelMap.addParcel(parcel);
  }

  public String parcelMapToString()
  {
    addToEventLog("All parcels: " + parcelMap.toString());
    return parcelMap.toString();
  }

  public void addToEventLog(String event)
  {
    log.addToEventLog(event);
  }

  public void writeToLogFile(String filename)
  {
    log.writeToLogFile("log.txt");
  }

  public static void main(String args[]){
		Manager manager = new Manager();
	}
}
