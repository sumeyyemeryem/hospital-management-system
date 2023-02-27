import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException{
		
		String  a = args[0];
		ReadFromFile reading = new ReadFromFile();
		String[] inputFile = reading.readFile(a);
		String[] patientList = reading.readFile("patient.txt");
		String[] admissionList = reading.readFile("admission.txt");
		
		Hospital infos = new Hospital();
		infos.readInputFile(inputFile,patientList, admissionList);
	}

}
