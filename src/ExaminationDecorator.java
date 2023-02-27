
public abstract class ExaminationDecorator implements Examination{
	
	private Examination newExamination;
	
	public Examination getNewExamination() {
		return newExamination;
	}

	public void setNewExamination(Examination newExamination) {
		this.newExamination = newExamination;
	}

	public ExaminationDecorator() {
		super();
	}

	public ExaminationDecorator(Examination examination) {
		this.newExamination = examination;
	}

	@Override
	public void addOperation(Examination operation) { //adds operations
		this.newExamination = operation;
		
	}

	@Override
	public String printOperations() {
		//returns operations
		return newExamination.printOperations();

	}

	@Override
	public int cost() {
		//returns operations cost
		return newExamination.cost();
		
		
	}
	
	
	

}
