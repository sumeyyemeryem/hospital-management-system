import java.util.Comparator;

public class AdmissioniDSorter implements Comparator<AdmissionDAO> {

	@Override
	 public int compare(AdmissionDAO o1, AdmissionDAO o2) {
       return o1.getAdmissioniD() - o2.getAdmissioniD();
   }
	

}
