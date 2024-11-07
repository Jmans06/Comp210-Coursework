package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> _patients;

    public SimpleEmergencyRoom() {
        _patients = new ArrayList<>();
    }

    /**
     * TODO (Task 1): dequeue
     * @return return patient with the highest priority
     */
    public Patient dequeue() {
        Patient topPriorityPatient=_patients.get(0);
        for (Patient patient:_patients){
            if (patient.getPriority().compareTo(topPriorityPatient.getPriority())>0){
                topPriorityPatient=patient;
            }
        }
        _patients.remove(topPriorityPatient);
        return topPriorityPatient;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        _patients.add(patient);
    }

    public List getPatients() {
        return _patients;
    }

    public int size() {
        return _patients.size();
    }

}