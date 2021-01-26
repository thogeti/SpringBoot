package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ForwardRx", propOrder = {
    "prescriptionNumber",
    "fillStatusCode"
})
public class ForwardRx {
	 protected Integer prescriptionNumber;
	 @XmlSchemaType(name = "string")
	 protected RxFillStatus fillStatusCode;
	 
	 
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
