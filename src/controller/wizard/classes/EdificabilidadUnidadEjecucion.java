package controller.wizard.classes;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Dao;

public class EdificabilidadUnidadEjecucion {

	private UnidadEjecucion unidadEjecucion;
	private ArrayList<TipoOrdenacionPormenorizada> tiposOrdenacionesPormenorizadas;
 	
	public EdificabilidadUnidadEjecucion(ArrayList<TipoOrdenacionPormenorizada> tipos, UnidadEjecucion unidadEjecucion){
		
		this.unidadEjecucion = unidadEjecucion;
		this.tiposOrdenacionesPormenorizadas = new ArrayList<TipoOrdenacionPormenorizada>();
		
		try{
			Dao dao = new Dao();
			for(int i = 0; i < tipos.size(); i++){
				TipoOrdenacionPormenorizada aux = (TipoOrdenacionPormenorizada) tipos.get(i).clone();
				this.tiposOrdenacionesPormenorizadas.add(dao.getPrograma().calcularEdificabilidadesDeTipoPormenorizadoPorUnidadEjecucion(aux, unidadEjecucion.getIdUnidadEjecucion()));
			}
			dao.getConection().close();
		
		}
		catch(CloneNotSupportedException e1){
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	@Override
	public String toString() {
		String aux = unidadEjecucion.getDenominacion() + "[\nEdifPondTotal = " + calcularEdificabilidadPonderadaTotal() + "\n   Tipos:\n";
		for(int i = 0; i < tiposOrdenacionesPormenorizadas.size(); i++){
			aux += "   - " + tiposOrdenacionesPormenorizadas.get(i) + "\n";
		}
		
		return aux += "]";
	}




	public UnidadEjecucion getUnidadEjecucion() {
		return unidadEjecucion;
	}



	public void setUnidadEjecucion(UnidadEjecucion unidadEjecucion) {
		this.unidadEjecucion = unidadEjecucion;
	}



	public ArrayList<TipoOrdenacionPormenorizada> getTiposOrdenacionesPormenorizadas() {
		return tiposOrdenacionesPormenorizadas;
	}
	public void setTiposOrdenacionesPormenorizadas(
			ArrayList<TipoOrdenacionPormenorizada> tiposOrdenacionesPormenorizadas) {
		this.tiposOrdenacionesPormenorizadas = tiposOrdenacionesPormenorizadas;
	}
	public double calcularEdificabilidadPonderadaTotal(){
		double edifPondTotal = 0;
		for(int i = 0; i < tiposOrdenacionesPormenorizadas.size(); i++){
			edifPondTotal += tiposOrdenacionesPormenorizadas.get(i).getEdifPond();
		}
		return edifPondTotal;
	}

	
	
}
