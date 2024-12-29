package Depot;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Frame;

public class Gui extends JPanel implements ActionListener {
  private Manager manager;

  public Gui(Manager manager)
  {
    this.manager = manager;

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run()
      {
        createGui();
      }
    });
  }

  private void createText(String string, String title) {
    JPanel textPanel = new JPanel();
    textPanel.setBorder(new TitledBorder(new EtchedBorder(), title));
    textPanel.setBackground(new Color(255, 255, 255));

    JTextArea display = new JTextArea(16, 40);
    display.setText(string);
    display.setBackground(new Color(255, 255, 255));
    display.setEditable(false);
    JScrollPane scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    textPanel.add(scroll);
    Frame frame = new JFrame();
    frame.add(textPanel);
    frame.pack();

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setResizable(false);
  }

  private void createGui()
  {
    JFrame frame = new JFrame("Depot GUI");
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        manager.writeToLogFile("Log.txt");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      }
    });

    setLayout(new BorderLayout());

    JLabel background = new JLabel();
    FlowLayout fl_background = new FlowLayout(FlowLayout.LEFT);
    fl_background.setVgap(0);
    fl_background.setHgap(0);
    background.setLayout(fl_background);
    add(background);

    JPanel buttonPanel = new JPanel(new GridLayout(11, 1));
    buttonPanel.setBorder(new EmptyBorder(30, 30, 0, 0));
    buttonPanel.setOpaque(false);

    JButton initiateButton = new JButton("Initiate Parcel Collection and Customer Queue");
    initiateButton.setPreferredSize(new Dimension(170, 45));
    buttonPanel.add(initiateButton);
    initiateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        manager.initiateParcelMap("Parcels.csv");
        manager.initiateCustomerQueue("Custs.csv");
        createText("Parcel collection and Customer Queue has been initialised", "Initiate Parcel Collection and Customer Queue");
      }
    });

    JButton pmtsButton = new JButton("Show All Parcels");
    pmtsButton.setPreferredSize(new Dimension(170, 45));
    buttonPanel.add(pmtsButton);
    pmtsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        createText("id, length, width, height, weight, days in collection, collected?\n" + manager.parcelMapToString(), "List All Parcels");
      }
    });

    JButton cqtsButton = new JButton("Show All Customers");
    cqtsButton.setPreferredSize(new Dimension(170, 45));
    buttonPanel.add(cqtsButton);
    cqtsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        createText("Name, Parcel ID\n" + manager.customerQueueToString(), "List All Customers");
      }
    });

    JButton actqButton = new JButton("Add Customer To Queue");
    actqButton.setPreferredSize(new Dimension(170, 45));
    buttonPanel.add(actqButton);
    actqButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Add a new JPanel to get a customer and parcel then return to be used below
        String customerName = JOptionPane.showInputDialog("Please enter your name:");
        String parcelId = JOptionPane.showInputDialog("Please enter your expected Parcel ID");
        boolean result = manager.addCustomerToQueue(new Customer(customerName, parcelId));
        if (result) {
          createText(customerName + " has been added to queue", "Add Customer to Queue");
        } else {
          createText(customerName + " has not been added to queue due to parcel not being found", "Add Customer to Queue");
        }
      }
    });

    JButton pcciqButton = new JButton("Process Current Customer");
    pcciqButton.setPreferredSize(new Dimension(170, 45));
    buttonPanel.add(pcciqButton);
    pcciqButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        createText("Current customer has been processed\n" + "Please pay - £" + 
                    String.format("%.02f", manager.processCurrentCustomerInQueue()), "Process Current Customer");
      }
    });

    JButton aptpmButton = new JButton("Add Parcel To Collection");
    aptpmButton.setPreferredSize(new Dimension(170, 45));
    buttonPanel.add(aptpmButton);
    aptpmButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String parcelId = JOptionPane.showInputDialog("Please enter parcel ID:");
        float length = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel length:"));
        float width = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel width:"));
        float height = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel height:"));
        float weight = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel weight:"));
        int daysInCollection = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many days parcel has been in collection:"));
        manager.addParcelToParcelMap(new Parcel(parcelId, length, width, height, daysInCollection, weight));
        createText("Parcel has been created and added to collection" ,"Add Parcel To Collection");
      }
    });

    background.add(buttonPanel);

    frame.getContentPane().add(this);
    frame.setSize(900, 600);

    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }

  @Override
    public void actionPerformed(ActionEvent e) {
  }
}
