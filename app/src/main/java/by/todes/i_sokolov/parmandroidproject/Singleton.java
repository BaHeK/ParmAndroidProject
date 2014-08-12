package by.todes.i_sokolov.parmandroidproject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Locale;

import org.ksoap2.serialization.SoapObject;

public class Singleton {
	private static Singleton instance;
	private String[] sex = { "", "МУЖСКОЙ", "ЖЕНСКИЙ" };
	private String[][] status;
	private String[][] sysTripType;
	private String[][] modelAuto;
    private String[][] units;
	private String[][] classAuto;
	private String[][] tripType;
	private String[][] country;
	private String[][] target;
	private String[][] typeDocument;
	private String[][] typeVisa;
	private String[][] kindVisa;
	private String[][] mainDocument;
	private String[][] vector;
	private String[][] tripList;
	private String[][] passOnTripList;
	private SoapObject passList;
    private SoapObject trips;
    private SoapObject passDetailMain;
	private SoapObject passDetailDopDoc;
	private SoapObject passDetailPass;
	private String[][] ppr;
	private String KPP;
	private String name;
	private String pass;
	private SoapObject trip;
	private SoapObject user;
	private SoapObject checkPass;
	private SoapObject tripSelected = null;
	private String[][] listServers;
	private String serverItem;
	private Boolean clearData = false;
	private String idUser;
    private SoapObject cargoData;
//    private ArrayList<SoapObject> cargoDataIn;


