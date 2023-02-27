import java.util.ArrayList;

public interface Admission {
	
	ArrayList<AdmissionDAO> getByID(int iD, ArrayList <AdmissionDAO> admissionDAOs);
	
	String getAll(AdmissionDAO admissionDAO);

}
