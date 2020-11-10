package recipe.model;

import recipe.model.Users;
import recipe.model.Recipes;

public class Ratings {
	protected int ratingId;
	protected int ratingPoints;
	protected Users user;
	protected Recipes recipe;
	
	public Ratings(int ratingId,int ratingPoints,Users user, Recipes recipe) {
		this.ratingId = ratingId;
		this.ratingPoints = ratingPoints;
		this.user = user;
		this.recipe = recipe;
		
	}
	
	public Ratings(int ratingId) {
		this.ratingId = ratingId;
		
	}
	
	public Ratings(int ratingPoints,Users user, Recipes recipe) {
		this.ratingPoints = ratingPoints;
		this.user = user;
		this.recipe = recipe;
		
	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}
	
	public int getRatingPoints() {
		return ratingPoints;
	}

	public void setRatingPoints(int ratingPoints) {
		this.ratingPoints = ratingPoints;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}	
	
	public Recipes getRecipes() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}	

}
