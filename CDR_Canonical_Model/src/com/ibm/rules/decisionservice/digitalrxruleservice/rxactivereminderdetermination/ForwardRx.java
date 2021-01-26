package com.ibm.rules.decisionservice.digitalrxruleservice.rxactivereminderdetermination;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ForwardRx", propOrder = {
    "fillStatusCode",
    "prescriptionNumber"
})
public class ForwardRx {
	 @XmlSchemaType(name = "string")
	 protected RxFillStatus fillStatusCode;
	 protected Integer prescriptionNumber;
	
	 
	 
	public Integer getPrescriptionNumber() {
		return prescriptionNumber;
	}
	public void setPrescriptionNumber(Integer prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}
	public RxFillStatus getFillStatusCode() {
		return fillStatusCode;
	}
	public void setFillStatusCode(RxFillStatus fillStatusCode) {
		this.fillStatusCode = fillStatusCode;
	}
}
