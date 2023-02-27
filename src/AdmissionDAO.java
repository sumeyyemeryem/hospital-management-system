import java.util.ArrayList;

public class AdmissionDAO implements Admission {
	
	private int admissioniD;
	private int iD;
	private String examinationType;
	private ArrayList <String> operations;
	
	public AdmissionDAO() {
		super();
	}

	public AdmissionDAO(int admissioniD, int iD) {
		this.admissioniD = admissioniD;
		this.iD = iD;	
	}
	
	
	public AdmissionDAO(int admissioniD, String examinationType, ArrayList<String> operations) {
		this.admissioniD = admissioniD;
		this.examinationType = examinationType;
		this.operations = operations;
	}

	@Override
	public ArrayList <AdmissionDAO> getByID(int admissioniD, ArrayList<AdmissionDAO> admissionDAOs) { //returns the admissions of requested patient by iD
		int k = 0;
		ArrayList <AdmissionDAO> admissions = new ArrayList<AdmissionDAO>();
		int len = admissionDAOs.size();
		for(int i=0; i<len; i++) {
		    if (admissionDAOs.get(i).getAdmissioniD() == admissioniD) {
		        k = i;
		        admissions.add(admissionDAOs.get(k));
		    }
		}
		return admissions;
	}	
	@Override
	public String getAll(AdmissionDAO admissionDAO) { //returns the examinations of admissions
		String operations = " ";
		for(int j = 0; j < admissionDAO.getOperations().size(); j++) {
			operations = operations + admissionDAO.getOperations().get(j) + " ";
		}
		return admissionDAO.getExaminationType() + "	" + operations;
	}

	public int getAdmissioniD() {
		return admissioniD;
	}

	public void setAdmissioniD(int admissioniD) {
		this.admissioniD = admissioniD;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getExaminationType() {
		return examinationType;
	}

	public void setExaminationType(String examinationType) {
		this.examinationType = examinationType;
	}

	public ArrayList<String> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<String> operations) {
		this.operations = operations;
	}

	/**@Override
	public void admissionCost(int admissionID, ArrayList<AdmissionDAO> admission) {
		// TODO Auto-generated method stub
		
	}*/





}
