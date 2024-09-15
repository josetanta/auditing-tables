package edu.systemia.auditing_entities.infrastructure.soap;

import edu.systemia.auditing_entities.infrastructure.wsdl.Add;
import edu.systemia.auditing_entities.infrastructure.wsdl.AddResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapClient extends WebServiceGatewaySupport {

	public AddResponse getAddResponse(int a, int b) {
		Add addRequest = new Add();
		addRequest.setIntA(a);
		addRequest.setIntB(b);

		var soapActionCallback = new SoapActionCallback("http://tempuri.org/Add");
		return (AddResponse) getWebServiceTemplate().marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", addRequest, soapActionCallback);
	}
}
