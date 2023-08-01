public class Patient {
    String patient_name;
    String patient_contact;
    int patient_age;
    String patient_gender;
    String patient_blood_group;

    public Patient(String patient_name, String patient_contact, int patient_age2,
            String patient_gender, String patient_blood_group) {
        this.patient_name = patient_name;
        this.patient_contact = patient_contact;
        this.patient_age = patient_age2;
        this.patient_gender = patient_gender;
        this.patient_blood_group = patient_blood_group;
    }

    public Patient() {
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_contact() {
        return patient_contact;
    }

    public void setPatient_contact(String patient_contact) {
        this.patient_contact = patient_contact;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public String getPatient_blood_group() {
        return patient_blood_group;
    }

    public void setPatient_blood_group(String patient_blood_group) {
        this.patient_blood_group = patient_blood_group;
    }

}
