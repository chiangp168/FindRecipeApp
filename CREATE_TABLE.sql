CREATE SCHEMA IF NOT EXISTS RecipeApplication;
USE RecipeApplication;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Ratings;
DROP TABLE IF EXISTS Favorites;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Techniques;
DROP TABLE IF EXISTS NutritionFacts;
DROP TABLE IF EXISTS Recipes;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Moderators;
DROP TABLE IF EXISTS Person;
CREATE TABLE Person (
  UserId INT,
  UserName VARCHAR(255),
  UserPassword VARCHAR(255),
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  Email VARCHAR(255),
  Phone VARCHAR(255),
  CONSTRAINT pk_Person_UserId PRIMARY KEY (UserId)
);
CREATE TABLE Users (
  UserId INT,
  CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId),
  CONSTRAINT fk_Users_UserId FOREIGN KEY (UserId) REFERENCES Person(UserId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Moderators (
  UserId INT,
  NumOfRecipesDeleted INT,
  NumOfRecipesApproved INT,
  CONSTRAINT pk_Moderators_UserId PRIMARY KEY (UserId),
  CONSTRAINT fk_Moderators_UserId FOREIGN KEY (UserId) REFERENCES Person(UserId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Recipes (
  RecipeName VARCHAR(255),
  RecipeId INT AUTO_INCREMENT,
  UserId INT,
  TimeToCook INT,
  NumOfStep INT,
  CONSTRAINT pk_Recipes_RecipeId PRIMARY KEY (RecipeId),
  CONSTRAINT fk_Recipes_UserId FOREIGN KEY (UserId) REFERENCES Person(UserId) ON UPDATE CASCADE ON DELETE
  SET
    NULL
);
CREATE TABLE Ratings(
  RatingId INT AUTO_INCREMENT,
  UserId INT,
  RecipeId INT,
  RatingPoints INT,
  CONSTRAINT pk_Ratings_RatingId PRIMARY KEY (RatingId),
  CONSTRAINT fk_Ratings_UserId FOREIGN KEY (UserId) REFERENCES Person(UserId) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Ratings_RecipeId FOREIGN KEY (RecipeId) REFERENCES Recipes(RecipeId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Favorites(
  FavoriteId INT AUTO_INCREMENT,
  UserId INT,
  RecipeId INT,
  CONSTRAINT pk_Favorites_FavoriteID PRIMARY KEY (FavoriteId),
  CONSTRAINT fk_Favorites_UserId Foreign KEY (UserId) References Person(UserId) On UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Favorites_RecipeId FOREIGN KEY (RecipeId) REFERENCES Recipes(RecipeId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Ingredients (
  IngredientId INT AUTO_INCREMENT,
  RecipeId INT,
  Ingredient VARCHAR(255),
  CONSTRAINT pk_Ingredients_IngredientId PRIMARY KEY (IngredientId),
  CONSTRAINT fk_Ingredients_RecipeId FOREIGN KEY (RecipeId) REFERENCES Recipes(RecipeId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Comments (
  CommentId INT AUTO_INCREMENT,
  UserId INT,
  RecipeId INT,
  ShortComment LONGTEXT,
  Created DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_Comments_CommentId PRIMARY KEY (CommentId),
  CONSTRAINT fk_Comments_UserId FOREIGN KEY (UserId) REFERENCES Person(UserId) ON UPDATE CASCADE ON DELETE
  SET
    NULL,
    CONSTRAINT fk_Comments_RecipeId FOREIGN KEY (RecipeId) REFERENCES Recipes(RecipeId) ON UPDATE CASCADE ON DELETE
  SET
    NULL
);
CREATE TABLE Techniques (
  TechniqueId INT AUTO_INCREMENT,
  Description VARCHAR(256),
  RecipeId INT,
  CONSTRAINT pk_Techniques_TechniqueId PRIMARY KEY (TechniqueId),
  CONSTRAINT fk_Techniques_RecipeId FOREIGN KEY (RecipeId) REFERENCES Recipes(RecipeId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE NutritionFacts (
  NutritionFactsId INT AUTO_INCREMENT,
  Calories DECIMAL(10, 1),
  Total_fat INT,
  Sugar INT,
  Sodium INT,
  Protein INT,
  Saturated_fat INT,
  Carb INT,
  RecipeId INT,
  CONSTRAINT pk_NutritionFacts_NutritionFactsId PRIMARY KEY (NutritionFactsId),
  CONSTRAINT fk_NutritionFacts_RecipeId FOREIGN KEY (RecipeId) REFERENCES Recipes(RecipeId) ON UPDATE CASCADE ON DELETE CASCADE
);