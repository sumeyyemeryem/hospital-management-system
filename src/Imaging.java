
public class Imaging extends ExaminationDecorator{
	
	public Imaging() {
		super();
	}

	public Imaging(Examination newOperation) {
		super(newOperation);
	}

	@Override
	public void addOperation(Examination operation) {
		super.addOperation(operation);
	}

	@Override
	public String printOperations() {
		return super.printOperations() + " imaging";
	}

	@Override
	public int cost() {
		return super.cost() + 10;
	}
	

}
