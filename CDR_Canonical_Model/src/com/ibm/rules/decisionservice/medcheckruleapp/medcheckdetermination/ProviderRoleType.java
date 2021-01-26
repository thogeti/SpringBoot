
package com.ibm.rules.decisionservice.medcheckruleapp.medcheckdetermination;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProviderRoleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProviderRoleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Cashier"/>
 *     &lt;enumeration value="Pharmacy Assistant"/>
 *     &lt;enumeration value="Regulated Technician"/>
 *     &lt;enumeration value="Central Support"/>
 *     &lt;enumeration value="Pharmacy Student"/>
 *     &lt;enumeration value="Pharmacy Intern"/>
 *     &lt;enumeration value="Store Administrator"/>
 *     &lt;enumeration value="Acupuncturist"/>
 *     &lt;enumeration value="AdvancedCareParamedic"/>
 *     &lt;enumeration value="AssociateMedicalOfficerofHealth"/>
 *     &lt;enumeration value="Audiologist"/>
 *     &lt;enumeration value="CertifiedGraduateNurse"/>
 *     &lt;enumeration value="Chiropractic"/>
 *     &lt;enumeration value="CommunicableDiseaseCaseInvestigator"/>
 *     &lt;enumeration value="CriticalCareParamedic"/>
 *     &lt;enumeration value="DentalAssistant"/>
 *     &lt;enumeration value="DentalHygienist"/>
 *     &lt;enumeration value="Dentist"/>
 *     &lt;enumeration value="Denturist"/>
 *     &lt;enumeration value="DoctorofOsteopathy"/>
 *     &lt;enumeration value="EmergencyMedicalResponder"/>
 *     &lt;enumeration value="Epidemiologist"/>
 *     &lt;enumeration value="HospitalInfectionControlPractitioner"/>
 *     &lt;enumeration value="InfectionControlPractitioner"/>
 *     &lt;enumeration value="LabTechnician"/>
 *     &lt;enumeration value="LicensedPracticalNurse"/>
 *     &lt;enumeration value="MedicalDoctor"/>
 *     &lt;enumeration value="MedicalLaboratoryTechnologist"/>
 *     &lt;enumeration value="MedicalOfficeAssistant"/>
 *     &lt;enumeration value="MedicalOfficerofHealth"/>
 *     &lt;enumeration value="NuclearMedicineTechnologist"/>
 *     &lt;enumeration value="Nurse'sAide"/>
 *     &lt;enumeration value="OccupationalTherapist"/>
 *     &lt;enumeration value="Optometrist"/>
 *     &lt;enumeration value="ParamedicPractitioner"/>
 *     &lt;enumeration value="Pharmacist"/>
 *     &lt;enumeration value="Physiotherapist"/>
 *     &lt;enumeration value="Podiatrist"/>
 *     &lt;enumeration value="PrimaryCareParamedic"/>
 *     &lt;enumeration value="ProvincialMedicalHealthOfficer"/>
 *     &lt;enumeration value="Psychologist"/>
 *     &lt;enumeration value="PublicHealthInspector"/>
 *     &lt;enumeration value="PublicHealthInvestigator"/>
 *     &lt;enumeration value="PublicHealthManager/Supervisor"/>
 *     &lt;enumeration value="PublicHealthNurseManager"/>
 *     &lt;enumeration value="RadiationTechnologistinMagneticResonance"/>
 *     &lt;enumeration value="RadiationTechnologistinRadiation"/>
 *     &lt;enumeration value="RadiationTechnologistinTherapy"/>
 *     &lt;enumeration value="RegisteredAcupuncturist"/>
 *     &lt;enumeration value="RegisteredDietitian"/>
 *     &lt;enumeration value="RegisteredElectroencephalographyTechnologist"/>
 *     &lt;enumeration value="RegisteredElectromyographyTechnologist"/>
 *     &lt;enumeration value="RegisteredEvokedPotentialTechnologist"/>
 *     &lt;enumeration value="RegisteredMidwife"/>
 *     &lt;enumeration value="RegisteredNurse"/>
 *     &lt;enumeration value="RegisteredNursePractitioner"/>
 *     &lt;enumeration value="RegisteredPsychiatricNurse"/>
 *     &lt;enumeration value="SocialWorker"/>
 *     &lt;enumeration value="SpeechLanguagePathologist"/>
 *     &lt;enumeration value="SpeechTherapist"/>
 *     &lt;enumeration value="Veterinarian"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
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
