public class PatientAppointmentData {
    private String patientName;
    private String patientGender;
    private int patientAge;
    private String patientContact;
    private String patientBloodGroup;
    private String appointmentTime;
    private String doctorName;

    // Constructors, getters, and setters for the properties

    public PatientAppointmentData() {
    }

    public PatientAppointmentData(String patientName, String patientGender, int patientAge, String patientContact,
            String patientBloodGroup, String appointmentTime, String doctorName) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.patientContact = patientContact;
        this.patientBloodGroup = patientBloodGroup;
        this.appointmentTime = appointmentTime;
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getPatientBloodGroup() {
        return patientBloodGroup;
    }

    public void setPatientBloodGroup(String patientBloodGroup) {
        this.patientBloodGroup = patientBloodGroup;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
