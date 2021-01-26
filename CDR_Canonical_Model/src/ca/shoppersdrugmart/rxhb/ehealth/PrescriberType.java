
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriberType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrescriberType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ON Dentist"/&gt;
 *     &lt;enumeration value="ON Medical Resident"/&gt;
 *     &lt;enumeration value="ON Midwife"/&gt;
 *     &lt;enumeration value="ON Optometrist"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: AB Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: BC Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: MB Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: NB Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: NF Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: NS Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: NT Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: PE Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: QC Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: SK Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Out-Of-Prov: YT Licensed Prescriber"/&gt;
 *     &lt;enumeration value="ON Pharmacist"/&gt;
 *     &lt;enumeration value="ON Physician"/&gt;
 *     &lt;enumeration value="ON Podiatrist"/&gt;
 *     &lt;enumeration value="ON Registered Nurse"/&gt;
 *     &lt;enumeration value="ON Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="ON Unknown Prescriber Type"/&gt;
 *     &lt;enumeration value="ON Veterinarian"/&gt;
 *     &lt;enumeration value="PE Audiologists"/&gt;
 *     &lt;enumeration value="PE Dentist"/&gt;
 *     &lt;enumeration value="PE Medical Resident"/&gt;
 *     &lt;enumeration value="PE Nurse Practitioner"/&gt;
 *     &lt;enumeration value="PE Optometrist"/&gt;
 *     &lt;enumeration value="PE Pharmacist"/&gt;
 *     &lt;enumeration value="PE Physician"/&gt;
 *     &lt;enumeration value="PE Podiatrist"/&gt;
 *     &lt;enumeration value="PE Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="PE Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="PE Veterinarian"/&gt;
 *     &lt;enumeration value="QC Audiologists"/&gt;
 *     &lt;enumeration value="QC Dentist"/&gt;
 *     &lt;enumeration value="QC Medical Resident"/&gt;
 *     &lt;enumeration value="QC Midwife"/&gt;
 *     &lt;enumeration value="QC Optometrist"/&gt;
 *     &lt;enumeration value="QC Out-Of-Province Dentist Registered With Ramq"/&gt;
 *     &lt;enumeration value="QC Out-Of-Province Physician Registered With Ramq"/&gt;
 *     &lt;enumeration value="QC Pharmacist"/&gt;
 *     &lt;enumeration value="QC Physician"/&gt;
 *     &lt;enumeration value="QC Podiatrist"/&gt;
 *     &lt;enumeration value="QC Registered Nurse"/&gt;
 *     &lt;enumeration value="QC Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="QC Veterinarian"/&gt;
 *     &lt;enumeration value="SK Audiologists"/&gt;
 *     &lt;enumeration value="SK Dentist"/&gt;
 *     &lt;enumeration value="SK Locum/ Out-Of-Province Dentist With SK Nbr"/&gt;
 *     &lt;enumeration value="SK Locum/Out-Of-Province Physician With SK Nbr"/&gt;
 *     &lt;enumeration value="SK Medical Resident"/&gt;
 *     &lt;enumeration value="SK Midwife"/&gt;
 *     &lt;enumeration value="SK Optometrist"/&gt;
 *     &lt;enumeration value="SK Pharmacist"/&gt;
 *     &lt;enumeration value="SK Physician"/&gt;
 *     &lt;enumeration value="SK Podiatrist"/&gt;
 *     &lt;enumeration value="SK Registered Nurse"/&gt;
 *     &lt;enumeration value="SK Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="SK Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="SK Veterinarian"/&gt;
 *     &lt;enumeration value="YT Audiologists"/&gt;
 *     &lt;enumeration value="YT Dentist"/&gt;
 *     &lt;enumeration value="YT Locum/ Out-Of-Province Dentist With YT Nbr"/&gt;
 *     &lt;enumeration value="YT Locum/Out-Of-Province Physician With YT Nbr"/&gt;
 *     &lt;enumeration value="YT Medical Resident"/&gt;
 *     &lt;enumeration value="YT Optometrist"/&gt;
 *     &lt;enumeration value="YT Pharmacist"/&gt;
 *     &lt;enumeration value="YT Physician"/&gt;
 *     &lt;enumeration value="YT Podiatrist"/&gt;
 *     &lt;enumeration value="YT Registered Nurse"/&gt;
 *     &lt;enumeration value="YT Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="YT Veterinarian"/&gt;
 *     &lt;enumeration value="YT Yukon Consumer Services - Dentists"/&gt;
 *     &lt;enumeration value="YT Yukon Consumer Services - Optometrists"/&gt;
 *     &lt;enumeration value="YT Yukon Consumer Services - Pharmacists"/&gt;
 *     &lt;enumeration value="AB Audiologists"/&gt;
 *     &lt;enumeration value="AB Dental Hygienist"/&gt;
 *     &lt;enumeration value="AB Dentist"/&gt;
 *     &lt;enumeration value="AB Dietician"/&gt;
 *     &lt;enumeration value="AB Locum/ Out-Of-Province Dentist With AB Nbr"/&gt;
 *     &lt;enumeration value="AB Locum/Out-Of-Province Physician With AB Nbr"/&gt;
 *     &lt;enumeration value="AB Medical Resident"/&gt;
 *     &lt;enumeration value="AB Midwife"/&gt;
 *     &lt;enumeration value="AB Optometrist"/&gt;
 *     &lt;enumeration value="AB Pharmacist"/&gt;
 *     &lt;enumeration value="AB Physician"/&gt;
 *     &lt;enumeration value="AB Podiatrist"/&gt;
 *     &lt;enumeration value="AB Registered Nurse"/&gt;
 *     &lt;enumeration value="AB Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="AB Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="AB Veterinarian"/&gt;
 *     &lt;enumeration value="BC Dentist"/&gt;
 *     &lt;enumeration value="BC Locum/ Out-Of-Province Dentist With BC Nbr"/&gt;
 *     &lt;enumeration value="BC Locum/Out-Of-Province Physician With BC Nbr"/&gt;
 *     &lt;enumeration value="BC Medical Resident"/&gt;
 *     &lt;enumeration value="BC Midwife"/&gt;
 *     &lt;enumeration value="BC Naturopathic Physician"/&gt;
 *     &lt;enumeration value="BC Optometrist"/&gt;
 *     &lt;enumeration value="BC Pharmacist"/&gt;
 *     &lt;enumeration value="BC Physician"/&gt;
 *     &lt;enumeration value="BC Podiatrist"/&gt;
 *     &lt;enumeration value="BC Registered Nurse"/&gt;
 *     &lt;enumeration value="BC Veterinarian"/&gt;
 *     &lt;enumeration value="MB Dentist"/&gt;
 *     &lt;enumeration value="MB Locum/ Out-Of-Province Dentist With MB Nbr"/&gt;
 *     &lt;enumeration value="MB Locum/Out-Of-Province Physician With MB Nbr"/&gt;
 *     &lt;enumeration value="MB Medical Resident"/&gt;
 *     &lt;enumeration value="MB Midwife"/&gt;
 *     &lt;enumeration value="MB Optometrist"/&gt;
 *     &lt;enumeration value="MB Pharmacist"/&gt;
 *     &lt;enumeration value="MB Physician"/&gt;
 *     &lt;enumeration value="MB Podiatrist"/&gt;
 *     &lt;enumeration value="MB Registered Nurse"/&gt;
 *     &lt;enumeration value="MB Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="MB Veterinarian"/&gt;
 *     &lt;enumeration value="N/A NOT APPLICABLE"/&gt;
 *     &lt;enumeration value="NB Audiologists"/&gt;
 *     &lt;enumeration value="NB Dentist"/&gt;
 *     &lt;enumeration value="NB Locum/ Out-Of-Province Dentist With NB Nbr"/&gt;
 *     &lt;enumeration value="NB Locum/Out-Of-Province Physician With NB Nbr"/&gt;
 *     &lt;enumeration value="NB Medical Resident"/&gt;
 *     &lt;enumeration value="NB NATUROPATHIC PHYSICIAN"/&gt;
 *     &lt;enumeration value="NB Nurse Practitioner"/&gt;
 *     &lt;enumeration value="NB Optometrist"/&gt;
 *     &lt;enumeration value="NB Pharmacist"/&gt;
 *     &lt;enumeration value="NB Physician"/&gt;
 *     &lt;enumeration value="NB Podiatrist"/&gt;
 *     &lt;enumeration value="NB Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="NB Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="NB Veterinarian"/&gt;
 *     &lt;enumeration value="NL Audiologists"/&gt;
 *     &lt;enumeration value="NL Dentist"/&gt;
 *     &lt;enumeration value="NL Locum/ Out-Of-Province Dentist With NF Nbr"/&gt;
 *     &lt;enumeration value="NL Locum/Out-Of-Province Physician With NF Nbr"/&gt;
 *     &lt;enumeration value="NL Medical Resident"/&gt;
 *     &lt;enumeration value="NL Nurse Practitioner"/&gt;
 *     &lt;enumeration value="NL Optometrist"/&gt;
 *     &lt;enumeration value="NL Pharmacist"/&gt;
 *     &lt;enumeration value="NL Physician"/&gt;
 *     &lt;enumeration value="NL Podiatrist"/&gt;
 *     &lt;enumeration value="NL Registered Nurse"/&gt;
 *     &lt;enumeration value="NL Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="NL Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="NL Veterinarian"/&gt;
 *     &lt;enumeration value="NS Audiologists"/&gt;
 *     &lt;enumeration value="NS Dentist"/&gt;
 *     &lt;enumeration value="NS Locum/ Out-Of-Province Dentist With NS Nbr"/&gt;
 *     &lt;enumeration value="NS Locum/Out-Of-Province Physician With NS Nbr"/&gt;
 *     &lt;enumeration value="NS Medical Resident"/&gt;
 *     &lt;enumeration value="NS Midwife"/&gt;
 *     &lt;enumeration value="NS Nurse Practitioner"/&gt;
 *     &lt;enumeration value="NS Optometrist"/&gt;
 *     &lt;enumeration value="NS Pharmacist"/&gt;
 *     &lt;enumeration value="NS Physician"/&gt;
 *     &lt;enumeration value="NS Podiatrist"/&gt;
 *     &lt;enumeration value="NS Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="NS Unknown Prescriber Nbr"/&gt;
 *     &lt;enumeration value="NS Veterinarian"/&gt;
 *     &lt;enumeration value="NT Audiologists"/&gt;
 *     &lt;enumeration value="NT Dentist"/&gt;
 *     &lt;enumeration value="NT Locum/Out-Of-Province Physician With NT Nbr"/&gt;
 *     &lt;enumeration value="NT Medical Resident"/&gt;
 *     &lt;enumeration value="NT Midwife"/&gt;
 *     &lt;enumeration value="NT Optometrist"/&gt;
 *     &lt;enumeration value="NT Pharmacist"/&gt;
 *     &lt;enumeration value="NT Physician"/&gt;
 *     &lt;enumeration value="NT Podiatrist"/&gt;
 *     &lt;enumeration value="NT Registered Nurse"/&gt;
 *     &lt;enumeration value="NT Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="NT Veterinarian"/&gt;
 *     &lt;enumeration value="NU Audiologists"/&gt;
 *     &lt;enumeration value="NU Medical Practitioner"/&gt;
 *     &lt;enumeration value="NU Optometrist"/&gt;
 *     &lt;enumeration value="NU Registered Nurse"/&gt;
 *     &lt;enumeration value="NU Speech Language Pathologists"/&gt;
 *     &lt;enumeration value="ON Audiologists"/&gt;
 *     &lt;enumeration value="ON NATUROPATHIC PHYSICIAN"/&gt;
 *     &lt;enumeration value="MB SPEECH LANGUAGE PATHOLOGISTS"/&gt;
 *     &lt;enumeration value="MB AUDIOLOGIST"/&gt;
 *     &lt;enumeration value="BC AUDIOLOGIST"/&gt;
 *     &lt;enumeration value="BC SPEECH LANGUAGE PATHOLOGISTS"/&gt;
 *     &lt;enumeration value="NB DIETITIAN"/&gt;
 *     &lt;enumeration value="ON OTHER"/&gt;
 *     &lt;enumeration value="NB MIDWIFE"/&gt;
 *     &lt;enumeration value="UNK PRESCRIBER"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PrescriberType")
