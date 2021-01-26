
package ca.shoppersdrugmart.rxhb.ehealth;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProviderRoleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProviderRoleType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Cashier"/&gt;
 *     &lt;enumeration value="Pharmacy Assistant"/&gt;
 *     &lt;enumeration value="Regulated Technician"/&gt;
 *     &lt;enumeration value="Central Support"/&gt;
 *     &lt;enumeration value="Pharmacy Student"/&gt;
 *     &lt;enumeration value="Pharmacy Intern"/&gt;
 *     &lt;enumeration value="Store Administrator"/&gt;
 *     &lt;enumeration value="Acupuncturist"/&gt;
 *     &lt;enumeration value="AdvancedCareParamedic"/&gt;
 *     &lt;enumeration value="AssociateMedicalOfficerofHealth"/&gt;
 *     &lt;enumeration value="Audiologist"/&gt;
 *     &lt;enumeration value="CertifiedGraduateNurse"/&gt;
 *     &lt;enumeration value="Chiropractic"/&gt;
 *     &lt;enumeration value="CommunicableDiseaseCaseInvestigator"/&gt;
 *     &lt;enumeration value="CriticalCareParamedic"/&gt;
 *     &lt;enumeration value="DentalAssistant"/&gt;
 *     &lt;enumeration value="DentalHygienist"/&gt;
 *     &lt;enumeration value="Dentist"/&gt;
 *     &lt;enumeration value="Denturist"/&gt;
 *     &lt;enumeration value="DoctorofOsteopathy"/&gt;
 *     &lt;enumeration value="EmergencyMedicalResponder"/&gt;
 *     &lt;enumeration value="Epidemiologist"/&gt;
 *     &lt;enumeration value="HospitalInfectionControlPractitioner"/&gt;
 *     &lt;enumeration value="InfectionControlPractitioner"/&gt;
 *     &lt;enumeration value="LabTechnician"/&gt;
 *     &lt;enumeration value="LicensedPracticalNurse"/&gt;
 *     &lt;enumeration value="MedicalDoctor"/&gt;
 *     &lt;enumeration value="MedicalLaboratoryTechnologist"/&gt;
 *     &lt;enumeration value="MedicalOfficeAssistant"/&gt;
 *     &lt;enumeration value="MedicalOfficerofHealth"/&gt;
 *     &lt;enumeration value="NuclearMedicineTechnologist"/&gt;
 *     &lt;enumeration value="Nurse'sAide"/&gt;
 *     &lt;enumeration value="OccupationalTherapist"/&gt;
 *     &lt;enumeration value="Optometrist"/&gt;
 *     &lt;enumeration value="ParamedicPractitioner"/&gt;
 *     &lt;enumeration value="Pharmacist"/&gt;
 *     &lt;enumeration value="Physiotherapist"/&gt;
 *     &lt;enumeration value="Podiatrist"/&gt;
 *     &lt;enumeration value="PrimaryCareParamedic"/&gt;
 *     &lt;enumeration value="ProvincialMedicalHealthOfficer"/&gt;
 *     &lt;enumeration value="Psychologist"/&gt;
 *     &lt;enumeration value="PublicHealthInspector"/&gt;
 *     &lt;enumeration value="PublicHealthInvestigator"/&gt;
 *     &lt;enumeration value="PublicHealthManager/Supervisor"/&gt;
 *     &lt;enumeration value="PublicHealthNurseManager"/&gt;
 *     &lt;enumeration value="RadiationTechnologistinMagneticResonance"/&gt;
 *     &lt;enumeration value="RadiationTechnologistinRadiation"/&gt;
 *     &lt;enumeration value="RadiationTechnologistinTherapy"/&gt;
 *     &lt;enumeration value="RegisteredAcupuncturist"/&gt;
 *     &lt;enumeration value="RegisteredDietitian"/&gt;
 *     &lt;enumeration value="RegisteredElectroencephalographyTechnologist"/&gt;
 *     &lt;enumeration value="RegisteredElectromyographyTechnologist"/&gt;
 *     &lt;enumeration value="RegisteredEvokedPotentialTechnologist"/&gt;
 *     &lt;enumeration value="RegisteredMidwife"/&gt;
 *     &lt;enumeration value="RegisteredNurse"/&gt;
 *     &lt;enumeration value="RegisteredNursePractitioner"/&gt;
 *     &lt;enumeration value="RegisteredPsychiatricNurse"/&gt;
 *     &lt;enumeration value="SocialWorker"/&gt;
 *     &lt;enumeration value="SpeechLanguagePathologist"/&gt;
 *     &lt;enumeration value="SpeechTherapist"/&gt;
 *     &lt;enumeration value="Veterinarian"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProviderRoleType")
