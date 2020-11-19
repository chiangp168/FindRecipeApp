package recipe.model;

public class NutritionFacts {

	protected int nutritionFactsId;
	protected double calories;
	protected int totalFat;
	protected int sugar;
	protected int sodium;
	protected int protein;
	protected int saturatedFat;
	protected int carb;
	protected Recipes recipe;

	public NutritionFacts(int nutritionFactsId, double calories, int totalFat, int sugar, int sodium, 
			int protein, int saturatedFat, int carb, Recipes recipe) {
		this.nutritionFactsId = nutritionFactsId;
		this.calories = calories;
		this.totalFat = totalFat;
		this.sugar = sugar;
		this.sodium = sodium;
		this.protein = protein;
		this.saturatedFat = saturatedFat;
		this.carb = carb;
		this.recipe = recipe;
	}




	public int getNutritionFactsId() {
		return nutritionFactsId;
	}
	
	public void setNutritionFactsId(int nutritionFactsId) {
		this.nutritionFactsId = nutritionFactsId;
	}
	 
	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public int getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(int totalFat) {
		this.totalFat = totalFat;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(int saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public int getCarb() {
		return carb;
	}

	public void setCarb(int carb) {
		this.carb = carb;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}
	
}

