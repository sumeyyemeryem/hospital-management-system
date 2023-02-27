
public class Outpatient implements Examination {
	
	Examination newExamination;

	@Override
	public void addOperation(Examination operation) {
		this.newExamination = operation;
	}

	@Override
	public String printOperations() {
		return "	Outpatient ";
	}

	@Override
	public int cost() {
		return 15;
	}

}