package Scheduling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is used to parse data from different Json Files and display it for
 * the easy understanding of the user
 * Implements interface Search
 * extends abstract class Station
 * @author Nalini Krishna Teja Chalasani
 *
 */
public class Scheduling extends Station implements Search {
	String lineCode;
	String stationCode;
	String Dest;
	Vector<String> origin = new Vector<String>();
	Vector<String> originCode = new Vector<String>();
	JComboBox<String> c1 = new JComboBox<String>(origin);
	Vector<String> destination = new Vector<String>();
	JComboBox<String> c2 = new JComboBox<String>(destination);
	Vector<String> time = new Vector<String>();
	JComboBox<String> c3 = new JComboBox<String>(time);
	Vector<String> stations = new Vector<String>();
	JComboBox<String> stat = new JComboBox<String>(stations);
	String Message;
	String StationCode;
	String Latitude;
	String Longitude;
	String allDayCount;
	String shortTimeCount;
	JButton email = new JButton("Send Email");
	JTextField jtf = new JTextField("email");
	boolean temp = true;
	String line;
	String car;
	String Origin;
	String Destination;
	String Min;
	JPanel ptab;
	JPanel ptab1;
	JPanel ptab2;
	String distance;
	String Time;
	String peakTimeFare;
	String offPeakTimeFare;
	String disabledFare;
	JPanel p;
	JFrame jf;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel labelf;
	JLabel labelg;
	JLabel labelh;
	JLabel labeli;
	JLabel labelj;
	JLabel labelp1;
	JLabel labelp2;
	JLabel labelp3;
	JLabel labelp4;
	JLabel labelp5;
	JLabel labelk;
	JLabel labell;
	JLabel labelm;
	JLabel labeln;
	JLabel labelo;
	JButton back;
	JLabel labelz;
	boolean reservetemp = true;

	JTabbedPane tabbedPane;

	/**
	 * This constructor calls the frame
	 */
	public Scheduling() {
		frame();
	}

