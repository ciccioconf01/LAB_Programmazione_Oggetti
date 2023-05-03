package diet;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	
	String nomeRicetta;
	TreeMap<String,Number> dizElem = new TreeMap<>();
	Food f;
	
	public Recipe(String name,Food robe) {
			nomeRicetta = name;
			f = robe;
		}
	
	public Recipe addIngredient(String material, double quantity) {
		dizElem.put(material, quantity);
		return this;
	}

	@Override
	public String getName() {
		return nomeRicetta;
	}

	
	@Override
	public double getCalories() {
		double acc=0;
		double prop;
		double peso=0;
		for(String key : dizElem.keySet()) {
			peso = peso + dizElem.get(key).doubleValue();
			prop = dizElem.get(key).doubleValue()/100;
			acc=acc+(f.dizPrime.get(key).getCalories()*prop);
		}
		return acc*100/peso;
	}
	

	@Override
	public double getProteins() {
		double acc=0;
		double prop;
		double peso=0;
		for(String key : dizElem.keySet()) {
			peso = peso + dizElem.get(key).doubleValue();
			prop = dizElem.get(key).doubleValue()/100;
			acc=acc+(f.dizPrime.get(key).getProteins()*prop);
		}
		return acc*100/peso;
	}

	@Override
	public double getCarbs() {
		double acc=0;
		double prop;
		double peso=0;
		for(String key : dizElem.keySet()) {
			peso = peso + dizElem.get(key).doubleValue();
			prop = dizElem.get(key).doubleValue()/100;
			acc=acc+(f.dizPrime.get(key).getCarbs()*prop);
		}
		return acc*100/peso;
	}

	@Override
	public double getFat() {
		double acc=0;
		double prop;
		double peso=0;
		for(String key : dizElem.keySet()) {
			peso = peso + dizElem.get(key).doubleValue();
			prop = dizElem.get(key).doubleValue()/100;
			acc=acc+(f.dizPrime.get(key).getFat()*prop);
		}
		return acc*100/peso;
	}

	
	@Override
	public boolean per100g() {
		return true;
	}
	
}
