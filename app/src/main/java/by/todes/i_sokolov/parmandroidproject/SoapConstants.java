package by.todes.i_sokolov.parmandroidproject;

public class SoapConstants {
	private static SoapConstants instance;
	private String URL;
	private String URL2;
	public static final String SOAP_ACTION = "";
	public static final String NAMESPACE = "http://borderbel/PArmControl.wsdl/types/";
	public static final String NAMESPACE2 = "http://borderbel/PArmCommon.wsdl";

	
	public static SoapConstants getInstance() {
		if (instance == null) {
			instance = new SoapConstants();
		}
		return instance;
	}
	public static void setInstance(SoapConstants instance) {
		SoapConstants.instance = instance;
	}

    public String getControlURL() {
		return URL;
	}

    public void setControlURL(String value) {
		URL = "http://"+ value +":8888/BorderWebService-PArmProject-context-root/PArmControlSoapHttpPort";
	}
	public String getCommonURL() {
		return URL2;
	}

    public void setCommonURL(String value) {
	   URL2 = "http://"+ value +":8888/BorderWebService-PArmProject-context-root/PArmCommonSoapHttpPort";
	}
	//172.22.5.18

}
