package recipe.model;

import recipe.model.Users;
import recipe.model.Ratings;

public class Favorites {
	protected int favoriteId;
	protected Users user;
	protected Recipes recipe;
	
	public Favorites(int favoriteId,Users user,Recipes recipe) {
		this.favoriteId = favoriteId;
		this.user = user;
		this.recipe = recipe;
	}
	
	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}	

}
