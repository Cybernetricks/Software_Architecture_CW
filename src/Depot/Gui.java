package Depot;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;


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
    FlowLayout flowLayoutBackground = new FlowLayout(FlowLayout.LEFT);
    flowLayoutBackground.setVgap(0);
    flowLayoutBackground.setHgap(0);
    background.setLayout(flowLayoutBackground);
    add(background);

    JPanel frameContainer = new JPanel();
    frameContainer.setLayout(new BoxLayout(frameContainer, BoxLayout.X_AXIS));
    frameContainer.setOpaque(false);

    JPanel extraButtonPanel = new JPanel(new GridLayout(5, 1));
    JPanel queueOfCustomersPanel = new JPanel();
    JPanel parcelPanel = new JPanel();

    extraButtonPanel.setPreferredSize(new Dimension(200, 750));
    extraButtonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    queueOfCustomersPanel.setPreferredSize(new Dimension(390, 750)); // width set at 390 for scroller
    queueOfCustomersPanel.setLayout(new BorderLayout());
    queueOfCustomersPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    JTextArea queueOfCustomersDisplay = new JTextArea();
    queueOfCustomersDisplay.setEditable(false); // set textArea non-editable
    JScrollPane queueOfCustomersScroll = new JScrollPane(queueOfCustomersDisplay);
    queueOfCustomersScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    parcelPanel.setPreferredSize(new Dimension(390, 750)); // width set at 390 for scroller
    parcelPanel.setLayout(new GridLayout(2, 1));
    parcelPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    JTextArea headParcelDisplay = new JTextArea();
    headParcelDisplay.setEditable(false);
    headParcelDisplay.setPreferredSize(new Dimension(390, 200));

    JTextArea parcelMapDisplay = new JTextArea();
    parcelMapDisplay.setEditable(false); // set textArea non-editable
    JScrollPane parcelMapScroll = new JScrollPane(parcelMapDisplay);
    parcelMapScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    JButton initiateButton = new JButton("Initiate collections");
    extraButtonPanel.add(initiateButton);
    initiateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        manager.initiateParcelMap("Parcels.csv");
        manager.initiateCustomerQueue("Custs.csv");
        JOptionPane.showMessageDialog(null, "Parcel collection and Customer Queue has been initialised");
        queueOfCustomersDisplay.setText(manager.customerQueueToString());
        headParcelDisplay.setText(manager.getHeadParcel());
        parcelMapDisplay.setText(manager.parcelMapToString());
      }
    });

    JButton addParcelButton = new JButton("Add Parcel To Collection");
    addParcelButton.setPreferredSize(new Dimension(170, 45));
    extraButtonPanel.add(addParcelButton);
    addParcelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String parcelId = JOptionPane.showInputDialog("Please enter parcel ID:");
        float length = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel length:"));
        float width = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel width:"));
        float height = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel height:"));
        float weight = Float.parseFloat(JOptionPane.showInputDialog("Please enter parcel weight:"));
        int daysInCollection = Integer.parseInt(JOptionPane.showInputDialog("Please enter how many days parcel has been in collection:"));
        manager.addParcelToParcelMap(new Parcel(parcelId, length, width, height, daysInCollection, weight));
        JOptionPane.showMessageDialog(null, "Parcel has been created and added to collection");
        parcelMapDisplay.setText(manager.parcelMapToString());
      }
    });

    JButton addCustomerButton = new JButton("Add Customer To Queue");
    addCustomerButton.setPreferredSize(new Dimension(170, 45));
    extraButtonPanel.add(addCustomerButton);
    addCustomerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String customerName = JOptionPane.showInputDialog("Please enter your name:");
        String parcelId = JOptionPane.showInputDialog("Please enter your expected Parcel ID");
        boolean result = manager.addCustomerToQueue(new Customer(customerName, parcelId));
        if (result) {
          JOptionPane.showMessageDialog(null, customerName + " has been added to queue");
          // createText(customerName + " has been added to queue", "Add Customer to Queue");
        } else {
          JOptionPane.showMessageDialog(null, customerName + " has not been added to queue due to parcel not being found");
          // createText(customerName + " has not been added to queue due to parcel not being found", "Add Customer to Queue");
        }
        queueOfCustomersDisplay.setText(manager.customerQueueToString());
      }
    });

    JButton processCustomerButton = new JButton("Process Current Customer");
    extraButtonPanel.add(processCustomerButton);
    processCustomerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        manager.processCurrentCustomerInQueue();
        queueOfCustomersDisplay.setText(manager.customerQueueToString());
        headParcelDisplay.setText(manager.getHeadParcel());
        parcelMapDisplay.setText(manager.parcelMapToString());
      }
    });

    queueOfCustomersPanel.add(queueOfCustomersScroll);

    parcelPanel.add(headParcelDisplay);
    parcelPanel.add(parcelMapScroll);

    frameContainer.add(extraButtonPanel);
    frameContainer.add(queueOfCustomersPanel);
    frameContainer.add(parcelPanel);

    background.add(frameContainer);

    frame.getContentPane().add(this);
    frame.setSize(1000, 800);

    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }

  @Override
    public void actionPerformed(ActionEvent e) {
  }
}
