package by.todes.i_sokolov.parmandroidproject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.util.concurrent.Callable;

//import com.sun.xml.internal.fastinfoset.util.StringArray;
//import com.sun.xml.internal.fastinfoset.util.StringArray;

public class Requests {
	private SoapObject loginIn;
	private SoapObject klUni;
	private SoapObject tripList;
	private SoapObject getPassInfo;
	private SoapObject checkAllLst;
	private SoapObject regTrip;
	private SoapObject userDetails;
	private SoapObject regPass;
	private SoapObject passangersOnTrip;
	private SoapObject PPR;
	private SoapObject checkPass;
	private SoapObject getDocSetDataElement;
	private SoapObject getVisesElement;
	private SoapObject getPassangersElement;
	private SoapObject getCheckAutoLst;
	private SoapObject checkTrip;
	private SoapObject cargo;
	private SoapObject transpDataElement;
	Singleton ms = Singleton.getInstance();

	public SoapObject getLoginIn(String userName, String password,
			String cSysOrgan, String logintype, String newPass) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"loginUserElement");
		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");
		SoapObject request3 = new SoapObject(SoapConstants.NAMESPACE, "ipAdrIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");


		pa.setValue(userName);

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(password);

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(cSysOrgan);

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:loginTypeIn", logintype);
		request.addProperty("n0:errCountIn", "0");
		request.addProperty("n0:newpasswordIn", newPass);
		request.addProperty("n0:clientUserIn", "");
		request.addProperty("n0:machineIn", "");
		request.addProperty("n0:terminalIn", "");
		request.addProperty("n0:progIn", "");
		request.addProperty("n0:progPathIn", "");
		request.addProperty("n0:progVersionIn", "");
		request.addProperty("n0:machineServerIn", "");
		request.addProperty("n0:userAuditDidIn", "");

		request.addSoapObject(request3);

		request3.addProperty("n0:idCargo", "172.22.5.18");

		request.addProperty("n0:cSysOrganIn", ms.getKPP());
		
		
    
		loginIn = request;
		return loginIn;
	}

	public void setLoginIn(SoapObject loginIn) {
		this.loginIn = loginIn;
	}

	public SoapObject getKlUni(String userName, String password,
			String cSysOrgan, String klType) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getKlassifFullElement");
		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(userName);

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(password);

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(cSysOrgan);

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		PropertyInfo pp = new PropertyInfo();
		pp.setName("n0:codeIn xmlns:ns2=\"http://www.w3.org/2001/XMLSchema-instance\" ns2:nil=\"true\"");
		// pp.setType(null);

		request.addProperty("n0:typeIn", klType);
		request.addProperty(pp);
		request.addProperty("n0:orderIn", "1");
		klUni = request;
		return klUni;
	}

	public void setKlUni(SoapObject klUni) {
		this.klUni = klUni;
	}

	public SoapObject getTripList(String userName, String password,
			String cSysOrgan, String autoType, String vector) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getTripsListElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(userName);

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(password);

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(cSysOrgan);

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:cDirectionIn", vector);
		request.addProperty(
				"n0:startDateIn xmlns:ns3=\"http://www.w3.org/2001/XMLSchema-instance\" ns3:nil=\"true\"",
				null);
		request.addProperty(
				"n0:endDateIn xmlns:ns4=\"http://www.w3.org/2001/XMLSchema-instance\" ns4:nil=\"true\"",
				null);
		request.addProperty("n0:cSysTripTypeIn", autoType);
		request.addProperty("n0:armIn", "1");
		tripList = request;
		return tripList;
	}

	public void setTripList(SoapObject tripList) {
		this.tripList = tripList;
	}

	public SoapObject getGetPassInfo(String docNumber, String bDate) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getPassInfoElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

        PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:docNumIn", docNumber);
		request.addProperty("n0:bdateIn", bDate);

		getPassInfo = request;

		return getPassInfo;
	}

	public void setGetPassInfo(SoapObject getPassInfo) {
		this.getPassInfo = getPassInfo;
	}

	public SoapObject getCheckAllLst(String name, String latname, String sex,
			String bdate, String citi, String docnum, String ident,
			String direct, String iduser) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"checkAllLstElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:nameIn", name);
		request.addProperty("n0:latNameIn", latname);
		request.addProperty("n0:sexIn", sex);
		request.addProperty("n0:bdateIn", bdate);
		request.addProperty("n0:citiIn", citi);
		request.addProperty("n0:docNumIn", docnum);
		request.addProperty("n0:identifIn", ident);
		request.addProperty("n0:directionIn", direct);
		request.addProperty("n0:idUserIn", iduser);

		checkAllLst = request;
		return checkAllLst;
	}

	public void setCheckAllLst(SoapObject checkAllLst) {
		this.checkAllLst = checkAllLst;
	}

	public SoapObject getUserDetails() {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getUserDetailsElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		request.addProperty("n0:usernameIn", ms.getName());
		userDetails = request;
		return userDetails;
	}

	public void setUserDetails(SoapObject userDetails) {
		this.userDetails = userDetails;
	}

	public SoapObject getRegPass(String name, String latname, String sex,
			String bdate, String citi, String docnum, String ident,
			String paspType, String expireDate, String citiOutIn, String goal,
			String ridIn) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"regPassElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:tripIdIn",
				ms.getTripSelected().getProperty("tripId").toString());
		request.addProperty("n0:cDerectionIn", ms.getTripSelected().getProperty("CDirection"));
		request.addProperty("n0:nameIn", name);
		request.addProperty("n0:latNameIn", latname);
		request.addProperty("n0:sexIn", sex);
		request.addProperty("n0:bdateIn", bdate);
		request.addProperty("n0:citiIn", citi);
		request.addProperty("n0:paspNumIn", docnum);
		request.addProperty("n0:identifIn", ident);
		request.addProperty("n0:paspTypeIn", paspType);
		request.addProperty("n0:expireDateIn", expireDate);
		request.addProperty("n0:citiOutIn", citiOutIn);
		request.addProperty("n0:goalIn", goal);
		request.addProperty("n0:idUserIn", ((SoapObject) ms.getUser()
				.getProperty(0)).getProperty("paramValue").toString());
		request.addProperty(
				"n0:visaDatebIn xmlns:ns2=\"http://www.w3.org/2001/XMLSchema-instance\" ns2:nil=\"true\"",
				null);
		request.addProperty(
				"n0:visaDateeIn xmlns:ns3=\"http://www.w3.org/2001/XMLSchema-instance\" ns3:nil=\"true\"",
				null);
		request.addProperty("n0:cTypeVisaIn", "2");
		request.addProperty("n0:cKindVisaIn", "2");
		request.addProperty("n0:visaNumIn", "");
		request.addProperty("n0:groupSignIn", "1");
		request.addProperty("n0:ridIn", ridIn);
		request.addProperty("n0:cDocTypeIn", "2");
		request.addProperty("n0:docOrganIn", "");
		regPass = request;
		return regPass;
	}

	public void setRegPass(SoapObject regPass) {
		this.regPass = regPass;
	}

	public SoapObject getPassangersOnTrip(String ridTrip) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getPassangersOnTripElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:tripIdIn", ridTrip);
		passangersOnTrip = request;
		return passangersOnTrip;
	}

	public void setPassangersOnTrip(SoapObject passangersOnTrip) {
		this.passangersOnTrip = passangersOnTrip;
	}

	public SoapObject getPPR() {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getPprElement");
		PPR = request;
		return PPR;
	}

	public void setPPR(SoapObject pPR) {
		PPR = pPR;
	}

	public SoapObject getCheckPass(String citi, String docnum, String tripId) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"checkPassDataElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

		request.addProperty("n0:tripIdIn", tripId);
		request.addProperty("n0:citiIn", citi);
		request.addProperty("n0:paspNumIn", docnum);
		request.addProperty("n0:onlyViol",
				(ms.getTripSelected() == null) ? "true" : "false");
		checkPass = request;
		return checkPass;
	}

	public void setCheckPass(SoapObject checkPass) {
		this.checkPass = checkPass;
	}

	public SoapObject getGetDocSetDataElement(String didIn) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getDocSetDataElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		request.addProperty("n0:didIn", didIn);
		getDocSetDataElement = request;
		
		return getDocSetDataElement;
	}

	public void setGetDocSetDataElement(SoapObject getDocSetDataElement) {
		this.getDocSetDataElement = getDocSetDataElement;
	}

	public SoapObject getGetVisesElement(String didIn) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getKlassifElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		request.addProperty("n0:typeIn", "1");
		request.addProperty("n0:codeIn", "1");
		request.addProperty("n0:orderIn", "1");
		
		getVisesElement = request;
		
		return getVisesElement;
	}

	public void setGetVisesElement(SoapObject getVisesElement) {
		this.getVisesElement = getVisesElement;
	}

	public SoapObject getGetPassangersElement(String pidIn) {
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getPassangersElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		request.addProperty("n0:pidIn", pidIn);
		getPassangersElement = request;
		
		return getPassangersElement;
	}

	public void setGetPassangersElement(SoapObject getPassangersElement) {
		this.getPassangersElement = getPassangersElement;
	}

	public SoapObject getRegTrip(String cSysTripTypeIn, String transpNumIn, String trailerNumIn,
			String cTripClassIn, String cTripTypeIn, String modelIn, String uklNumIn,
			String citiIn, String ownerIn, String amountPassangerIn, String cDerectionIn, String idUserIn,
			String tripNumIn, String tripDateIn, String sourceIn, String destinationIn, String amountAllCarIn, String amountForCarIn, String searchRidIn,
			String updateIn, String tripIdIn, String cTripStateIn/*, SoapObject cargoData*/) {
		
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"regTripElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());

		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);

        //searchRidIn = "1813841711240000163";
        cTripClassIn = ms.getClassAuto()[0][Integer.parseInt(cTripClassIn)];

		request.addProperty("n0:cSysTripTypeIn", cSysTripTypeIn);
		request.addProperty("n0:transpNumIn", transpNumIn);
		request.addProperty("n0:trailerNumIn", trailerNumIn);
		request.addProperty("n0:cTripClassIn", cTripClassIn);
		request.addProperty("n0:cTripTypeIn", cTripTypeIn);
		request.addProperty("n0:modelIn", modelIn);
		request.addProperty("n0:uklNumIn", uklNumIn);
		request.addProperty("n0:citiIn", citiIn);
		request.addProperty("n0:ownerIn", ownerIn);
		request.addProperty("n0:amountPassangerIn", amountPassangerIn);
		request.addProperty("n0:cDerectionIn", cDerectionIn);
		request.addProperty("n0:idUserIn", idUserIn);
		request.addProperty("n0:tripNumIn", tripNumIn);

