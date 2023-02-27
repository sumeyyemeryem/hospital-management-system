import java.util.ArrayList;

public class PatientDAO implements Patient {
	
	private int iD;
	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	
	
	public PatientDAO() {
		super();
	}

	public PatientDAO(int iD, String name, String surname, String phoneNumber, String address) {
		this.iD = iD;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public PatientDAO getByID(int iD, ArrayList <PatientDAO> patientDAOs) { //returns the requested patient data access object by ID
		int len = patientDAOs.size();
		int k = 0;
		for(int i=0; i<len; i++) {
		    if (patientDAOs.get(i).getiD() == iD) {
		        k = i;
		    }
		}
		return patientDAOs.get(k);
	}

	@Override
	public PatientDAO deleteByID(int iD, ArrayList <PatientDAO> patientDAOs) { //removes and returns the patient data access object by ID
		PatientDAO removedPatient = new PatientDAO();
		for(int i = 0; i<patientDAOs.size(); i++) {
		    if (patientDAOs.get(i).getiD() == iD) {
		    	removedPatient = patientDAOs.get(i);
		        patientDAOs.remove(patientDAOs.get(i));
		    }
		}
		return removedPatient;
	}

	@Override
	public void add(PatientDAO patient, ArrayList <PatientDAO> patientDAOs) { //adds the patient data access objects to the array list
		patientDAOs.add(patient);		
	}

	@Override
	public String getAll(PatientDAO patientDAO){ //returns the information of patients as string 
			return patientDAO.getiD() + "	" + patientDAO.getName() + " " + patientDAO.getSurname() + "	" + patientDAO.getPhoneNumber() + "	" + patientDAO.getAddress();
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
