
public class Inpatient implements Examination {
	
	Examination newExamination;
	
	public Inpatient() {
		super();
	}
	
	@Override
	public void addOperation(Examination operation) {
		this.newExamination = operation;
	}

	@Override
	public String printOperations() {
		return "	Inpatient ";
	}

	@Override
	public int cost() {
		return 10;
	}
}
