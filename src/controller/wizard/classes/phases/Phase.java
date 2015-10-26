package controller.wizard.classes.phases;

public class Phase {

	private int idPlan;
	private int fase;
	
	public Phase(int idPlan){
		this.setIdPlan(idPlan);
		this.setFase(fase);
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}
	
}
