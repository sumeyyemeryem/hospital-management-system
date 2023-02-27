import java.util.Comparator;

public class NameSorter implements Comparator<PatientDAO>{ 

	@Override
	public int compare(PatientDAO o1, PatientDAO o2) {
		return o1.getName().compareTo(o2.getName());
	}


}
