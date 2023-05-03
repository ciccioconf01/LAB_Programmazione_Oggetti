package diet;

public class Cibo implements NutritionalElement {
	String nome;
	double calorie;
	double proteine;
	double carboidrati;
	double grassi;
	boolean centoG;
	
	public Cibo(String name, double calories, double proteins, double carbs, double fat, boolean cento) {
		nome = name;
		calorie = calories;
		proteine = proteins;
		carboidrati = carbs;
		grassi = fat;
		centoG=cento;
	}
	@Override
	public String getName() {
		
		return nome;
	}

	@Override
	public double getCalories() {
		
		return calorie;
	}

	@Override
	public double getProteins() {
		
		return proteine;
	}

	@Override
	public double getCarbs() {
		
		return carboidrati;
	}

	@Override
	public double getFat() {
		
		return grassi;
	}

	@Override
	public boolean per100g() {
		return centoG;
	}
			 
	

}
