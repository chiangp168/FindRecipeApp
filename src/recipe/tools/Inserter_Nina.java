package recipe.tools;

import java.sql.SQLException;
import java.util.List;

import recipe.dal.*;
import recipe.model.*;

public class Inserter_Nina {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		ModeratorsDao moderatorsDao = ModeratorsDao.getInstance();
		NutritionFactsDao nutritionFactsDao = NutritionFactsDao.getInstance();
		RecipesDao recipesDao = RecipesDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		
		
		// CREATE
		
		Moderators moderator = new Moderators(41, "nancy_bloom", "password", "Nancy", "Bloom", "nbloom@gmail.com", "3432422", 4, 22);
		moderator = moderatorsDao.create(moderator);
		
		Moderators moderator1 = new Moderators(42, "felix_harmony", "password", "Felix", "Harmony", "fharmony@gmail.com", "321769", 41, 22);
		moderator1 = moderatorsDao.create(moderator1);
		
		Moderators moderator2 = new Moderators(42, "ferg_jauz", "password", "Ferg", "Jauz", "fjauz@gmail.com", "3242312", 47, 4);
		moderator2 = moderatorsDao.create(moderator2);
		
		Users user1 = new Users(1, "username1", "password1", "firstName1", "lastName1", "email1",
	            "phone1");
		user1 = usersDao.create(user1);
		Recipes r1 = new Recipes("recipeName1", user1, 15, 3);
		r1 = recipesDao.create(r1);
		

		NutritionFacts nutritionFact = new NutritionFacts(51, 452.00, 12, 3,5, 34,1,4, r1);
		nutritionFact = nutritionFactsDao.create(nutritionFact);
		
		NutritionFacts nutritionFact1 = new NutritionFacts(52, 321.00, 45, 2, 52, 34,1,4, r1);
		nutritionFact1 = nutritionFactsDao.create(nutritionFact1);
		
		NutritionFacts nutritionFact2 = new NutritionFacts(53, 321.00, 45, 2, 52, 34,1,23, r1);
		nutritionFact2 = nutritionFactsDao.create(nutritionFact2);
		
		
		//READ
		Moderators mod = moderatorsDao.getModeratorById(41);
		System.out.println("Read moderator :" +"id is "+ mod.getUserId());
		Moderators m = moderatorsDao.delete(mod);
		System.out.println("After deletion, moderator is: " + mod);
		
		Moderators mod1 = moderatorsDao.getModeratorByUserName("felix_harmony");
		System.out.println("Read moderatetor :" +"username is "+ mod.getUserName());
		
		List<Moderators> modList = moderatorsDao.getModeratorByNumDeleted(4);
		System.out.println("reading moderators list:");
		for(Moderators mi:modList) {
			System.out.println("UserId is "+ mi.getUserId());
		}
		
		NutritionFacts nu = nutritionFactsDao.getNutritionFactById(51);
		System.out.println("Read nutrition fact :" +"nutritionId is "+ nu.getNutritionFactsId());
		
		NutritionFacts nuRecipe = nutritionFactsDao.getNutritionFactByRecipeId(1);
		System.out.println("Read nutrition fact :" +"recipe Id is "+ nuRecipe.getRecipe().getRecipeId());
		List<NutritionFacts> nuFacts = nutritionFactsDao.getNutritionFactsByProtein(0);
		System.out.println("reading nutrition fact list:");
		for(NutritionFacts nuf: nuFacts) {
			System.out.println("UserId is "+ nuf.getNutritionFactsId());
		}
		
		
		//UPDATE
		
		Moderators rMod = moderatorsDao.addRecipesDeleted(moderator2, 1);
		System.out.println("Read: new number of recipes deleted for " + rMod.getUserName() + "is " + rMod.getNumOfRecipesDeleted());
		Moderators rMod1 = moderatorsDao.subtractRecipesDeleted(moderator1, 5);
		System.out.println("Read: new number of recipes deleted for " + rMod1.getUserName() + "is " + rMod1.getNumOfRecipesDeleted());
		
		NutritionFacts nuUpdate = nutritionFactsDao.updateNutritionFact(nutritionFact2, 214, 55, 3, 2, 5, 3, 4, r1);
		System.out.println("Read: new calories for "+ nuUpdate.getNutritionFactsId() + "is " + nuUpdate.getCalories());
		
		//DELETE
		Moderators moderatorD = moderatorsDao.delete(moderator);
		System.out.println("After deletion, moderator is: " + moderatorD);
		NutritionFacts n = nutritionFactsDao.delete(nutritionFact2);
		System.out.println("After deletion, nutrition fact is: " + n);
	}
}
