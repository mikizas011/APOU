package controller.wizard.classes.phases;

public class PhaseTexto extends Phase{

	private String text;
	
	public PhaseTexto(int idPlan, String text) {
		super(idPlan);
		this.setText(text);
	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
