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
