package Depot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Log {
  private String[] event;
  private int current_Size = 0;

  public Log(int max) 
  {
    event = new String[max];
  }

  public void readFromLogFile(String filename)
  {
		try{
	      FileReader file_Reader = new FileReader(filename);
	      BufferedReader buffered_Reader = new BufferedReader(file_Reader);
	      String line; 
	      while(buffered_Reader.ready()){
	          line = buffered_Reader.readLine();
	          String[] array = line.split(";");
	          event[current_Size++] = array[0]; // firstname
	       } // end of outer while loop
	      buffered_Reader.close();
        for(int i=0; i<current_Size; i++) //Can remove
        {
          System.out.println(event[i]);
        }
		}catch(Exception e){
			e.printStackTrace();
	  }
	}

  public void addToEventLog(String event)
  {
    this.event[current_Size++] = "Event " + current_Size + "\n" + event + "\n";
  }

  public void writeToLogFile(String filename)
  {
    StringBuffer eventLog = new StringBuffer();
		for (int i = 0; i < current_Size; i++)
			eventLog.append(event[i] + "\n");
		try{
			FileWriter wr = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(wr);
			bw.write(eventLog.toString());
			bw.close();
		  }catch(Exception e){
			  System.out.println(e.getMessage());
		  }
  }

  //Test
  public static void main(String args[]){
		Log log = new Log(100);
		log.readFromLogFile("log.txt");
    log.writeToLogFile("log.txt");
	}
}
