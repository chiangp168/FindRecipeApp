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
	protected int recipeId;

	public NutritionFacts(int nutritionFactsId, double calories, int totalFat, int sodium, int protein, int saturatedFat, int carb, int recipeId) {
		this.nutritionFactsId = nutritionFactsId;
		this.calories = calories;
		this.totalFat = totalFat;
		this.sodium = sodium;
		this.protein = protein;
		this.saturatedFat = saturatedFat;
		this.carb = carb;
		this.recipeId = recipeId;
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

	public Integer getSaturatedFat() {
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

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
}