@XmlEnum
public enum ProviderRoleType {

    @XmlEnumValue("Cashier")
    CASHIER("Cashier"),
    @XmlEnumValue("Pharmacy Assistant")
    PHARMACY_ASSISTANT("Pharmacy Assistant"),
    @XmlEnumValue("Regulated Technician")
    REGULATED_TECHNICIAN("Regulated Technician"),
    @XmlEnumValue("Central Support")
    CENTRAL_SUPPORT("Central Support"),
    @XmlEnumValue("Pharmacy Student")
    PHARMACY_STUDENT("Pharmacy Student"),
    @XmlEnumValue("Pharmacy Intern")
    PHARMACY_INTERN("Pharmacy Intern"),
    @XmlEnumValue("Store Administrator")
    STORE_ADMINISTRATOR("Store Administrator"),
    @XmlEnumValue("Acupuncturist")
    ACUPUNCTURIST("Acupuncturist"),
    @XmlEnumValue("AdvancedCareParamedic")
    ADVANCED_CARE_PARAMEDIC("AdvancedCareParamedic"),
    @XmlEnumValue("AssociateMedicalOfficerofHealth")
    ASSOCIATE_MEDICAL_OFFICEROF_HEALTH("AssociateMedicalOfficerofHealth"),
    @XmlEnumValue("Audiologist")
    AUDIOLOGIST("Audiologist"),
    @XmlEnumValue("CertifiedGraduateNurse")
    CERTIFIED_GRADUATE_NURSE("CertifiedGraduateNurse"),
    @XmlEnumValue("Chiropractic")
    CHIROPRACTIC("Chiropractic"),
    @XmlEnumValue("CommunicableDiseaseCaseInvestigator")
    COMMUNICABLE_DISEASE_CASE_INVESTIGATOR("CommunicableDiseaseCaseInvestigator"),
    @XmlEnumValue("CriticalCareParamedic")
    CRITICAL_CARE_PARAMEDIC("CriticalCareParamedic"),
    @XmlEnumValue("DentalAssistant")
    DENTAL_ASSISTANT("DentalAssistant"),
    @XmlEnumValue("DentalHygienist")
    DENTAL_HYGIENIST("DentalHygienist"),
    @XmlEnumValue("Dentist")
    DENTIST("Dentist"),
    @XmlEnumValue("Denturist")
    DENTURIST("Denturist"),
    @XmlEnumValue("DoctorofOsteopathy")
    DOCTOROF_OSTEOPATHY("DoctorofOsteopathy"),
    @XmlEnumValue("EmergencyMedicalResponder")
    EMERGENCY_MEDICAL_RESPONDER("EmergencyMedicalResponder"),
    @XmlEnumValue("Epidemiologist")
    EPIDEMIOLOGIST("Epidemiologist"),
    @XmlEnumValue("HospitalInfectionControlPractitioner")
    HOSPITAL_INFECTION_CONTROL_PRACTITIONER("HospitalInfectionControlPractitioner"),
    @XmlEnumValue("InfectionControlPractitioner")
    INFECTION_CONTROL_PRACTITIONER("InfectionControlPractitioner"),
    @XmlEnumValue("LabTechnician")
    LAB_TECHNICIAN("LabTechnician"),
    @XmlEnumValue("LicensedPracticalNurse")
    LICENSED_PRACTICAL_NURSE("LicensedPracticalNurse"),
    @XmlEnumValue("MedicalDoctor")
    MEDICAL_DOCTOR("MedicalDoctor"),
    @XmlEnumValue("MedicalLaboratoryTechnologist")
    MEDICAL_LABORATORY_TECHNOLOGIST("MedicalLaboratoryTechnologist"),
    @XmlEnumValue("MedicalOfficeAssistant")
    MEDICAL_OFFICE_ASSISTANT("MedicalOfficeAssistant"),
    @XmlEnumValue("MedicalOfficerofHealth")
    MEDICAL_OFFICEROF_HEALTH("MedicalOfficerofHealth"),
    @XmlEnumValue("NuclearMedicineTechnologist")
    NUCLEAR_MEDICINE_TECHNOLOGIST("NuclearMedicineTechnologist"),
    @XmlEnumValue("Nurse'sAide")
    NURSE_S_AIDE("Nurse'sAide"),
    @XmlEnumValue("OccupationalTherapist")
    OCCUPATIONAL_THERAPIST("OccupationalTherapist"),
    @XmlEnumValue("Optometrist")
    OPTOMETRIST("Optometrist"),
    @XmlEnumValue("ParamedicPractitioner")
    PARAMEDIC_PRACTITIONER("ParamedicPractitioner"),
    @XmlEnumValue("Pharmacist")
    PHARMACIST("Pharmacist"),
    @XmlEnumValue("Physiotherapist")
    PHYSIOTHERAPIST("Physiotherapist"),
    @XmlEnumValue("Podiatrist")
    PODIATRIST("Podiatrist"),
    @XmlEnumValue("PrimaryCareParamedic")
    PRIMARY_CARE_PARAMEDIC("PrimaryCareParamedic"),
    @XmlEnumValue("ProvincialMedicalHealthOfficer")
    PROVINCIAL_MEDICAL_HEALTH_OFFICER("ProvincialMedicalHealthOfficer"),
    @XmlEnumValue("Psychologist")
    PSYCHOLOGIST("Psychologist"),
    @XmlEnumValue("PublicHealthInspector")
    PUBLIC_HEALTH_INSPECTOR("PublicHealthInspector"),
    @XmlEnumValue("PublicHealthInvestigator")
    PUBLIC_HEALTH_INVESTIGATOR("PublicHealthInvestigator"),
    @XmlEnumValue("PublicHealthManager/Supervisor")
    PUBLIC_HEALTH_MANAGER_SUPERVISOR("PublicHealthManager/Supervisor"),
    @XmlEnumValue("PublicHealthNurseManager")
    PUBLIC_HEALTH_NURSE_MANAGER("PublicHealthNurseManager"),
    @XmlEnumValue("RadiationTechnologistinMagneticResonance")
    RADIATION_TECHNOLOGISTIN_MAGNETIC_RESONANCE("RadiationTechnologistinMagneticResonance"),
    @XmlEnumValue("RadiationTechnologistinRadiation")
    RADIATION_TECHNOLOGISTIN_RADIATION("RadiationTechnologistinRadiation"),
    @XmlEnumValue("RadiationTechnologistinTherapy")
    RADIATION_TECHNOLOGISTIN_THERAPY("RadiationTechnologistinTherapy"),
    @XmlEnumValue("RegisteredAcupuncturist")
    REGISTERED_ACUPUNCTURIST("RegisteredAcupuncturist"),
    @XmlEnumValue("RegisteredDietitian")
    REGISTERED_DIETITIAN("RegisteredDietitian"),
    @XmlEnumValue("RegisteredElectroencephalographyTechnologist")
    REGISTERED_ELECTROENCEPHALOGRAPHY_TECHNOLOGIST("RegisteredElectroencephalographyTechnologist"),
    @XmlEnumValue("RegisteredElectromyographyTechnologist")
    REGISTERED_ELECTROMYOGRAPHY_TECHNOLOGIST("RegisteredElectromyographyTechnologist"),
    @XmlEnumValue("RegisteredEvokedPotentialTechnologist")
    REGISTERED_EVOKED_POTENTIAL_TECHNOLOGIST("RegisteredEvokedPotentialTechnologist"),
    @XmlEnumValue("RegisteredMidwife")
    REGISTERED_MIDWIFE("RegisteredMidwife"),
    @XmlEnumValue("RegisteredNurse")
    REGISTERED_NURSE("RegisteredNurse"),
    @XmlEnumValue("RegisteredNursePractitioner")
    REGISTERED_NURSE_PRACTITIONER("RegisteredNursePractitioner"),
    @XmlEnumValue("RegisteredPsychiatricNurse")
    REGISTERED_PSYCHIATRIC_NURSE("RegisteredPsychiatricNurse"),
    @XmlEnumValue("SocialWorker")
    SOCIAL_WORKER("SocialWorker"),
    @XmlEnumValue("SpeechLanguagePathologist")
    SPEECH_LANGUAGE_PATHOLOGIST("SpeechLanguagePathologist"),
    @XmlEnumValue("SpeechTherapist")
    SPEECH_THERAPIST("SpeechTherapist"),
    @XmlEnumValue("Veterinarian")
    VETERINARIAN("Veterinarian");
    private final String value;

    ProviderRoleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProviderRoleType fromValue(String v) {
        for (ProviderRoleType c: ProviderRoleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
