package recipe.model;

public class Recipes {
	/**
	 * RecipeId int - autoincrement
	 * RecipeName   -varchar(255)
	 * UserId INT   --fk
	 * TimeToCook INT
	 * NumOfStep INT
	 */
	
	protected int recipeId;
	protected String recipeName;
	protected Users user;
	protected int timeToCook;
	protected int numOfStep;
	
	/**
	 * constructor with all fields
	 * @param recipeId
	 * @param recipeName
	 * @param user
	 * @param timeToCook
	 * @param numOfStep
	 */
	public Recipes(int recipeId, String recipeName, Users user, int timeToCook, int numOfStep) {
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.user = user;
		this.timeToCook = timeToCook;
		this.numOfStep = numOfStep;
	}

	/**
	 * constructor with only recipeId (for reading records from MySQL with recipeId)
	 * @param recipeId
	 */
	public Recipes(int recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * constructor without recipeId
	 * @param recipeName
	 * @param user
	 * @param timeToCook
	 * @param numOfStep
	 */
	public Recipes(String recipeName, Users user, int timeToCook, int numOfStep) {
		this.recipeName = recipeName;
		this.user = user;
		this.timeToCook = timeToCook;
		this.numOfStep = numOfStep;
	}

	/**
	 * @return the recipeId
	 */
	public int getRecipeId() {
		return recipeId;
	}

	/**
	 * @param recipeId the recipeId to set
	 */
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * @return the recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * @param recipeName the recipeName to set
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * @return the timeToCook
	 */
	public int getTimeToCook() {
		return timeToCook;
	}

	/**
	 * @param timeToCook the timeToCook to set
	 */
	public void setTimeToCook(int timeToCook) {
		this.timeToCook = timeToCook;
	}

	/**
	 * @return the numOfStep
	 */
	public int getNumOfStep() {
		return numOfStep;
	}

	/**
	 * @param numOfStep the numOfStep to set
	 */
	public void setNumOfStep(int numOfStep) {
		this.numOfStep = numOfStep;
	}
	
	
}
