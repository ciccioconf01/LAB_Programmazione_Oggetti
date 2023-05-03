package diet;

import java.util.Collection;
import java.util.Collections;
import java.util.*;
/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {
	
	SortedMap<String,NutritionalElement> dizPrime = new TreeMap<>();
	SortedMap<String,NutritionalElement> dizPrec = new TreeMap<>();
	SortedMap<String,NutritionalElement> dizRic = new TreeMap<>();
	SortedMap<String,Menu> dizMen = new TreeMap<>();
	
	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat) {
		
		Cibo cibo = new Cibo(name,calories,proteins,carbs,fat,true);
		dizPrime.put(name,cibo);
		
	}
	

	public Collection<NutritionalElement> rawMaterials() {
		
		return dizPrime.values();
		
	}

	public NutritionalElement getRawMaterial(String name) {
		return dizPrime.get(name);
	}

	
	
	
	public void defineProduct(String name, double calories, double proteins, double carbs, double fat) {
		Cibo cibo = new Cibo(name,calories,proteins,carbs,fat,false);
		dizPrec.put(name,cibo);
	}

	
	public Collection<NutritionalElement> products() {
		return dizPrec.values();
	}

	 public NutritionalElement getProduct(String name) {
		return dizPrec.get(name);
	}

	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * @return the newly created Recipe object
	 */
	public Recipe createRecipe(String name) {
		Recipe vuoto = new Recipe(name,this);
		dizRic.put(name, vuoto);
		
		return vuoto;
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes() {
		
		return dizRic.values();
	}

	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name) {
		return dizRic.get(name);
	}

	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu m = new Menu(name,this);
		dizMen.put(name, m);
		return m;
	}
}