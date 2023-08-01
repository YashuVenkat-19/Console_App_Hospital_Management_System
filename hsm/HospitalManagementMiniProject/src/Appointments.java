public class Appointments {

    String appointment_time;
    String patient_name;
    String doctor_name;
    String app_patient_contact;

    public Appointments(String appointment_time, String patient_name, String doctor_name,
            String app_patient_contact) {
        this.appointment_time = appointment_time;
        this.patient_name = patient_name;
        this.doctor_name = doctor_name;
        this.app_patient_contact = app_patient_contact;
    }

    public Appointments() {
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getApp_patient_contact() {
        return app_patient_contact;
    }

    public void setApp_patient_contact(String app_patient_contact) {
        this.app_patient_contact = app_patient_contact;
    }

}