	/**
	 * This function is used to set the frame
	 */
	public void frame() {
		jf = new JFrame();
		jf.setSize(1000, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel(new BorderLayout());
		Box mainbox = Box.createHorizontalBox();
		back = new JButton("Back");
		createPage1();
		createPage2();
		createPage3();
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Search", ptab);
		tabbedPane.addTab("Reserve", ptab1);
		tabbedPane.addTab("Parking", ptab2);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, true);
		p.add(tabbedPane, BorderLayout.CENTER);
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				tabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = tabbedPane.getSelectedIndex();
				if (index == 0) {
					stat.setSelectedIndex(0);
					labelp1.setText("");
					labelp2.setText("");
					labelp3.setText("");
					labelp4.setText("");
					labelp5.setText("");
					labelz.setText("");
					labelk.setVisible(false);
					labell.setVisible(false);
					labelm.setVisible(false);
					labeln.setVisible(false);
					labelo.setVisible(false);
				}
			}
		};
		tabbedPane.addChangeListener(changeListener);

		Font myfont = new Font("Serif", Font.BOLD, 50);
		JLabel lable1 = new JLabel("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRailway Reservation");
		JLabel label9 = new JLabel("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
		JLabel label10 = new JLabel("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
		lable1.setFont(myfont);
		mainbox.add(lable1);
		mainbox.add(Box.createHorizontalGlue());
		mainbox.add(back);
		p.add(mainbox, BorderLayout.NORTH);
		p.add(label9, BorderLayout.EAST);
		p.add(label10, BorderLayout.WEST);

		back.setVisible(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					back.setVisible(false);
					tabbedPane.setSelectedIndex(0);
					c1.setSelectedIndex(0);
					c2.setSelectedIndex(0);
					c3.setSelectedIndex(0);
					stat.setSelectedIndex(0);
					// for(int i = 0;i<destination.size();i++)
					destination.removeAllElements();
					time.removeAllElements();
					temp = true;
					labelp1.setText("");
					labelp2.setText("");
					labelp3.setText("");
					labelp4.setText("");
					labelp5.setText("");
					jtf.setText("email");
					labelk.setVisible(false);
					labell.setVisible(false);
					labelm.setVisible(false);
					labeln.setVisible(false);
					labelo.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		jf.setVisible(true);

	}

	/**
	 * This is used to create a page which displays the station details based on
	 * user choice of station
	 */
	public void createPage3() {
		ptab2 = new JPanel(new BorderLayout());
		Font myfont1 = new Font("Serif", Font.BOLD, 20);
		Font myfont2 = new Font("Serif", Font.PLAIN, 20);
		Box box1 = Box.createVerticalBox();
		Box box2 = Box.createVerticalBox();
		labelk = new JLabel("Station Code: ");
		labelk.setFont(myfont1);
		labelk.setVisible(false);
		labell = new JLabel("Latitude: ");
		labell.setFont(myfont1);
		labell.setVisible(false);
		labelm = new JLabel("Longitude: ");
		labelm.setFont(myfont1);
		labelm.setVisible(false);
		labeln = new JLabel("All Day Parking Slots: ");
		labeln.setFont(myfont1);
		labeln.setVisible(false);
		labelo = new JLabel("Short Time Parking Slots: ");
		labelo.setFont(myfont1);
		labelo.setVisible(false);
		box1.add(labelk);
		box1.add(Box.createVerticalStrut(20));
		box1.add(labell);
		box1.add(Box.createVerticalStrut(20));
		box1.add(labelm);
		box1.add(Box.createVerticalStrut(20));
		box1.add(labeln);
		box1.add(Box.createVerticalStrut(20));
		box1.add(labelo);
		labelp1 = new JLabel();
		labelp1.setFont(myfont2);
		labelp2 = new JLabel();
		labelp2.setFont(myfont2);
		labelp3 = new JLabel();
		labelp3.setFont(myfont2);
		labelp4 = new JLabel();
		labelp4.setFont(myfont2);
		labelp5 = new JLabel();
		labelp5.setFont(myfont2);
		box2.add(labelp1);
		box2.add(Box.createVerticalStrut(20));
		box2.add(labelp2);
		box2.add(Box.createVerticalStrut(20));
		box2.add(labelp3);
		box2.add(Box.createVerticalStrut(20));
		box2.add(labelp4);
		box2.add(Box.createVerticalStrut(20));
		box2.add(labelp5);
		box2.add(Box.createVerticalStrut(20));
		ptab2.add(stat, BorderLayout.NORTH);
		ptab2.add(box1, BorderLayout.CENTER);
		ptab2.add(box2, BorderLayout.EAST);
		FileReader StationRead;
		if (temp)
			stations.add("--");
		try {
			StationRead = new FileReader("station.txt");
			JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
			JSONArray jsonStatArr = (JSONArray) jsonStatObj.get("Stations");
			for (int i = 0; i < jsonStatArr.size(); i++) {
				JSONObject jsonObj = (JSONObject) jsonStatArr.get(i);
				stations.add(jsonObj.get("Name").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		stat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = stat.getSelectedIndex();
				FileReader StationRead;
				try {
					StationRead = new FileReader("station.txt");
					JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
					JSONArray jsonStatArr = (JSONArray) jsonStatObj.get("Stations");
					JSONObject jsonObj = (JSONObject) jsonStatArr.get(index);
					StationCode = jsonObj.get("Code").toString();
					stationDetails(StationCode);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Retrieves and displays station details based on input station code
	 * 
	 * @param code
	 *            Takes station code as input
	 */
	public void stationDetails(String code) {
		try {
			URL uStat = new URL(
					"https://api.wmata.com/Rail.svc/json/jStationInfo?api_key=6e09576695ae4156ac6a367de0c329c1&StationCode="
							+ code);
			@SuppressWarnings("resource")
			Scanner sStat = new Scanner(uStat.openStream());
			PrintWriter write = new PrintWriter("stationDetails.txt", "UTF-8");
			while (sStat.hasNext())
				write.println(sStat.nextLine());
			write.close();
			FileReader StationRead;
			StationRead = new FileReader("stationDetails.txt");
			JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
			Latitude = jsonStatObj.get("Lat").toString();
			Longitude = jsonStatObj.get("Lon").toString();
			parkingDetails(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the parking details
	 * 
	 * @param code
	 *            Retrieves the parking information of station based on station
	 *            code
	 */
	public void parkingDetails(String code) {
		try {
			URL uStat = new URL(
					"https://api.wmata.com/Rail.svc/json/jStationParking?api_key=6e09576695ae4156ac6a367de0c329c1&StationCode="
							+ code);
			@SuppressWarnings("resource")
			Scanner sStat = new Scanner(uStat.openStream());
			PrintWriter write = new PrintWriter("parkingDetails.txt", "UTF-8");
			while (sStat.hasNext())
				write.println(sStat.nextLine());
			write.close();
			FileReader StationRead;
			StationRead = new FileReader("parkingDetails.txt");
			JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
			JSONArray jsonDetArr = (JSONArray) jsonStatObj.get("StationsParking");
			if (jsonDetArr.size() == 0) {
				allDayCount = "0";
				shortTimeCount = "0";
			} else {
				JSONObject jsonDetObj1 = (JSONObject) jsonDetArr.get(0);
				JSONObject jsonAllDayObj = (JSONObject) jsonDetObj1.get("AllDayParking");
				allDayCount = jsonAllDayObj.get("TotalCount").toString();
				JSONObject jsonShortTimeObj = (JSONObject) jsonDetObj1.get("ShortTermParking");
				shortTimeCount = jsonShortTimeObj.get("TotalCount").toString();
			}
			labelk.setVisible(true);
			labell.setVisible(true);
			labelm.setVisible(true);
			labeln.setVisible(true);
			labelo.setVisible(true);
			labelp1.setText(code);
			labelp2.setText(Latitude);
			labelp3.setText(Longitude);
			labelp4.setText(allDayCount);
			labelp5.setText(shortTimeCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a page to diaplay details about user itenary selected User can
	 * also send email about the details and reservations
	 */
	public void createPage2() {
		JButton park = new JButton("Parking Details");
		ptab1 = new JPanel(new BorderLayout());
		Font myfont1 = new Font("Serif", Font.BOLD, 20);
		Font myfont2 = new Font("Serif", Font.PLAIN, 20);
		JLabel label1 = new JLabel("Line: ");
		label1.setFont(myfont1);
		JLabel label2 = new JLabel("Car: ");
		label2.setFont(myfont1);
		JLabel label3 = new JLabel("Destination: ");
		label3.setFont(myfont1);
		JLabel label4 = new JLabel("MIN: ");
		label4.setFont(myfont1);
		JLabel labela = new JLabel("Distance between stations: ");
		labela.setFont(myfont1);
		JLabel labelb = new JLabel("Time Taken For Journey: ");
		labelb.setFont(myfont1);
		JLabel labelc = new JLabel("Peak Time Fare: ");
		labelc.setFont(myfont1);
		JLabel labeld = new JLabel("Off Peak Time Fare: ");
		labeld.setFont(myfont1);
		JLabel labele = new JLabel("Disabled Fare: ");
		labele.setFont(myfont1);
		labelz = new JLabel();
		labelz.setFont(myfont1);
		label5 = new JLabel();
		label5.setFont(myfont2);
		label6 = new JLabel();
		label6.setFont(myfont2);
		label7 = new JLabel();
		label7.setFont(myfont2);
		label8 = new JLabel();
		label8.setFont(myfont2);
		labelf = new JLabel();
		labelf.setFont(myfont2);
		labelg = new JLabel();
		labelg.setFont(myfont2);
		labelh = new JLabel();
		labelh.setFont(myfont2);
		labeli = new JLabel();
		labeli.setFont(myfont2);
		labelj = new JLabel();
		labelj.setFont(myfont2);
		Box box1 = Box.createVerticalBox();
		Box box2 = Box.createVerticalBox();
		box1.add(label1);
		box1.add(Box.createVerticalStrut(10));
		box1.add(label2);
		box1.add(Box.createVerticalStrut(10));
		box1.add(label3);
		box1.add(Box.createVerticalStrut(10));
		box1.add(label4);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labela);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labelb);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labelc);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labeld);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labele);
		box1.add(Box.createVerticalStrut(10));
		box1.add(labelz);
		box1.add(Box.createVerticalStrut(20));
		box1.add(jtf);
		box2.add(label5);
		box2.add(Box.createVerticalStrut(10));
		box2.add(label6);
		box2.add(Box.createVerticalStrut(10));
		box2.add(label7);
		box2.add(Box.createVerticalStrut(10));
		box2.add(label8);
		box2.add(Box.createVerticalStrut(10));
		box2.add(labelf);
		box2.add(Box.createVerticalStrut(10));
		box2.add(labelg);
		box2.add(Box.createVerticalStrut(10));
		box2.add(labelh);
		box2.add(Box.createVerticalStrut(10));
		box2.add(labeli);
		box2.add(Box.createVerticalStrut(10));
		box2.add(labelj);
		box2.add(Box.createVerticalStrut(38));
		box2.add(email);
		ptab1.add(box1, BorderLayout.CENTER);
		ptab1.add(box2, BorderLayout.EAST);
		ptab1.add(park, BorderLayout.SOUTH);
		park.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					tabbedPane.setSelectedIndex(2);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	/**
	 * Creates a page where user can select stations from drop down
	 */
	public void createPage1() {

		ptab = new JPanel(new BorderLayout());
		JButton submit = new JButton("SUBMIT");
		JButton reserve = new JButton("RESERVE");
		Box right = Box.createHorizontalBox();
		Box botbox = Box.createHorizontalBox();
		JLabel lable2 = new JLabel("From: ");
		right.add(lable2);
		right.add(c1);
		right.add(Box.createHorizontalStrut(0));
		JLabel lable3 = new JLabel("To: ");
		right.add(lable3);
		right.add(c2);
		right.add(Box.createHorizontalStrut(0));
		JLabel lable4 = new JLabel("Leaving In : ");
		right.add(lable4);
		right.add(c3);
		ptab.add(right, BorderLayout.CENTER);
		botbox.add(Box.createHorizontalStrut(0));
		botbox.add(submit);
		botbox.add(Box.createHorizontalStrut(650));
		botbox.add(reserve);
		ptab.add(botbox, BorderLayout.SOUTH);
		jf.add(p);
		URL uStat;
		try {
			uStat = new URL("https://api.wmata.com/Rail.svc/json/jStations?api_key=6e09576695ae4156ac6a367de0c329c1");
			@SuppressWarnings("resource")
			Scanner sStat = new Scanner(uStat.openStream());
			PrintWriter write = new PrintWriter("station.txt", "UTF-8");
			while (sStat.hasNext())
				write.println(sStat.nextLine());
			write.close();
			FileReader StationRead = new FileReader("station.txt");
			JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
			JSONArray jsonStatArr = (JSONArray) jsonStatObj.get("Stations");
			if (temp)
				origin.add("--");
			for (int i = 0; i < jsonStatArr.size(); i++) {
				JSONObject jsonObj = (JSONObject) jsonStatArr.get(i);
				origin.add(jsonObj.get("Name").toString());
				originCode.add(jsonObj.get("Code").toString());
			}
			destination.removeAllElements();
			time.removeAllElements();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = c1.getSelectedIndex();
				Origin = origin.elementAt(index);
				stationCode = originCode.elementAt(index);
				try {
					URL uStat = new URL("https://api.wmata.com/StationPrediction.svc/json/GetPrediction/" + stationCode
							+ "?api_key=6e09576695ae4156ac6a367de0c329c1");
					@SuppressWarnings("resource")
					Scanner sStat = new Scanner(uStat.openStream());
					PrintWriter write = new PrintWriter("origin.txt", "UTF-8");
					while (sStat.hasNext())
						write.println(sStat.nextLine());
					write.close();
					FileReader StationRead = new FileReader("origin.txt");
					JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
					JSONArray jsonStatArr = (JSONArray) jsonStatObj.get("Trains");
					if (temp)
						destination.add("--");
					for (int i = 0; i < jsonStatArr.size(); i++) {
						JSONObject jsonObj = (JSONObject) jsonStatArr.get(i);
						int flag = 0;
						for (int j = 0; j < destination.size(); j++) {
							if (destination.elementAt(j).equals(jsonObj.get("DestinationName").toString())) {
								flag = 1;
								break;
							}
						}
						if (flag == 0) {
							destination.add(jsonObj.get("DestinationName").toString());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dest = c2.getSelectedItem().toString();
				FileReader StationRead;
				try {
					StationRead = new FileReader("origin.txt");
					JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
					JSONArray jsonStatArr = (JSONArray) jsonStatObj.get("Trains");
					if (temp) {
						time.add("--");
						temp = false;
					}
					for (int i = 0; i < jsonStatArr.size(); i++) {
						JSONObject jsonObj = (JSONObject) jsonStatArr.get(i);
						if (Dest.equals(jsonObj.get("DestinationName").toString())) {
							time.add(jsonObj.get("Min").toString());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tim = c3.getSelectedItem().toString();
				FileReader StationRead;
				try {
					StationRead = new FileReader("origin.txt");
					JSONObject jsonStatObj = (JSONObject) new JSONParser().parse(StationRead);
					JSONArray jsonStatArr = (JSONArray) jsonStatObj.get("Trains");
					for (int i = 0; i < jsonStatArr.size(); i++) {
						JSONObject jsonObj = (JSONObject) jsonStatArr.get(i);
						if (Dest.equals(jsonObj.get("DestinationName").toString())
								&& tim.equals(jsonObj.get("Min").toString())) {
							line = jsonObj.get("Line").toString();
							label5.setText(line);
							car = jsonObj.get("Car").toString();
							label6.setText(car);
							Destination = jsonObj.get("DestinationName").toString();
							label7.setText(Destination);
							Min = jsonObj.get("Min").toString();
							label8.setText(Min);
						}
					}
					FileReader fr = new FileReader("station.txt");
					JSONObject jsonStatObj1 = (JSONObject) new JSONParser().parse(fr);
					JSONArray jsonStatArr1 = (JSONArray) jsonStatObj1.get("Stations");
					String desCode = "";
					String oriCode = "";
					for (int i = 1; i < jsonStatArr1.size(); i++) {
						JSONObject jsonObj = (JSONObject) jsonStatArr1.get(i);
						if (jsonObj.get("Name").toString().equals(Destination)) {
							desCode = jsonObj.get("Code").toString();
						} else if (jsonObj.get("Name").toString().equals(Origin)) {
							oriCode = jsonObj.get("Code").toString();
						}
					}
					URL uStat = new URL(
							"https://api.wmata.com/Rail.svc/json/jSrcStationToDstStationInfo?api_key=6e09576695ae4156ac6a367de0c329c1&FromStationCode="
									+ oriCode + "&ToStationCode=" + desCode);
					@SuppressWarnings("resource")
					Scanner sStat = new Scanner(uStat.openStream());
					PrintWriter write = new PrintWriter("details.txt", "UTF-8");
					while (sStat.hasNext())
						write.println(sStat.nextLine());
					write.close();
					FileReader StationRead1 = new FileReader("details.txt");
					JSONObject jsonDetObj = (JSONObject) new JSONParser().parse(StationRead1);
					JSONArray jsonDetArr = (JSONArray) jsonDetObj.get("StationToStationInfos");
					JSONObject jsonDetObj1 = (JSONObject) jsonDetArr.get(0);
					distance = jsonDetObj1.get("CompositeMiles").toString();
					labelf.setText(distance);
					Time = jsonDetObj1.get("RailTime").toString();
					labelg.setText(Time);
					JSONObject jsonFareObj = (JSONObject) jsonDetObj1.get("RailFare");
					peakTimeFare = jsonFareObj.get("PeakTime").toString();
					labelh.setText(peakTimeFare);
					offPeakTimeFare = jsonFareObj.get("OffPeakTime").toString();
					labeli.setText(offPeakTimeFare);
					disabledFare = jsonFareObj.get("SeniorDisabled").toString();
					labelj.setText(disabledFare);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					tabbedPane.setSelectedIndex(1);
					back.setVisible(true);
					Message = "\n" + "Line: " + line + "\n" + "Car: " + car + "\n" + "Origin: " + Origin + "\n"
							+ "Destination: " + Destination + "\n" + "Min: " + Min + "\n"
							+ "Distance between stations: " + distance + " miles" + "\n" + "Time taken for journey: "
							+ Time + " min" + "\n" + "Peak Time Fare: " + peakTimeFare + "\n" + "Off Peak Time Fare: "
							+ offPeakTimeFare + "\n" + "Disabled Fare: " + disabledFare;
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					tabbedPane.setSelectedIndex(1);
					labelz.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\tRESERVED!!!!!");
					labelz.setForeground(Color.RED);
					back.setVisible(true);
					Message = "\n" + "Line: " + line + "\n" + "Car: " + car + "\n" + "Origin: " + Origin + "\n"
							+ "Destination: " + Destination + "\n" + "Min: " + Min + "\n"
							+ "Distance between stations: " + distance + " miles" + "\n" + "Time taken for journey: "
							+ Time + " min" + "\n" + "Peak Time Fare: " + peakTimeFare + "\n" + "Off Peak Time Fare: "
							+ offPeakTimeFare + "\n" + "Disabled Fare: " + disabledFare + "\n"
							+ "YOUR TICKET HAS BEEN RESERVED!";
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sendEmail se = new sendEmail("emailThread", Message, jtf.getText());
				se.start();
			}
		});
	}
}