@XmlEnum
public enum PrescriberType {

    @XmlEnumValue("ON Dentist")
    ON_DENTIST("ON Dentist"),
    @XmlEnumValue("ON Medical Resident")
    ON_MEDICAL_RESIDENT("ON Medical Resident"),
    @XmlEnumValue("ON Midwife")
    ON_MIDWIFE("ON Midwife"),
    @XmlEnumValue("ON Optometrist")
    ON_OPTOMETRIST("ON Optometrist"),
    @XmlEnumValue("ON Out-Of-Prov: AB Licensed Prescriber")
    ON_OUT_OF_PROV_AB_LICENSED_PRESCRIBER("ON Out-Of-Prov: AB Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: BC Licensed Prescriber")
    ON_OUT_OF_PROV_BC_LICENSED_PRESCRIBER("ON Out-Of-Prov: BC Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: MB Licensed Prescriber")
    ON_OUT_OF_PROV_MB_LICENSED_PRESCRIBER("ON Out-Of-Prov: MB Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: NB Licensed Prescriber")
    ON_OUT_OF_PROV_NB_LICENSED_PRESCRIBER("ON Out-Of-Prov: NB Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: NF Licensed Prescriber")
    ON_OUT_OF_PROV_NF_LICENSED_PRESCRIBER("ON Out-Of-Prov: NF Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: NS Licensed Prescriber")
    ON_OUT_OF_PROV_NS_LICENSED_PRESCRIBER("ON Out-Of-Prov: NS Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: NT Licensed Prescriber")
    ON_OUT_OF_PROV_NT_LICENSED_PRESCRIBER("ON Out-Of-Prov: NT Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: PE Licensed Prescriber")
    ON_OUT_OF_PROV_PE_LICENSED_PRESCRIBER("ON Out-Of-Prov: PE Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: QC Licensed Prescriber")
    ON_OUT_OF_PROV_QC_LICENSED_PRESCRIBER("ON Out-Of-Prov: QC Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: SK Licensed Prescriber")
    ON_OUT_OF_PROV_SK_LICENSED_PRESCRIBER("ON Out-Of-Prov: SK Licensed Prescriber"),
    @XmlEnumValue("ON Out-Of-Prov: YT Licensed Prescriber")
    ON_OUT_OF_PROV_YT_LICENSED_PRESCRIBER("ON Out-Of-Prov: YT Licensed Prescriber"),
    @XmlEnumValue("ON Pharmacist")
    ON_PHARMACIST("ON Pharmacist"),
    @XmlEnumValue("ON Physician")
    ON_PHYSICIAN("ON Physician"),
    @XmlEnumValue("ON Podiatrist")
    ON_PODIATRIST("ON Podiatrist"),
    @XmlEnumValue("ON Registered Nurse")
    ON_REGISTERED_NURSE("ON Registered Nurse"),
    @XmlEnumValue("ON Speech Language Pathologists")
    ON_SPEECH_LANGUAGE_PATHOLOGISTS("ON Speech Language Pathologists"),
    @XmlEnumValue("ON Unknown Prescriber Type")
    ON_UNKNOWN_PRESCRIBER_TYPE("ON Unknown Prescriber Type"),
    @XmlEnumValue("ON Veterinarian")
    ON_VETERINARIAN("ON Veterinarian"),
    @XmlEnumValue("PE Audiologists")
    PE_AUDIOLOGISTS("PE Audiologists"),
    @XmlEnumValue("PE Dentist")
    PE_DENTIST("PE Dentist"),
    @XmlEnumValue("PE Medical Resident")
    PE_MEDICAL_RESIDENT("PE Medical Resident"),
    @XmlEnumValue("PE Nurse Practitioner")
    PE_NURSE_PRACTITIONER("PE Nurse Practitioner"),
    @XmlEnumValue("PE Optometrist")
    PE_OPTOMETRIST("PE Optometrist"),
    @XmlEnumValue("PE Pharmacist")
    PE_PHARMACIST("PE Pharmacist"),
    @XmlEnumValue("PE Physician")
    PE_PHYSICIAN("PE Physician"),
    @XmlEnumValue("PE Podiatrist")
    PE_PODIATRIST("PE Podiatrist"),
    @XmlEnumValue("PE Speech Language Pathologists")
    PE_SPEECH_LANGUAGE_PATHOLOGISTS("PE Speech Language Pathologists"),
    @XmlEnumValue("PE Unknown Prescriber Nbr")
    PE_UNKNOWN_PRESCRIBER_NBR("PE Unknown Prescriber Nbr"),
    @XmlEnumValue("PE Veterinarian")
    PE_VETERINARIAN("PE Veterinarian"),
    @XmlEnumValue("QC Audiologists")
    QC_AUDIOLOGISTS("QC Audiologists"),
    @XmlEnumValue("QC Dentist")
    QC_DENTIST("QC Dentist"),
    @XmlEnumValue("QC Medical Resident")
    QC_MEDICAL_RESIDENT("QC Medical Resident"),
    @XmlEnumValue("QC Midwife")
    QC_MIDWIFE("QC Midwife"),
    @XmlEnumValue("QC Optometrist")
    QC_OPTOMETRIST("QC Optometrist"),
    @XmlEnumValue("QC Out-Of-Province Dentist Registered With Ramq")
    QC_OUT_OF_PROVINCE_DENTIST_REGISTERED_WITH_RAMQ("QC Out-Of-Province Dentist Registered With Ramq"),
    @XmlEnumValue("QC Out-Of-Province Physician Registered With Ramq")
    QC_OUT_OF_PROVINCE_PHYSICIAN_REGISTERED_WITH_RAMQ("QC Out-Of-Province Physician Registered With Ramq"),
    @XmlEnumValue("QC Pharmacist")
    QC_PHARMACIST("QC Pharmacist"),
    @XmlEnumValue("QC Physician")
    QC_PHYSICIAN("QC Physician"),
    @XmlEnumValue("QC Podiatrist")
    QC_PODIATRIST("QC Podiatrist"),
    @XmlEnumValue("QC Registered Nurse")
    QC_REGISTERED_NURSE("QC Registered Nurse"),
    @XmlEnumValue("QC Speech Language Pathologists")
    QC_SPEECH_LANGUAGE_PATHOLOGISTS("QC Speech Language Pathologists"),
    @XmlEnumValue("QC Veterinarian")
    QC_VETERINARIAN("QC Veterinarian"),
    @XmlEnumValue("SK Audiologists")
    SK_AUDIOLOGISTS("SK Audiologists"),
    @XmlEnumValue("SK Dentist")
    SK_DENTIST("SK Dentist"),
    @XmlEnumValue("SK Locum/ Out-Of-Province Dentist With SK Nbr")
    SK_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_SK_NBR("SK Locum/ Out-Of-Province Dentist With SK Nbr"),
    @XmlEnumValue("SK Locum/Out-Of-Province Physician With SK Nbr")
    SK_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_SK_NBR("SK Locum/Out-Of-Province Physician With SK Nbr"),
    @XmlEnumValue("SK Medical Resident")
    SK_MEDICAL_RESIDENT("SK Medical Resident"),
    @XmlEnumValue("SK Midwife")
    SK_MIDWIFE("SK Midwife"),
    @XmlEnumValue("SK Optometrist")
    SK_OPTOMETRIST("SK Optometrist"),
    @XmlEnumValue("SK Pharmacist")
    SK_PHARMACIST("SK Pharmacist"),
    @XmlEnumValue("SK Physician")
    SK_PHYSICIAN("SK Physician"),
    @XmlEnumValue("SK Podiatrist")
    SK_PODIATRIST("SK Podiatrist"),
    @XmlEnumValue("SK Registered Nurse")
    SK_REGISTERED_NURSE("SK Registered Nurse"),
    @XmlEnumValue("SK Speech Language Pathologists")
    SK_SPEECH_LANGUAGE_PATHOLOGISTS("SK Speech Language Pathologists"),
    @XmlEnumValue("SK Unknown Prescriber Nbr")
    SK_UNKNOWN_PRESCRIBER_NBR("SK Unknown Prescriber Nbr"),
    @XmlEnumValue("SK Veterinarian")
    SK_VETERINARIAN("SK Veterinarian"),
    @XmlEnumValue("YT Audiologists")
    YT_AUDIOLOGISTS("YT Audiologists"),
    @XmlEnumValue("YT Dentist")
    YT_DENTIST("YT Dentist"),
    @XmlEnumValue("YT Locum/ Out-Of-Province Dentist With YT Nbr")
    YT_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_YT_NBR("YT Locum/ Out-Of-Province Dentist With YT Nbr"),
    @XmlEnumValue("YT Locum/Out-Of-Province Physician With YT Nbr")
    YT_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_YT_NBR("YT Locum/Out-Of-Province Physician With YT Nbr"),
    @XmlEnumValue("YT Medical Resident")
    YT_MEDICAL_RESIDENT("YT Medical Resident"),
    @XmlEnumValue("YT Optometrist")
    YT_OPTOMETRIST("YT Optometrist"),
    @XmlEnumValue("YT Pharmacist")
    YT_PHARMACIST("YT Pharmacist"),
    @XmlEnumValue("YT Physician")
    YT_PHYSICIAN("YT Physician"),
    @XmlEnumValue("YT Podiatrist")
    YT_PODIATRIST("YT Podiatrist"),
    @XmlEnumValue("YT Registered Nurse")
    YT_REGISTERED_NURSE("YT Registered Nurse"),
    @XmlEnumValue("YT Speech Language Pathologists")
    YT_SPEECH_LANGUAGE_PATHOLOGISTS("YT Speech Language Pathologists"),
    @XmlEnumValue("YT Veterinarian")
    YT_VETERINARIAN("YT Veterinarian"),
    @XmlEnumValue("YT Yukon Consumer Services - Dentists")
    YT_YUKON_CONSUMER_SERVICES_DENTISTS("YT Yukon Consumer Services - Dentists"),
    @XmlEnumValue("YT Yukon Consumer Services - Optometrists")
    YT_YUKON_CONSUMER_SERVICES_OPTOMETRISTS("YT Yukon Consumer Services - Optometrists"),
    @XmlEnumValue("YT Yukon Consumer Services - Pharmacists")
    YT_YUKON_CONSUMER_SERVICES_PHARMACISTS("YT Yukon Consumer Services - Pharmacists"),
    @XmlEnumValue("AB Audiologists")
    AB_AUDIOLOGISTS("AB Audiologists"),
    @XmlEnumValue("AB Dental Hygienist")
    AB_DENTAL_HYGIENIST("AB Dental Hygienist"),
    @XmlEnumValue("AB Dentist")
    AB_DENTIST("AB Dentist"),
    @XmlEnumValue("AB Dietician")
    AB_DIETICIAN("AB Dietician"),
    @XmlEnumValue("AB Locum/ Out-Of-Province Dentist With AB Nbr")
    AB_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_AB_NBR("AB Locum/ Out-Of-Province Dentist With AB Nbr"),
    @XmlEnumValue("AB Locum/Out-Of-Province Physician With AB Nbr")
    AB_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_AB_NBR("AB Locum/Out-Of-Province Physician With AB Nbr"),
    @XmlEnumValue("AB Medical Resident")
    AB_MEDICAL_RESIDENT("AB Medical Resident"),
    @XmlEnumValue("AB Midwife")
    AB_MIDWIFE("AB Midwife"),
    @XmlEnumValue("AB Optometrist")
    AB_OPTOMETRIST("AB Optometrist"),
    @XmlEnumValue("AB Pharmacist")
    AB_PHARMACIST("AB Pharmacist"),
    @XmlEnumValue("AB Physician")
    AB_PHYSICIAN("AB Physician"),
    @XmlEnumValue("AB Podiatrist")
    AB_PODIATRIST("AB Podiatrist"),
    @XmlEnumValue("AB Registered Nurse")
    AB_REGISTERED_NURSE("AB Registered Nurse"),
    @XmlEnumValue("AB Speech Language Pathologists")
    AB_SPEECH_LANGUAGE_PATHOLOGISTS("AB Speech Language Pathologists"),
    @XmlEnumValue("AB Unknown Prescriber Nbr")
    AB_UNKNOWN_PRESCRIBER_NBR("AB Unknown Prescriber Nbr"),
    @XmlEnumValue("AB Veterinarian")
    AB_VETERINARIAN("AB Veterinarian"),
    @XmlEnumValue("BC Dentist")
    BC_DENTIST("BC Dentist"),
    @XmlEnumValue("BC Locum/ Out-Of-Province Dentist With BC Nbr")
    BC_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_BC_NBR("BC Locum/ Out-Of-Province Dentist With BC Nbr"),
    @XmlEnumValue("BC Locum/Out-Of-Province Physician With BC Nbr")
    BC_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_BC_NBR("BC Locum/Out-Of-Province Physician With BC Nbr"),
    @XmlEnumValue("BC Medical Resident")
    BC_MEDICAL_RESIDENT("BC Medical Resident"),
    @XmlEnumValue("BC Midwife")
    BC_MIDWIFE("BC Midwife"),
    @XmlEnumValue("BC Naturopathic Physician")
    BC_NATUROPATHIC_PHYSICIAN("BC Naturopathic Physician"),
    @XmlEnumValue("BC Optometrist")
    BC_OPTOMETRIST("BC Optometrist"),
    @XmlEnumValue("BC Pharmacist")
    BC_PHARMACIST("BC Pharmacist"),
    @XmlEnumValue("BC Physician")
    BC_PHYSICIAN("BC Physician"),
    @XmlEnumValue("BC Podiatrist")
    BC_PODIATRIST("BC Podiatrist"),
    @XmlEnumValue("BC Registered Nurse")
    BC_REGISTERED_NURSE("BC Registered Nurse"),
    @XmlEnumValue("BC Veterinarian")
    BC_VETERINARIAN("BC Veterinarian"),
    @XmlEnumValue("MB Dentist")
    MB_DENTIST("MB Dentist"),
    @XmlEnumValue("MB Locum/ Out-Of-Province Dentist With MB Nbr")
    MB_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_MB_NBR("MB Locum/ Out-Of-Province Dentist With MB Nbr"),
    @XmlEnumValue("MB Locum/Out-Of-Province Physician With MB Nbr")
    MB_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_MB_NBR("MB Locum/Out-Of-Province Physician With MB Nbr"),
    @XmlEnumValue("MB Medical Resident")
    MB_MEDICAL_RESIDENT("MB Medical Resident"),
    @XmlEnumValue("MB Midwife")
    MB_MIDWIFE("MB Midwife"),
    @XmlEnumValue("MB Optometrist")
    MB_OPTOMETRIST("MB Optometrist"),
    @XmlEnumValue("MB Pharmacist")
    MB_PHARMACIST("MB Pharmacist"),
    @XmlEnumValue("MB Physician")
    MB_PHYSICIAN("MB Physician"),
    @XmlEnumValue("MB Podiatrist")
    MB_PODIATRIST("MB Podiatrist"),
    @XmlEnumValue("MB Registered Nurse")
    MB_REGISTERED_NURSE("MB Registered Nurse"),
    @XmlEnumValue("MB Unknown Prescriber Nbr")
    MB_UNKNOWN_PRESCRIBER_NBR("MB Unknown Prescriber Nbr"),
    @XmlEnumValue("MB Veterinarian")
    MB_VETERINARIAN("MB Veterinarian"),
    @XmlEnumValue("N/A NOT APPLICABLE")
    N_A_NOT_APPLICABLE("N/A NOT APPLICABLE"),
    @XmlEnumValue("NB Audiologists")
    NB_AUDIOLOGISTS("NB Audiologists"),
    @XmlEnumValue("NB Dentist")
    NB_DENTIST("NB Dentist"),
    @XmlEnumValue("NB Locum/ Out-Of-Province Dentist With NB Nbr")
    NB_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_NB_NBR("NB Locum/ Out-Of-Province Dentist With NB Nbr"),
    @XmlEnumValue("NB Locum/Out-Of-Province Physician With NB Nbr")
    NB_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_NB_NBR("NB Locum/Out-Of-Province Physician With NB Nbr"),
    @XmlEnumValue("NB Medical Resident")
    NB_MEDICAL_RESIDENT("NB Medical Resident"),
    @XmlEnumValue("NB NATUROPATHIC PHYSICIAN")
    NB_NATUROPATHIC_PHYSICIAN("NB NATUROPATHIC PHYSICIAN"),
    @XmlEnumValue("NB Nurse Practitioner")
    NB_NURSE_PRACTITIONER("NB Nurse Practitioner"),
    @XmlEnumValue("NB Optometrist")
    NB_OPTOMETRIST("NB Optometrist"),
    @XmlEnumValue("NB Pharmacist")
    NB_PHARMACIST("NB Pharmacist"),
    @XmlEnumValue("NB Physician")
    NB_PHYSICIAN("NB Physician"),
    @XmlEnumValue("NB Podiatrist")
    NB_PODIATRIST("NB Podiatrist"),
    @XmlEnumValue("NB Speech Language Pathologists")
    NB_SPEECH_LANGUAGE_PATHOLOGISTS("NB Speech Language Pathologists"),
    @XmlEnumValue("NB Unknown Prescriber Nbr")
    NB_UNKNOWN_PRESCRIBER_NBR("NB Unknown Prescriber Nbr"),
    @XmlEnumValue("NB Veterinarian")
    NB_VETERINARIAN("NB Veterinarian"),
    @XmlEnumValue("NL Audiologists")
    NL_AUDIOLOGISTS("NL Audiologists"),
    @XmlEnumValue("NL Dentist")
    NL_DENTIST("NL Dentist"),
    @XmlEnumValue("NL Locum/ Out-Of-Province Dentist With NF Nbr")
    NL_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_NF_NBR("NL Locum/ Out-Of-Province Dentist With NF Nbr"),
    @XmlEnumValue("NL Locum/Out-Of-Province Physician With NF Nbr")
    NL_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_NF_NBR("NL Locum/Out-Of-Province Physician With NF Nbr"),
    @XmlEnumValue("NL Medical Resident")
    NL_MEDICAL_RESIDENT("NL Medical Resident"),
    @XmlEnumValue("NL Nurse Practitioner")
    NL_NURSE_PRACTITIONER("NL Nurse Practitioner"),
    @XmlEnumValue("NL Optometrist")
    NL_OPTOMETRIST("NL Optometrist"),
    @XmlEnumValue("NL Pharmacist")
    NL_PHARMACIST("NL Pharmacist"),
    @XmlEnumValue("NL Physician")
    NL_PHYSICIAN("NL Physician"),
    @XmlEnumValue("NL Podiatrist")
    NL_PODIATRIST("NL Podiatrist"),
    @XmlEnumValue("NL Registered Nurse")
    NL_REGISTERED_NURSE("NL Registered Nurse"),
    @XmlEnumValue("NL Speech Language Pathologists")
    NL_SPEECH_LANGUAGE_PATHOLOGISTS("NL Speech Language Pathologists"),
    @XmlEnumValue("NL Unknown Prescriber Nbr")
    NL_UNKNOWN_PRESCRIBER_NBR("NL Unknown Prescriber Nbr"),
    @XmlEnumValue("NL Veterinarian")
    NL_VETERINARIAN("NL Veterinarian"),
    @XmlEnumValue("NS Audiologists")
    NS_AUDIOLOGISTS("NS Audiologists"),
    @XmlEnumValue("NS Dentist")
    NS_DENTIST("NS Dentist"),
    @XmlEnumValue("NS Locum/ Out-Of-Province Dentist With NS Nbr")
    NS_LOCUM_OUT_OF_PROVINCE_DENTIST_WITH_NS_NBR("NS Locum/ Out-Of-Province Dentist With NS Nbr"),
    @XmlEnumValue("NS Locum/Out-Of-Province Physician With NS Nbr")
    NS_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_NS_NBR("NS Locum/Out-Of-Province Physician With NS Nbr"),
    @XmlEnumValue("NS Medical Resident")
    NS_MEDICAL_RESIDENT("NS Medical Resident"),
    @XmlEnumValue("NS Midwife")
    NS_MIDWIFE("NS Midwife"),
    @XmlEnumValue("NS Nurse Practitioner")
    NS_NURSE_PRACTITIONER("NS Nurse Practitioner"),
    @XmlEnumValue("NS Optometrist")
    NS_OPTOMETRIST("NS Optometrist"),
    @XmlEnumValue("NS Pharmacist")
    NS_PHARMACIST("NS Pharmacist"),
    @XmlEnumValue("NS Physician")
    NS_PHYSICIAN("NS Physician"),
    @XmlEnumValue("NS Podiatrist")
    NS_PODIATRIST("NS Podiatrist"),
    @XmlEnumValue("NS Speech Language Pathologists")
    NS_SPEECH_LANGUAGE_PATHOLOGISTS("NS Speech Language Pathologists"),
    @XmlEnumValue("NS Unknown Prescriber Nbr")
    NS_UNKNOWN_PRESCRIBER_NBR("NS Unknown Prescriber Nbr"),
    @XmlEnumValue("NS Veterinarian")
    NS_VETERINARIAN("NS Veterinarian"),
    @XmlEnumValue("NT Audiologists")
    NT_AUDIOLOGISTS("NT Audiologists"),
    @XmlEnumValue("NT Dentist")
    NT_DENTIST("NT Dentist"),
    @XmlEnumValue("NT Locum/Out-Of-Province Physician With NT Nbr")
    NT_LOCUM_OUT_OF_PROVINCE_PHYSICIAN_WITH_NT_NBR("NT Locum/Out-Of-Province Physician With NT Nbr"),
    @XmlEnumValue("NT Medical Resident")
    NT_MEDICAL_RESIDENT("NT Medical Resident"),
    @XmlEnumValue("NT Midwife")
    NT_MIDWIFE("NT Midwife"),
    @XmlEnumValue("NT Optometrist")
    NT_OPTOMETRIST("NT Optometrist"),
    @XmlEnumValue("NT Pharmacist")
    NT_PHARMACIST("NT Pharmacist"),
    @XmlEnumValue("NT Physician")
    NT_PHYSICIAN("NT Physician"),
    @XmlEnumValue("NT Podiatrist")
    NT_PODIATRIST("NT Podiatrist"),
    @XmlEnumValue("NT Registered Nurse")
    NT_REGISTERED_NURSE("NT Registered Nurse"),
    @XmlEnumValue("NT Speech Language Pathologists")
    NT_SPEECH_LANGUAGE_PATHOLOGISTS("NT Speech Language Pathologists"),
    @XmlEnumValue("NT Veterinarian")
    NT_VETERINARIAN("NT Veterinarian"),
    @XmlEnumValue("NU Audiologists")
    NU_AUDIOLOGISTS("NU Audiologists"),
    @XmlEnumValue("NU Medical Practitioner")
    NU_MEDICAL_PRACTITIONER("NU Medical Practitioner"),
    @XmlEnumValue("NU Optometrist")
    NU_OPTOMETRIST("NU Optometrist"),
    @XmlEnumValue("NU Registered Nurse")
    NU_REGISTERED_NURSE("NU Registered Nurse"),
    @XmlEnumValue("NU Speech Language Pathologists")
    NU_SPEECH_LANGUAGE_PATHOLOGISTS("NU Speech Language Pathologists"),
    @XmlEnumValue("ON Audiologists")
    ON_AUDIOLOGISTS("ON Audiologists"),
    @XmlEnumValue("ON NATUROPATHIC PHYSICIAN")
    ON_NATUROPATHIC_PHYSICIAN("ON NATUROPATHIC PHYSICIAN"),
    @XmlEnumValue("MB SPEECH LANGUAGE PATHOLOGISTS")
    MB_SPEECH_LANGUAGE_PATHOLOGISTS("MB SPEECH LANGUAGE PATHOLOGISTS"),
    @XmlEnumValue("MB AUDIOLOGIST")
    MB_AUDIOLOGIST("MB AUDIOLOGIST"),
    @XmlEnumValue("BC AUDIOLOGIST")
    BC_AUDIOLOGIST("BC AUDIOLOGIST"),
    @XmlEnumValue("BC SPEECH LANGUAGE PATHOLOGISTS")
    BC_SPEECH_LANGUAGE_PATHOLOGISTS("BC SPEECH LANGUAGE PATHOLOGISTS"),
    @XmlEnumValue("NB DIETITIAN")
    NB_DIETITIAN("NB DIETITIAN"),
    @XmlEnumValue("ON OTHER")
    ON_OTHER("ON OTHER"),
    @XmlEnumValue("NB MIDWIFE")
    NB_MIDWIFE("NB MIDWIFE"),
    @XmlEnumValue("UNK PRESCRIBER")
    UNK_PRESCRIBER("UNK PRESCRIBER");
    private final String value;

    PrescriberType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrescriberType fromValue(String v) {
        for (PrescriberType c: PrescriberType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
