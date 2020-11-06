package recipe.model;

public class NutritionFacts {

	protected Integer nutritionFactsId;
	protected double calories;
	protected Integer totalFat;
	protected Integer sugar;
	protected Integer sodium;
	protected Integer protein;
	protected Integer saturatedFat;
	protected Integer carb;
	protected Integer recipeId;

	public NutritionFacts(Integer nutritionFactsId, double calories, Integer totalFat, Integer sodium, Integer protein, Integer saturatedFat, Integer carb, Integer recipeId) {
		this.nutritionFactsId = nutritionFactsId;
		this.calories = calories;
		this.totalFat = totalFat;
		this.sodium = sodium;
		this.protein = protein;
		this.saturatedFat = saturatedFat;
		this.carb = carb;
		this.recipeId = recipeId;
	}
	
	public Integer getNutritionFactsId() {
		return nutritionFactsId;
	}
	
	public void setNutritionFactsId(Integer nutritionFactsId) {
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

	public void setTotalFat(Integer totalFat) {
		this.totalFat = totalFat;
	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}

	public Integer getSodium() {
		return sodium;
	}

	public void setSodium(Integer sodium) {
		this.sodium = sodium;
	}

	public Integer getProtein() {
		return protein;
	}

	public void setProtein(Integer protein) {
		this.protein = protein;
	}

	public Integer getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(Integer saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public Integer getCarb() {
		return carb;
	}

	public void setCarb(Integer carb) {
		this.carb = carb;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	
}

