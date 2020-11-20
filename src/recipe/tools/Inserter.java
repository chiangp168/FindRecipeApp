package recipe.tools;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import recipe.dal.*;
import recipe.model.*;

public class Inserter {
	public static void main(String[] args) throws SQLException {
        PersonDao personDao = PersonDao.getInstance();
        UsersDao usersDao = UsersDao.getInstance();
        RecipesDao recipesDao = RecipesDao.getInstance();
        CommentsDao commentsDao = CommentsDao.getInstance();
        TechniquesDao techniquesDao = TechniquesDao.getInstance();
		IngredientsDao ingredientsDao = IngredientsDao.getInstance();
		ModeratorsDao moderatorsDao = ModeratorsDao.getInstance();
		NutritionFactsDao nutritionFactsDao = NutritionFactsDao.getInstance();
		FavoritesDao favoriteDao = FavoritesDao.getInstance();
		RatingsDao ratingDao = RatingsDao.getInstance();
		
//		
		//----------Insert Operator------------
        //Person
        Person person = new Person(99999, "Person1", "password1", "PersonFirst1", "PersonLast1", "person1@gmail.com", "123456789");
        personDao.create(person);
        System.out.printf("create new person with id: %d \n", person.getUserId());
        Person person2 = new Person(88888, "Person2", "password2", "PersonFirst2", "PersonLast2", "person2@gmail.com", "223456789");
        personDao.create(person2);
        System.out.printf("create new person with id: %d \n", person2.getUserId());
//
//        //User
        Users user = new Users(777777, "User1", "userpassword1", "UserFirst1", "UserLast1", "user1@gmail.com", "223456788");
        usersDao.create(user);
        System.out.printf("create new user with id: %d \n", user.getUserId());
        Users user2 = new Users(666666, "User2", "userpassword2", "UserFirst2", "UserLast2", "user2@gmail.com", "223456777");
        usersDao.create(user2);
        System.out.printf("create new user with id: %d \n", user2.getUserId());
//        
// 
//        
//        
        // Techniques/ Ingredients
        Users user10 = new Users(11000, "username10", "password1", "firstName1", "lastName1", "email1",
	            "phone1");
		user10 = usersDao.create(user10);
		
		Recipes r10 = new Recipes(100, "recipeName1", user10, 15, 3);
		r10 = recipesDao.create(r10);
		Ingredients ig1 = new Ingredients(r10,"testIngredient");
		Ingredients igTest = ingredientsDao.create(ig1);
		System.out.printf("create new ingredient with id: %d \n", ig1.getIngredientId());
		Techniques tq1 = new Techniques(r10,"testTechnique");
		Techniques tqTest = techniquesDao.create(tq1);
		System.out.printf("create new technique with id: %d \n", tq1.getTechniqueId());
		
		
		 //Comments
        Comments newCommet = new Comments(11000, 1, "new comment", LocalDateTime.now());
        commentsDao.create(newCommet);
        System.out.printf("create new comment with id: %d \n", newCommet.getCommentId());
        System.out.printf("get new comment with content: %s \n", commentsDao.getCommentById(newCommet.getCommentId()).getContent());
//		
		// Moderators and NutritionFact
		Moderators moderator = new Moderators(5437, "nancy_bloom", "password", "Nancy", "Bloom", "nbloom@gmail.com", "3432422", 4, 22);
		moderator = moderatorsDao.create(moderator);
		System.out.printf("create new moderator with id: %d \n", moderator.getUserId());
		Moderators moderator1 = new Moderators(543, "felix_harmony", "password", "Felix", "Harmony", "fharmony@gmail.com", "321769", 41, 22);
		moderator1 = moderatorsDao.create(moderator1);
		System.out.printf("create new moderator with id: %d \n", moderator1.getUserId());
		Moderators moderator2 = new Moderators(22, "ferg_jauz", "password", "Ferg", "Jauz", "fjauz@gmail.com", "3242312", 47, 4);
		moderator2 = moderatorsDao.create(moderator2);
		System.out.printf("create new moderator with id: %d \n", moderator2.getUserId());
		
		Users user11 = new Users(1, "username1", "password1", "firstName1", "lastName1", "email1",
	            "phone1");
		user11 = usersDao.create(user11);
		
		Users user21 = new Users(2, "username2", "password2", "firstName2", "lastName2", "email2",
	            "phone2");
		user2 = usersDao.create(user21);
//		
//		
//		// Recipes
//		
		Recipes r1 = new Recipes("recipeName1", user11, 15, 3);
		r1 = recipesDao.create(r1);
		System.out.printf("create new recipe with id: %d \n", r1.getRecipeId());
		
		Recipes r2 = new Recipes("recipeName2", user11, 20, 4);
		r2 = recipesDao.create(r2);
		
		Recipes r3 = new Recipes("recipeName3", user21, 15, 4);
		r3 = recipesDao.create(r3);
		
		Recipes r4 = new Recipes("recipeName4", user21, 20, 3);
		r4 = recipesDao.create(r4);
//		

		NutritionFacts nutritionFact = new NutritionFacts(432454, 452.00, 12, 3,5, 34,1,4, r1);
		nutritionFact = nutritionFactsDao.create(nutritionFact);
		System.out.printf("create new nutritionFact with id: %d \n", nutritionFact.getNutritionFactsId());
		NutritionFacts nutritionFact1 = new NutritionFacts(5435, 321.00, 45, 2, 52, 34,1,4, r1);
		nutritionFact1 = nutritionFactsDao.create(nutritionFact1);
		System.out.printf("create new nutritionFact with id: %d \n", nutritionFact1.getNutritionFactsId());
		NutritionFacts nutritionFact2 = new NutritionFacts(52335, 321.00, 45, 2, 52, 34,1,23, r1);
		nutritionFact2 = nutritionFactsDao.create(nutritionFact2);
		System.out.printf("create new nutritionFact with id: %d \n", nutritionFact2.getNutritionFactsId());
		
		Favorites favorite1 = new Favorites(user11,r2);
		favorite1 = favoriteDao.create(favorite1);
		System.out.printf("create new favorite with id: %d \n", favorite1.getFavoriteId());
		Favorites favorite2 = new Favorites(user2,r2);
		favorite2 = favoriteDao.create(favorite2);
		System.out.printf("create new favorite with id: %d \n", favorite2.getFavoriteId());
		Favorites favorite3 = new Favorites(user11,r2);
		favorite3 = favoriteDao.create(favorite3);
		System.out.printf("create new favorite with id: %d \n", favorite3.getFavoriteId());
		Favorites favorite4 = new Favorites(user11,r3);
		favorite4 = favoriteDao.create(favorite4);
		System.out.printf("create new favorite with id: %d \n", favorite4.getFavoriteId());
		
		Ratings rating1 = new Ratings(3,user2,r3);
		rating1 = ratingDao.create(rating1);
		System.out.printf("create new rating with id: %d \n", rating1.getRatingId());
		Ratings rating2 = new Ratings(4,user11,r3);
		rating2 = ratingDao.create(rating2);
		System.out.printf("create new rating with id: %d \n", rating2.getRatingId());
		Ratings rating3 = new Ratings(5,user2,r2);
		rating3 = ratingDao.create(rating3);
		Ratings rating4 = new Ratings(4,user2,r3);
		rating4 = ratingDao.create(rating4);
		
		
		//Read
		
		 //Person
        Person personFirst = personDao.getPersonByUserId(99999);
		System.out.format("Reading person1: id:%s username: %s p:%s f:%s l:%s e:%s p:%s \n",
            personFirst.getUserId(), personFirst.getUserName(), personFirst.getPassword(), personFirst.getFirstName(),
            personFirst.getLastName(), personFirst.getEmail(), personFirst.getPhone());

        Person personSecond = personDao.getPersonByUserId(88888);
        System.out.format("Reading person2: id:%s username: %s p:%s f:%s l:%s e:%s p:%s \n",
            personSecond.getUserId(), personSecond.getUserName(), personSecond.getPassword(), personSecond.getFirstName(),
            personSecond.getLastName(), personSecond.getEmail(), personSecond.getPhone());
        
        //User
        Users userFirst = usersDao.getUsersByUserId(777777);
		System.out.format("Reading user1: id:%s username: %s p:%s f:%s l:%s e:%s p:%s \n",
                userFirst.getUserId(), userFirst.getUserName(), userFirst.getPassword(), userFirst.getFirstName(),
                userFirst.getLastName(), userFirst.getEmail(), userFirst.getPhone());
        Users userSecond = usersDao.getUsersByUserId(666666);
		System.out.format("Reading user2: id:%s username: %s p:%s f:%s l:%s e:%s p:%s \n",
                userSecond.getUserId(), userSecond.getUserName(), userSecond.getPassword(), userSecond.getFirstName(),
                userSecond.getLastName(), userSecond.getEmail(), userSecond.getPhone());
		
		//Favorite
		Favorites favorite = favoriteDao.getFavoriteById(1);
		List<Favorites> favoriteList1 = favoriteDao.getFavoriteByRecipeId(2);
		for(Favorites f: favoriteList1) {
			System.out.println("favoriteList1 Id is " + f.getFavoriteId());
		}
		List<Favorites> favoriteList2 = favoriteDao.getFavoriteByUserId(2);
		for(Favorites f: favoriteList2) {
			System.out.println("favoriteList2 Id is  " + f.getFavoriteId());
		}
		Ratings rating = ratingDao.getRatingById(1);
		System.out.println("ratingId is " + rating.getRatingId());
		List<Ratings> ratingList1 = ratingDao.getRatingsByRatingPoints(4);
		for(Ratings ratingRead1: ratingList1) {
			System.out.println("RatingList1 Id is " + ratingRead1.getRatingId());
		}
		List<Ratings> ratingList2 = ratingDao.getRatingsByUserId(2);
		for(Ratings ratingRead2: ratingList2) {
			System.out.println("RatingList2 ID is " + ratingRead2.getRatingId());
		
		
			
		//Comments
		

		    // select by users id
		System.out.println("___________");
		System.out.printf("select by users id: 27\n");
		List<Comments> comments = commentsDao.getCommentsByUserId(27);
		for (Comments comment : comments) {
			System.out.printf("User: %d, Content: %s \n", comment.getUserId(), comment.getContent());
		}

		// select by recipe id
		System.out.println("___________");
		System.out.printf("select by recipes id: 41\n");
		List<Comments> comments2 = commentsDao.getCommentsByRecipeId(41);
		for (Comments comment : comments2) {
		      System.out.printf("RecipeID: %d, Content: %s \n", comment.getRecipeId(), comment.getContent());
		}
		
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
		
		Moderators mod = moderatorsDao.getModeratorById(5437);
		System.out.println("Read moderator :" +"id is "+ mod.getUserId());
		
		Moderators mod1 = moderatorsDao.getModeratorByUserName("felix_harmony");
		System.out.println("Read moderatetor :" +"username is "+ mod.getUserName());
		
		List<Moderators> modList = moderatorsDao.getModeratorByNumDeleted(4);
		System.out.println("reading moderators list:");
		for(Moderators mi:modList) {
			System.out.println("UserId is "+ mi.getUserId());
		}
		
		NutritionFacts nu = nutritionFactsDao.getNutritionFactById(1);
		System.out.println("Read nutrition fact :" +"nutritionId is "+ nu.getNutritionFactsId());
		
		NutritionFacts nuRecipe = nutritionFactsDao.getNutritionFactByRecipeId(2);
		System.out.println("Read nutrition fact :" +"recipe Id is "+ nuRecipe.getRecipe().getRecipeId());
		List<NutritionFacts> nuFacts = nutritionFactsDao.getNutritionFactsByProtein(34);
		System.out.println("reading nutrition fact list:");
		for(NutritionFacts nuf: nuFacts) {
			System.out.println("UserId is "+ nuf.getNutritionFactsId());
		}
		
		Recipes recipe = recipesDao.getRecipeById(2);
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
		
		//UPDATE
		String newPersonPassword = "newPerson1Password";
        personDao.updatePassword(person, newPersonPassword);
        System.out.format("Updated Person1 Password: id:%s p:%s \n", person.getUserId(), person.getPassword());

        String newPersonLastName = "newPerson1LastName";
        personDao.updateLastName(person, newPersonLastName);
        System.out.format("Updated Person1 LastName: id:%s l:%s \n", person.getUserId(), person.getLastName());

        String newUserPassword = "newUser1Password";
        usersDao.updatePassword(user, newUserPassword);
        System.out.format("Updated User1 Password: id:%s l:%s \n", user.getUserId(), user.getPassword());

        String newUserLastName = "newUser1LastName";
        usersDao.updateLastName(user, newUserLastName);
        System.out.format("Updated User1 LastName: id:%s l:%s \n", user.getUserId(), user.getLastName());

        igTest = ingredientsDao.updateIngredient(igTest,"testIngredient1");
		System.out.println("Read: new ingredient is: " + igTest.getIngredient());
		tqTest = techniquesDao.updateTechniqueDescription(tqTest,"descriptionUpdated");
		System.out.println("Read: new description is: " + tqTest.getDescription());
		
		Moderators rMod = moderatorsDao.addRecipesDeleted(moderator2, 1);
		System.out.println("Read: new number of recipes deleted for " + rMod.getUserName() + "is " + rMod.getNumOfRecipesDeleted());
		Moderators rMod1 = moderatorsDao.subtractRecipesDeleted(moderator1, 5);
		System.out.println("Read: new number of recipes deleted for " + rMod1.getUserName() + "is " + rMod1.getNumOfRecipesDeleted());
		
		NutritionFacts nuUpdate = nutritionFactsDao.updateNutritionFact(nutritionFact2, 214, 55, 3, 2, 5, 3, 4, r1);
		System.out.println("Read: new calories for "+ nuUpdate.getNutritionFactsId() + "is " + nuUpdate.getCalories());
		
		Recipes rnew = recipesDao.updateByTimeToCook(r1, 30);
		System.out.println("Read: new recipe timeToCook is: " + rnew.getTimeToCook());
		
		Ratings ratingUpdate = ratingDao.updateByRatingPoints(rating1, 5);
		System.out.println("Update Rating is " + ratingUpdate.getRatingPoints());
		
		commentsDao.updateByCommentId(newCommet, "updated");
		System.out.printf("update new comment with new content: %s \n", newCommet.getContent());
		System.out.println("___________");

		// DELETE
		
		Person personDeleted = personDao.delete(person2);
        System.out.println("Deleted Person 2: " + personDeleted);
        Person personDeleted1 = personDao.delete(person);
        System.out.println("Deleted Person : " + personDeleted1);
        Users usersDeleted = usersDao.delete(user);
        System.out.println("Deleted User : " + usersDeleted);
        Recipes r = recipesDao.delete(r10);
		System.out.println("after deletion, r1 is: " + r);
		Favorites f = favoriteDao.delete(favorite1);
		Ingredients igDeleted = ingredientsDao.delete(igTest);
		if (igDeleted==null){
			System.out.println("Ingredient delete successful");
		}
		Techniques tqDeleted = techniquesDao.delete(tqTest);
		if (tqDeleted==null){
			System.out.println("Technique delete successful");
		}
		System.out.println("after deletion, favorite1 is: " + f);
		commentsDao.deleteById(newCommet);
	    System.out.printf("delete by comment id: %s\n", newCommet.getCommentId());
	    Ratings ratingToDelete = ratingDao.delete(rating2);
		System.out.println("After deletion, rating2 is " + ratingToDelete);
	}
		
	}
}
