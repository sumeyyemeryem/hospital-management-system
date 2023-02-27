import java.util.ArrayList;

public interface Patient {
	
	
	//application should terminate and save files automatically when finish to read the input file
	
	PatientDAO getByID(int iD, ArrayList <PatientDAO> patientDAOs); //read a single entry from file 
	
	PatientDAO deleteByID(int iD, ArrayList <PatientDAO> patientDAOs); //delete a single entry from file
	
	void add(PatientDAO object, ArrayList <PatientDAO> patientDAOs); //add or update an entry
	
    String getAll(PatientDAO patientDAO); // get all entries

	

}
