
public class Tests extends ExaminationDecorator {
	
	public Tests() {
		super();
	}

	public Tests(Examination newOperation) {
		super(newOperation);
	}

	@Override
	public void addOperation(Examination operation) {
		super.addOperation(operation);
	}

	@Override
	public String printOperations() {
		return super.printOperations() + "tests";
	}

	@Override
	public int cost() {
		return super.cost() + 7;
	}

}
