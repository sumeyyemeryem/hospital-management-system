
public class Measurments extends ExaminationDecorator {
	
	public Measurments() {
		super();
	}

	public Measurments(Examination newOperation) {
		super(newOperation);
	}

	@Override
	public void addOperation(Examination operation) {
		super.addOperation(operation);
	}

	@Override
	public String printOperations() {
		return super.printOperations() + " measurments ";
	}

	@Override
	public int cost() {
		return super.cost() + 5;
	}

}
