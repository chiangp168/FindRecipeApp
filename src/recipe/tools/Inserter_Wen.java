package recipe.tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import recipe.dal.*;
import recipe.model.*;


public class Inserter_Wen {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		RecipesDao recipesDao = RecipesDao.getInstance();
		
		// create.
		Users user1 = new Users(1, "username1", "password1", "firstName1", "lastName1", "email1",
	            "phone1");
		user1 = usersDao.create(user1);
		
		Users user2 = new Users(2, "username2", "password2", "firstName2", "lastName2", "email2",
	            "phone2");
		user2 = usersDao.create(user2);
		
		Recipes r1 = new Recipes("recipeName1", user1, 15, 3);
		r1 = recipesDao.create(r1);
		
		Recipes r2 = new Recipes("recipeName2", user1, 20, 4);
		r2 = recipesDao.create(r2);
		
		Recipes r3 = new Recipes("recipeName3", user2, 15, 4);
		r3 = recipesDao.create(r3);
		
		Recipes r4 = new Recipes("recipeName4", user2, 20, 3);
		r4 = recipesDao.create(r4);
		
		//read
		Recipes recipe = recipesDao.getRecipeById(1);
		System.out.println("Read recipe:" +"recipeId is "+recipe.getRecipeId());
		
		List<Recipes> recipelist1 = recipesDao.getRecipesByRecipeName("recipeName1");
		System.out.println("reading recipe list:");
		for(Recipes r:recipelist1) {
			System.out.println("recipeId is "+r.getRecipeId());
		}
		
		List<Recipes> recipelist2 = recipesDao.getRecipesByUserName("username1");
		System.out.println("reading recipe list:");
		for(Recipes r:recipelist2) {
			System.out.println("recipeId is "+r.getRecipeId());
		}
		
		List<Recipes> recipelist3 = recipesDao.getRecipesByUserId(2);
		System.out.println("reading recipe list:");
		for(Recipes r:recipelist3) {
			System.out.println("recipeId is "+r.getRecipeId());
		}
		
		//update
		Recipes rnew = recipesDao.updateByTimeToCook(r1, 30);
		System.out.println("Read: new recipe timeToCook is: " + rnew.getTimeToCook());
		
		//delete
		Recipes r = recipesDao.delete(r1);
		System.out.println("after deletion, r1 is: " + r);
		
	}
	
}
