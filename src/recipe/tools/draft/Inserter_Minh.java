package recipe.tools;

import java.sql.SQLException;
import java.util.List;
import recipe.dal.IngredientsDao;
import recipe.dal.RecipesDao;
import recipe.dal.TechniquesDao;
import recipe.dal.UsersDao;
import recipe.model.Ingredients;
import recipe.model.Recipes;
import recipe.model.Techniques;
import recipe.model.Users;


public class Inserter_Minh {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		RecipesDao recipesDao = RecipesDao.getInstance();
		TechniquesDao techniquesDao = TechniquesDao.getInstance();
		IngredientsDao ingredientsDao = IngredientsDao.getInstance();
		
		// create.
		Users user10 = new Users(11000, "username10", "password1", "firstName1", "lastName1", "email1",
	            "phone1");
		user10 = usersDao.create(user10);
		
		Recipes r10 = new Recipes("recipeName1", user10, 15, 3);
		r10 = recipesDao.create(r10);
		Ingredients ig1 = new Ingredients(r10,"testIngredient");
		Ingredients igTest = ingredientsDao.create(ig1);
		Techniques tq1 = new Techniques(r10,"testTechnique");
		Techniques tqTest = techniquesDao.create(tq1);
		
		//read
		Ingredients ig2 = ingredientsDao.getIngredientsById(igTest.getIngredientId());
		System.out.println(ig2);
		List<Ingredients> ingredientList = ingredientsDao.getIngredientsByRecipe(r10);
		System.out.println("reading ingredient list:");
		for(Ingredients ig: ingredientList) {
			System.out.println("Ingredient: "+ ig.getIngredient());
		}
		Techniques tq2 = techniquesDao.getTechniquesById(tqTest.getTechniqueId());
		System.out.println(tq2);
		List<Techniques> TechniquesList = techniquesDao.getTechniquesByRecipe(r10);
		System.out.println("reading Techniques list:");
		for(Techniques tq: TechniquesList) {
			System.out.println("Technique: "+ tq.getDescription());
		}
		
		//update
		igTest = ingredientsDao.updateIngredient(igTest,"testIngredient1");
		System.out.println("Read: new ingredient is: " + igTest.getIngredient());
		tqTest = techniquesDao.updateTechniqueDescription(tqTest,"descriptionUpdated");
		System.out.println("Read: new description is: " + tqTest.getDescription());

		
		//delete
		Ingredients igDeleted = ingredientsDao.delete(igTest);
		if (igDeleted==null){
			System.out.println("Ingredient delete successful");
		}
		Techniques tqDeleted = techniquesDao.delete(tqTest);
		if (tqDeleted==null){
			System.out.println("Technique delete successful");
		}

		
	}
	
}
