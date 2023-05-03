package diet;

import java.util.TreeMap;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {

	String nomeMenu;
	TreeMap<String,Number> dizElem = new TreeMap<>();
	Food f;
	
	public Menu(String name,Food robe) {
		nomeMenu = name;
		f = robe;
	}
	
    public Menu addRecipe(String recipe, double quantity) {
		dizElem.put(recipe, quantity);
		return this;
	}

    public Menu addProduct(String product) {
		dizElem.put(product, 1);
		return this;
	}

	@Override
	public String getName() {
		return nomeMenu;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		double acc=0;
		double prop;
		for(String key : dizElem.keySet()) {
			if (f.dizRic.containsKey(key)) {
				prop = f.dizRic.get(key).getCalories()*dizElem.get(key).doubleValue()/100;
				acc=acc+prop;
			}
			else{
				prop = f.dizPrec.get(key).getCalories();
				acc = acc + prop;
			}
			
		}
		return acc;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		double acc=0;
		double prop;
		for(String key : dizElem.keySet()) {
			if (f.dizRic.containsKey(key)) {
				prop = f.dizRic.get(key).getProteins()*dizElem.get(key).doubleValue()/100;
				acc=acc+prop;
			}
			else{
				prop = f.dizPrec.get(key).getProteins();
				acc = acc + prop;
			}
			
		}
		return acc;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		double acc=0;
		double prop;
		for(String key : dizElem.keySet()) {
			if (f.dizRic.containsKey(key)) {
				prop = f.dizRic.get(key).getCarbs()*dizElem.get(key).doubleValue()/100;
				acc=acc+prop;
			}
			else{
				prop = f.dizPrec.get(key).getCarbs();
				acc = acc + prop;
			}
			
		}
		return acc;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		double acc=0;
		double prop;
		for(String key : dizElem.keySet()) {
			if (f.dizRic.containsKey(key)) {
				prop = f.dizRic.get(key).getFat()*dizElem.get(key).doubleValue()/100;
				acc=acc+prop;
			}
			else{
				prop = f.dizPrec.get(key).getFat();
				acc = acc + prop;
			}
			
		}
		return acc;
	}


	@Override
	public boolean per100g() {
		return false;
	}
}