	public int codeToPosition(String[][] type, String code) {
		int i;
		for (i = 0; i < type[0].length; i++) {
			if (type[0][i].equals(code)) {
				break;
			}
		}
		if (i == type[0].length){
			return codeToPosition(type, "0");
		}
		return i;
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	public static void setInstance(Singleton instance) {
		Singleton.instance = instance;
	}

	public String[][] getKlUni(SoapObject klUni) {
		String[][] type = new String[2][klUni.getPropertyCount()];
		for (Integer i = 0; i < klUni.getPropertyCount(); i++) {
			if (!((SoapObject) klUni.getProperty(i)).getProperty(2).toString()
					.equals("anyType{}")) {
				type[0][i] = ((SoapObject) klUni.getProperty(i)).getProperty(1)
						.toString();
				type[1][i] = ((SoapObject) klUni.getProperty(i)).getProperty(2)
						.toString();
			} else {
				type[0][i] = "0";
				type[1][i] = "";
			}
		}
		return type;
	}


	public String[][] getCountry() {
		return country;
	}

	public void setCountry(SoapObject klUni) {
		String[][] country = new String[3][klUni.getPropertyCount()];
		for (Integer i = 0; i < klUni.getPropertyCount(); i++) {
			if (!((SoapObject) klUni.getProperty(i)).getProperty(2).toString()
					.equals("anyType{}")) {
				country[0][i] = ((SoapObject) klUni.getProperty(i))
						.getProperty(1).toString();
				country[1][i] = ((SoapObject) klUni.getProperty(i))
						.getProperty(2).toString();
				country[2][i] = ((SoapObject) klUni.getProperty(i))
						.getProperty(3).toString();
			} else {
				country[0][i] = "0";
				country[1][i] = "";
				country[2][i] = "";
			}
		}
		this.country = country;
	}

	public String[][] getTarget() {
		return target;
	}

	public void setTarget(SoapObject klUni) {
		this.target = getKlUni(klUni);
	}

	public String[][] getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(SoapObject klUni) {
		this.typeDocument = getKlUni(klUni);
	}

	public String[][] getTypeVisa() {
		return typeVisa;
	}

	public void setTypeVisa(SoapObject klUni) {
		this.typeVisa = getKlUni(klUni);
	}

	public String[][] getKindVisa() {
		return kindVisa;
	}

	public void setKindVisa(SoapObject klUni) {
		this.kindVisa = getKlUni(klUni);
	}

	public String[][] getMainDocument() {
		return mainDocument;
	}

	public void setMainDocument(SoapObject klUni) {
		this.mainDocument = getKlUni(klUni);
	}

	public String getKPP() {
		return KPP;
	}

	public void setKPP(String kPP) {
		KPP = kPP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String[][] getVector() {
		return vector;
	}

	public void setVector() {
		String[][] type = new String[2][2];

		type[0][0] = "1";
		type[0][1] = "2";
//		type[0][2] = "0";
//		type[1][2] = "";
		type[1][0] = "ВЪЕЗД";
		type[1][1] = "ВЫЕЗД";

		this.vector = type;
	}

	public String[][] getTripList() {
		return tripList;
	}

	public void setTripList(SoapObject tripList) throws ParseException {
		setTrips(tripList);

        String[][] type = new String[1][tripList.getPropertyCount()];
		SimpleDateFormat formater1 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
		SimpleDateFormat formater2 = new SimpleDateFormat(
				"dd.MM.yyyy HH:mm:ss", Locale.getDefault());

		// 2013-08-06T16:38:21.000+04:00("yyyy-MM-ddTHH:mm:ss")
		for (Integer i = 0; i < tripList.getPropertyCount(); i++) {
			if (((SoapObject) tripList.getProperty(i))
					.getProperty("CSysTripTypeStr").toString().equals("ПЕШИЙ")) {
				type[0][i] = formater2.format(formater1
						.parse(((SoapObject) tripList.getProperty(i))
								.getProperty("tripDate").toString()))
						+ "   "
						+ ((SoapObject) tripList.getProperty(i)).getProperty(
								"CSysTripTypeStr").toString()
						+ "   "
						+ ((SoapObject) tripList.getProperty(i)).getProperty(
								"owner").toString();
			}
			if (((SoapObject) tripList.getProperty(i))
					.getProperty("CSysTripTypeStr").toString().equals("АВТО")) {
				type[0][i] = formater2.format(formater1
						.parse(((SoapObject) tripList.getProperty(i))
								.getProperty("tripDate").toString()))
						+ "   "
						+ ((SoapObject) tripList.getProperty(i)).getProperty(
								"CSysTripTypeStr").toString()
						+ "   "
						+ ((SoapObject) tripList.getProperty(i)).getProperty(
								"transpNum").toString();
			}
			if (!((SoapObject) tripList.getProperty(i))
					.getProperty("CSysTripTypeStr").toString().equals("ПЕШИЙ")
					&& !((SoapObject) tripList.getProperty(i))
							.getProperty("CSysTripTypeStr").toString()
							.equals("АВТО")) {
				type[0][i] = formater2.format(formater1
						.parse(((SoapObject) tripList.getProperty(i))
								.getProperty("tripDate").toString()))
						+ "   "
						+ ((((SoapObject) tripList.getProperty(i)).getProperty(
								"source").toString().equals("anyType{}")) ? " "
								: ((SoapObject) tripList.getProperty(i))
										.getProperty("source").toString())
						+ " - "
						+ ((((SoapObject) tripList.getProperty(i)).getProperty(
								"source").toString().equals("anyType{}")) ? " "
								: ((SoapObject) tripList.getProperty(i))
										.getProperty("destination").toString())
						+ "   "
						+ ((((SoapObject) tripList.getProperty(i)).getProperty(
								"source").toString().equals("anyType{}")) ? " "
								: ((SoapObject) tripList.getProperty(i))
										.getProperty("tripNum").toString());
			}
		}
		this.tripList = type;
	}

	public SoapObject getTrip() {
		return trip;
	}

	public void setTrip(SoapObject trip) {
		this.trip = trip;
	}

	public SoapObject getUser() {
		return user;
	}

	public void setUser(SoapObject user) {
		this.user = user;
	}

	public SoapObject getCheckPass() {
		return checkPass;
	}

	public void setCheckPass(SoapObject checkPass) {
		this.checkPass = checkPass;
	}

	public SoapObject getTripSelected() {
		return tripSelected;
	}

	public void setTripSelected(SoapObject tripSelected) {
		this.tripSelected = tripSelected;
	}

	public String[][] getPassOnTripList() {
		return passOnTripList;
	}

	public void setPassOnTripList(SoapObject list) {
		Integer i;
		Integer item = 0;
		for (i = 0; i < list.getPropertyCount(); i++) {
			if (((SoapObject) list.getProperty(i)).getProperty("viol")
					.toString().equals("0")) {
				item++;
			}
		}
		String[][] listPass = new String[2][item];
		item = 0;
		for (i = 0; i < list.getPropertyCount(); i++) {
			if (((SoapObject) list.getProperty(i)).getProperty("viol")
					.toString().equals("0")) {
				listPass[0][item] = ((SoapObject) list.getProperty(i))
						.getProperty("name").toString()
						+ " "
						+ ((SoapObject) list.getProperty(i)).getProperty(
								"latName").toString()
						+ " "
						+ ((SoapObject) list.getProperty(i)).getProperty(
								"paspNum").toString();
				listPass[1][item] = ((SoapObject) list.getProperty(i))
						.getProperty("did").toString();
				item++;
			}

		}
		this.passOnTripList = listPass;
	}

	public String[][] getListServers() {
		return listServers;
	}

	public void setListServers(String servers) {
		if (!servers.equals("")) {
			String ser = servers;
			int index = 0;
			while (ser.contains("$")) {
				ser = ser.substring(ser.indexOf("$") + 1);
				index++;
			}
			String[][] listServers = new String[2][index];
			ser = servers;
			for (int i = 0; i < index; i++) {

				listServers[0][i] = ser.substring(ser.indexOf("#") + 1,
						ser.indexOf("$"));

				listServers[1][i] = ser.substring(0, ser.indexOf("#"));

				ser = ser.substring(ser.indexOf("$") + 1);
			}
			this.listServers = listServers;
		}
	}

	public String getServerItem() {
		return serverItem;
	}

	public void setServerItem(String serverItem) {
		this.serverItem = serverItem;
	}

	public String[][] getPpr() {
		return ppr;
	}

	public void setPpr(SoapObject ppr) {
		String[][] type = new String[2][ppr.getPropertyCount() + 1];
		type[0][0] = "";
		type[1][0] = "";
		for (Integer i = 1; i <= ppr.getPropertyCount(); i++) {
			type[0][i] = ((SoapObject) ppr.getProperty(i - 1)).getProperty(0)
					.toString();
			type[1][i] = ((SoapObject) ppr.getProperty(i - 1)).getProperty(1)
					.toString();
		}
		this.ppr = type;
	}

	public Boolean getClearData() {
		return clearData;
	}

	public void setClearData(Boolean clearData) {
		this.clearData = clearData;
	}

	public SoapObject getPassList() {
		return passList;
	}

	public void setPassList(SoapObject passList) {
		this.passList = passList;
	}

	public SoapObject getPassDetailMain() {
		return passDetailMain;
	}

	public void setPassDetailMain(SoapObject passDetailMain) {
		this.passDetailMain = passDetailMain;
	}

	public SoapObject getPassDetailDopDoc() {
		return passDetailDopDoc;
	}

	public void setPassDetailDopDoc(SoapObject passDetailDopDoc) {
		this.passDetailDopDoc = passDetailDopDoc;
	}

	public SoapObject getPassDetailPass() {
		return passDetailPass;
	}

	public void setPassDetailPass(SoapObject passDetailPass) {
		this.passDetailPass = passDetailPass;
	}

	public String[] getSex() {
		return sex;
	}

	public void setSex(String[] sex) {
		this.sex = sex;
	}

	public String[][] getStatus() {
		return status;
	}

	public void setStatus(SoapObject klUni) {
		this.status = getKlUni(klUni);
	}
	
	public void setModelAuto(SoapObject klUni) {
		
		this.modelAuto = getKlUni(klUni);
	}
	
	public String[][] getModelAuto() {
		return modelAuto;
	}

	public String[][] getClassAuto() {
		return classAuto;
	}

	public void setClassAuto(SoapObject klUni) {
		this.classAuto = getKlUni(klUni);		
	}

	public String[][] getTripType() {
		return tripType;
	}

	public void setTripType(SoapObject klUni) {
		this.tripType = getKlUni(klUni);
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {

		this.idUser = idUser;
	}

	public String[][] getSysTripType() {
		return sysTripType;
	}

	public void setSysTripType(SoapObject klUni) {
		this.sysTripType = getKlUni(klUni);
	}


    public SoapObject getTrips() {
        return trips;
    }

    public void setTrips(SoapObject tripList) {
        this.trips = tripList;
    }


    public SoapObject getCargoData() {
        return cargoData;
    }

    public void setCargoData(SoapObject cargoData) {
//        setCargoDataIn(cargoData);
        this.cargoData = cargoData;
    }

//    public ArrayList<SoapObject> getCargoDataIn() {
//        return this.cargoDataIn;
//    }
//
//    public void setCargoDataIn(SoapObject cargo) {
//
//        cargoDataIn = new ArrayList<SoapObject>();
//
//        for (int i=0;i<cargo.getPropertyCount();i++) {
//            cargoDataIn.add((SoapObject) cargo.getProperty(i));
//        }
//
//    }

    public String[][] getUnits() {
        return units;
    }

    public void setUnits(SoapObject klUni) {
        this.units = getKlUni(klUni);
    }
}
