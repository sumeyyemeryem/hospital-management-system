
public class DoctorVisit extends ExaminationDecorator {
	
	public DoctorVisit() {
		super();
	}

	public DoctorVisit(Examination newOperation) {
		super(newOperation);
	}

	@Override
	public void addOperation(Examination operation) {
		super.addOperation(operation);
	}

	@Override
	public String printOperations() {
		return super.printOperations() + " doctorvisit ";
	}

	@Override
	public int cost() {
		return super.cost() + 15;
	}

}
