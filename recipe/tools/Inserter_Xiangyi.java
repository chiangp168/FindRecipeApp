package recipe.tools;

import java.sql.SQLException;
import java.util.List;

import recipe.dal.FavoritesDao;
import recipe.dal.RatingsDao;
import recipe.dal.RecipesDao;
import recipe.dal.UsersDao;
import recipe.model.Favorites;
import recipe.model.Recipes;
import recipe.model.Users;
import recipe.model.Ratings;

public class Inserter_Xiangyi {
	public static void main(String[] args) throws SQLException {	
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
		
		//Xiangyi's Data
		//DAO instances
		FavoritesDao favoriteDao = FavoritesDao.getInstance();
		RatingsDao ratingDao = RatingsDao.getInstance();
		
		System.out.println("**************** Favorite Data ****************");
		//create
		Favorites favorite1 = new Favorites(user1,r2);
		favorite1 = favoriteDao.create(favorite1);
		Favorites favorite2 = new Favorites(user2,r2);
		favorite2 = favoriteDao.create(favorite2);
		Favorites favorite3 = new Favorites(user1,r2);
		favorite3 = favoriteDao.create(favorite3);
		Favorites favorite4 = new Favorites(user1,r3);
		favorite4 = favoriteDao.create(favorite4);
		//read
		Favorites favorite = favoriteDao.getFavoriteById(1);
		List<Favorites> favoriteList1 = favoriteDao.getFavoriteByRecipeId(2);
		for(Favorites f: favoriteList1) {
			System.out.println("favoriteList1 Id is " + f.getFavoriteId());
		}
		List<Favorites> favoriteList2 = favoriteDao.getFavoriteByUserId(2);
		for(Favorites f: favoriteList2) {
			System.out.println("favoriteList2 Id is  " + f.getFavoriteId());
		}
		//delete
		Favorites f = favoriteDao.delete(favorite4);
		System.out.println("after deletion, favorite4 is: " + f);
		
		System.out.println("**************** Rating Data ****************");
		//create
		Ratings rating1 = new Ratings(3,user2,r3);
		rating1 = ratingDao.create(rating1);
		Ratings rating2 = new Ratings(4,user1,r3);
		rating2 = ratingDao.create(rating2);
		Ratings rating3 = new Ratings(5,user2,r2);
		rating3 = ratingDao.create(rating3);
		Ratings rating4 = new Ratings(4,user2,r3);
		rating4 = ratingDao.create(rating4);
		//read
		Ratings rating = ratingDao.getRatingById(1);
		System.out.println("ratingId is " + rating.getRatingId());
		List<Ratings> ratingList1 = ratingDao.getRatingsByRatingPoints(4);
		for(Ratings ratingRead1: ratingList1) {
			System.out.println("RatingList1 Id is " + ratingRead1.getRatingId());
		}
		List<Ratings> ratingList2 = ratingDao.getRatingsByUserId(2);
		for(Ratings ratingRead2: ratingList2) {
			System.out.println("RatingList2 ID is " + ratingRead2.getRatingId());
		}
		//update
		Ratings ratingUpdate = ratingDao.updateByRatingPoints(rating1, 5);
		System.out.println("Update Rating is " + ratingUpdate.getRatingPoints());
		//delete
		Ratings ratingToDelete = ratingDao.delete(rating2);
		System.out.println("After deletion, rating2 is " + ratingToDelete);
	}
}
