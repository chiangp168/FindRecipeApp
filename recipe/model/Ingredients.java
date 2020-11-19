package recipe.model;

/**
 * The type Ingredients.
 */
public class Ingredients {
  private Integer ingredientId;
  private Recipes recipe;
  private String ingredient;

  /**
   * Instantiates a new Ingredients.
   *
   * @param ingredientId the ingredient id
   * @param recipe       the recipe
   * @param ingredient   the ingredient
   */
  public Ingredients(Integer ingredientId, Recipes recipe, String ingredient) {
    this.ingredientId = ingredientId;
    this.recipe = recipe;
    this.ingredient = ingredient;
  }

  /**
   * Instantiates a new Ingredients.
   *
   * @param recipe     the recipe
   * @param ingredient the ingredient
   */
  public Ingredients(Recipes recipe, String ingredient) {
    this.recipe = recipe;
    this.ingredient = ingredient;
  }

  /**
   * Instantiates a new Ingredients.
   *
   * @param ingredientId the ingredient id
   */
  public Ingredients(Integer ingredientId) {
    this.ingredientId = ingredientId;
  }

  /**
   * Gets ingredient id.
   *
   * @return the ingredient id
   */
  public Integer getIngredientId() {
    return ingredientId;
  }

  /**
   * Sets ingredient id.
   *
   * @param ingredientId the ingredient id
   */
  public void setIngredientId(Integer ingredientId) {
    this.ingredientId = ingredientId;
  }

  /**
   * Gets recipe.
   *
   * @return the recipe
   */
  public Recipes getRecipe() {
    return recipe;
  }

  /**
   * Sets recipe.
   *
   * @param recipe the recipe
   */
  public void setRecipe(Recipes recipe) {
    this.recipe = recipe;
  }

  /**
   * Gets ingredient.
   *
   * @return the ingredient
   */
  public String getIngredient() {
    return ingredient;
  }

  /**
   * Sets ingredient.
   *
   * @param ingredient the ingredient
   */
  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  @Override
  public String toString() {
    return "Ingredients{" +
        "ingredientId=" + ingredientId +
        ", recipe=" + recipe +
        ", ingredient='" + ingredient + '\'' +
        '}';
  }
}
