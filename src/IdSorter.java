import java.util.Comparator;

public class IdSorter implements Comparator<PatientDAO> {

	@Override
	 public int compare(PatientDAO o1, PatientDAO o2) {
        return o1.getiD() - o2.getiD();
    }

}
