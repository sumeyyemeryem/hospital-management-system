import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Hospital {
	
	private ArrayList <PatientDAO> patientDAOs = new ArrayList<PatientDAO>(); 
	private ArrayList <AdmissionDAO> admissioniDs = new ArrayList<AdmissionDAO>();
	private ArrayList <AdmissionDAO> admissionDAOs = new ArrayList<AdmissionDAO>();

	public void readInputFile(String[] inputFile, String[] patientList, String[] admissionList) throws IOException{ 
		
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", false)));
		PrintWriter updatedpatient = new PrintWriter(new BufferedWriter(new FileWriter("updatedpatient.txt", false)));
		PrintWriter updatedadmission = new PrintWriter(new BufferedWriter(new FileWriter("updatedadmission.txt", false)));

		//splits the admission text and creates admission data access objects from the given infos and adds them to array lists   
		int admissioniD = 0;
		for (String line : admissionList) {
			String[] admissionInfo = line.split("	");
			if(admissionInfo[0].length() == 1) {
				admissioniD = Integer.valueOf(admissionInfo[0]);
				AdmissionDAO newAdmission = new AdmissionDAO(admissioniD, Integer.valueOf(admissionInfo[1]));	
				admissioniDs.add(newAdmission);
			}
			else {
				String[] list = admissionInfo[1].split(" ");
				ArrayList <String> operations = new ArrayList<String>();
				for(int k = 0; k < list.length ; k++ ) {
					operations.add(list[k]);
				}
				AdmissionDAO newAdmission = new AdmissionDAO(admissioniD, admissionInfo[0], operations); //creating new admission
				admissionDAOs.add(newAdmission);	
			}
		}
		//splits the patient text and creates patient data access objects from the given infos and adds them to array list
		for (String line : patientList) {
			String[] patientInfo = line.split("	");
			String[] name = patientInfo[1].split(" ");
			PatientDAO patient = new PatientDAO(Integer.valueOf(patientInfo[0]), name[0], name[1], patientInfo[2], patientInfo[3]);
			patientDAOs.add(patient); }
		
		//splits the input file and according to requests calls required methods
		for (String line : inputFile) {
			String[] applicationType = line.split(" "); 
	
			if (applicationType[0].equals("AddPatient")) {	
				String address = "Address: " + applicationType[5];
				for(int i = 6; i < applicationType.length ; i++ ) {
					address = address + " " +  applicationType[i];
				}
				PatientDAO patient = new PatientDAO(Integer.valueOf(applicationType[1]), applicationType[2], applicationType[3], applicationType[4], address);
				patient.add(patient, patientDAOs);
				output.println("Patient " + patient.getiD() + " " + patient.getName() + " added.");
			}
			
			if (applicationType[0].equals("RemovePatient")) {
				PatientDAO patientDAO = new PatientDAO();
				AdmissionDAO admissionDAO = new AdmissionDAO();
				int iD = Integer.valueOf(applicationType[1]);

				PatientDAO removedPatient = patientDAO.deleteByID(iD, patientDAOs); //remove patient
				
				output.println("Patient " + iD + " " + removedPatient.getName() + " removed.");
				
				//remove related admission
				int adID = 0;
				for (int k = 0; k < admissioniDs.size(); k++) {
					if(admissioniDs.get(k).getiD() == iD) {
						adID = admissioniDs.get(k).getAdmissioniD();
						admissioniDs.remove(admissioniDs.get(k));}
				}
				ArrayList <AdmissionDAO> removedAdmissions = admissionDAO.getByID(adID, admissionDAOs);
				for (int n = 0; n < removedAdmissions.size(); n++) {
					admissionDAOs.remove(removedAdmissions.get(n)); }
				
			}
			
			if (applicationType[0].equals("ListPatients")) {
				PatientDAO patientDAO = new PatientDAO();
				output.println("Patient List:");
				Collections.sort(patientDAOs, new NameSorter()); //sorted by names of patients
				for (int i = 0; i< patientDAOs.size(); i++) {
					output.println(patientDAO.getAll(patientDAOs.get(i))); }
				output.close();
			}
			
			if (applicationType[0].equals("CreateAdmission")) {
				
				AdmissionDAO newAdmission = new AdmissionDAO(Integer.valueOf(applicationType[1]), Integer.valueOf(applicationType[2]));	
				admissioniDs.add(newAdmission);
				output.println("Admission " + Integer.valueOf(applicationType[1]) + " created");
			}
			
			
			if (applicationType[0].equals("AddExamination")) {
				ArrayList <String> operations = new ArrayList<String>();
				for(int i = 3; i < applicationType.length ; i++ ) {
					operations.add(applicationType[i]); }
				
				AdmissionDAO newAdmission = new AdmissionDAO(Integer.valueOf(applicationType[1]), applicationType[2], operations); //creating new admission
				admissionDAOs.add(newAdmission);
				
				output.println(applicationType[2] + " examination added to admission " + Integer.valueOf(applicationType[1]));
				
				//decorator pattern
				Examination operation;
				if (applicationType[2].equals("Inpatient")) {
					operation = new Inpatient();
				}
				else {
					operation = new Outpatient();
				}

				for(int i =0; i < operations.size(); i++) {
					if (operations.get(i).equals("imaging")) {
						operation = new Imaging(operation);
					}
					else if (operations.get(i).equals("tests")) {
						operation = new Tests(operation);
					}
					else if (operations.get(i).equals("measurments")) {
						operation = new Measurments(operation);
					}
					else if (operations.get(i).equals("doctorvisit")) {
						operation = new DoctorVisit(operation);
					}
			   } 
				Examination examination = operation; 
				examination.addOperation(operation);		
		    }
			
			if (applicationType[0].equals("TotalCost")) {
				AdmissionDAO examinationInfo = new AdmissionDAO();
				ArrayList<AdmissionDAO> admissions = examinationInfo.getByID(Integer.valueOf(applicationType[1]), admissionDAOs);
				output.println("Total cost for admission " + admissions.get(0).getAdmissioniD());
				int total = 0;
				
				//decorator pattern
				Examination operation;
				for (int m = 0; m < admissions.size(); m++) {
					if(admissions.get(m).getExaminationType().equals("Inpatient")) {
						operation = new Inpatient();
					}
				   else {
					    operation = new Outpatient();
				    }

					for(int i = 0; i < admissions.get(m).getOperations().size(); i++) {
						if (admissions.get(m).getOperations().get(i).equals("imaging")) {
							operation = new Imaging(operation);
						}
						else if (admissions.get(m).getOperations().get(i).equals("tests")) {
							operation = new Tests(operation);
						}
						else if (admissions.get(m).getOperations().get(i).equals("measurements")) {
							operation = new Measurments(operation);
						}
						else if (admissions.get(m).getOperations().get(i).equals("doctorvisit")) {
							operation = new DoctorVisit(operation);
						}
				   } 
					Examination examination = operation;
					output.print(examination.printOperations() + " ");
					output.println(examination.cost() + "$");
				    total = total + examination.cost();
				}
				output.println(String.format("	Total: %s$", total));
              }
		}
		//update the patient text
		Patient patientDAO = new PatientDAO();  
		Collections.sort(patientDAOs, new IdSorter()); //sorted by patient ID's
		for (int i = 0; i< patientDAOs.size(); i++) {
			updatedpatient.println(patientDAO.getAll(patientDAOs.get(i))); }
		updatedpatient.close();
		
		
		//update the admission text
		Admission admissionDAO = new AdmissionDAO();
		Collections.sort(admissionDAOs, new AdmissioniDSorter()); //sorted by patient admission ID's
		Collections.sort(admissioniDs, new AdmissioniDSorter());
		for (int i = 0; i< admissioniDs.size(); i++) {
		    updatedadmission.println(admissioniDs.get(i).getAdmissioniD() + "	" + admissioniDs.get(i).getiD());
		    ArrayList<AdmissionDAO> patientAdmissions = admissionDAO.getByID(admissioniDs.get(i).getAdmissioniD(), admissionDAOs);
		    for (int m = 0; m < patientAdmissions.size(); m++) {
		    	updatedadmission.println(admissionDAO.getAll(patientAdmissions.get(m))); }
		}
		updatedadmission.close();
		
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}