//		request.addProperty("n0:tripDateIn xmlns:ns3=\"http://www.w3.org/2001/XMLSchema-instance\" ns3:nil=\"true\"\",\n" +
//                "\t\t\t\t",null);

        request.addProperty(
                "n0:tripDateIn xmlns:ns4=\"http://www.w3.org/2001/XMLSchema-instance\" ns4:nil=\"true\"",
                null);
//        request.addProperty("n0:tripDateIn",null);

		request.addProperty("n0:sourceIn", sourceIn);
		request.addProperty("n0:destinationIn", destinationIn);
		request.addProperty("n0:amountAllCarIn", "0");
		request.addProperty("n0:amountForCarIn", "0");
		request.addProperty("n0:searchRidIn", searchRidIn);
		request.addProperty("n0:updateIn", updateIn);


       /* if (cargoData.getPropertyCount()>0) {
        SoapObject cargoIn = new SoapObject(SoapConstants.NAMESPACE, "cargoIn");

            for(int i=0;i<cargoData.getPropertyCount();i++) {
                SoapObject item = (SoapObject) cargoData.getProperty(i);
                cargoIn.addProperty("n0:stampNum",item.getProperty("stampNum"));
                cargoIn.addProperty("n0:amount", item.getProperty("amount"));
                cargoIn.addProperty("n0:CUnits", item.getProperty("CUnits"));
                cargoIn.addProperty("n0:cargoName", item.getProperty("cargoName"));
                cargoIn.addProperty("n0:rid", item.getProperty("rid"));
                cargoIn.addProperty("n0:CUnitsStr", item.getProperty("CUnitsStr"));
            }

                request.addSoapObject(cargoIn);

        }*/



        request.addProperty("n0:tripIdIn", tripIdIn);
        request.addProperty("n0:cTripStateIn", cTripStateIn);

       /* SoapObject delListIn = new SoapObject(SoapConstants.NAMESPACE, "delListIn");
        request.addSoapObject(delListIn);
        delListIn.addProperty("n0:idCargo",null);


        SoapObject delDiscoverListIn = new SoapObject(SoapConstants.NAMESPACE, "delDiscoverListIn");
        request.addSoapObject(delDiscoverListIn);
        delDiscoverListIn.addProperty("n0:idCargo",null);

        SoapObject delMeasureListIn = new SoapObject(SoapConstants.NAMESPACE, "delMeasureListIn");
        request.addSoapObject(delMeasureListIn);
        delMeasureListIn.addProperty("n0:idCargo",null);

        SoapObject tripViolDiscoverIn = new SoapObject(SoapConstants.NAMESPACE, "tripViolDiscoverIn");
        request.addSoapObject(tripViolDiscoverIn);
        tripViolDiscoverIn.addProperty("n0:CDecision",null);
        tripViolDiscoverIn.addProperty("n0:addInfo",null);
        tripViolDiscoverIn.addProperty("n0:CContentsStr",null);
        tripViolDiscoverIn.addProperty("n0:COrganStr",null);
        tripViolDiscoverIn.addProperty("n0:tripId",null);
        tripViolDiscoverIn.addProperty("n0:CViolType",null);
        tripViolDiscoverIn.addProperty("n0:COrgan",null);
        tripViolDiscoverIn.addProperty("n0:personDecision",null);
        tripViolDiscoverIn.addProperty("n0:CViolTypeStr",null);
        tripViolDiscoverIn.addProperty("n0:rid",null);
        tripViolDiscoverIn.addProperty("n0:CDecisionStr",null);
        tripViolDiscoverIn.addProperty("n0:CContents",null);

        SoapObject tripViolMeasuresIn = new SoapObject(SoapConstants.NAMESPACE, "tripViolMeasuresIn");
        request.addSoapObject(tripViolMeasuresIn);

        tripViolMeasuresIn.addProperty("n0:tripsViolDRid",null);
        tripViolMeasuresIn.addProperty("n0:CMeasuresStr",null);
        tripViolMeasuresIn.addProperty("n0:CMeasures",null);
        tripViolMeasuresIn.addProperty("n0:rid",null);*/

        regTrip = request;
		return regTrip;
		
	}
	
	public SoapObject getCheckAutoLst(String transpNumIn,String trailerNumIn, String idUserIn, String tripIdIn, String cDirectionIn) {
		
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"checkAutoLstElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");		
		request.addSoapObject(request2);
		
		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		
		
		request.addProperty("n0:transpNumIn", transpNumIn);
		request.addProperty("n0:trailerNumIn", trailerNumIn);
		request.addProperty("n0:idUserIn", idUserIn);
		request.addProperty("n0:tripIdIn", tripIdIn);
		request.addProperty("n0:cDirectionIn", cDirectionIn);
		
		getCheckAutoLst = request;
		return getCheckAutoLst;
	}
	
	public void setRegTrip(SoapObject regTrip) {
		this.regTrip = regTrip;
	}
	
	public SoapObject checkTrip(String transpNum, String trailerNum) {
		
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE,
				"checkTripElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE, "authIn");		
		request.addSoapObject(request2);
		
		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		request.addProperty("n0:transpNumIn", transpNum);
		request.addProperty("n0:trailerNumIn", trailerNum);
		
		return request;
		
	}

	public SoapObject getCargo(String tripId) {
	
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getCargoDataElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		request.addProperty("n0:tripIdIn", tripId);
		
		cargo = request;
		
		return cargo;
	}

	public void setCargo(SoapObject cargo) {
		this.cargo = cargo;
	}

	public SoapObject getTranspDataElement(String transpNumIn, String updateTripIn) {
		
		SoapObject request = new SoapObject(SoapConstants.NAMESPACE2,
				"getTranspDataElement");

		SoapObject request2 = new SoapObject(SoapConstants.NAMESPACE2, "authIn");
		request.addSoapObject(request2);

		PropertyInfo pa = new PropertyInfo();
		pa.setName("n0:username");
		pa.setValue(ms.getName());

		PropertyInfo pb = new PropertyInfo();
		pb.setName("n0:userpwd");
		pb.setValue(ms.getPass());

		PropertyInfo pc = new PropertyInfo();
		pc.setName("n0:CSysOrgan");
		pc.setValue(ms.getKPP());
		
		request2.addProperty(pa);
		request2.addProperty(pb);
		request2.addProperty(pc);
		
		request.addProperty("n0:transpNumIn", transpNumIn);
		request.addProperty("n0:updateTripIn", updateTripIn);

		transpDataElement = request;		
		return transpDataElement;
	}

	public void setTranspDataElement(SoapObject transpDataElement) {
		this.transpDataElement = transpDataElement;
	}

    public  SoapObject setAuthIn (SoapObject obj) {

        PropertyInfo pa = new PropertyInfo();
        pa.setName("n0:username");
        pa.setValue(ms.getName());

        PropertyInfo pb = new PropertyInfo();
        pb.setName("n0:userpwd");
        pb.setValue(ms.getPass());

        PropertyInfo pc = new PropertyInfo();
        pc.setName("n0:CSysOrgan");
        pc.setValue(ms.getKPP());

        obj.addProperty(pa);
        obj.addProperty(pb);
        obj.addProperty(pc);

        return obj;
    }
	
	
}
