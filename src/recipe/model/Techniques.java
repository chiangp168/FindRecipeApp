package recipe.model;

/**
 * The type Techniques.
 */
public class Techniques {
  private Integer techniqueId;
  private Recipes recipe;
  private String description;

  /**
   * Instantiates a new Techniques.
   *
   * @param techniqueId the technique id
   * @param recipe      the recipe
   * @param description the description
   */
  public Techniques(Integer techniqueId, Recipes recipe, String description) {
    this.techniqueId = techniqueId;
    this.recipe = recipe;
    this.description = description;
  }

  /**
   * Instantiates a new Techniques.
   *
   * @param recipe      the recipe
   * @param description the description
   */
  public Techniques(Recipes recipe, String description) {
    this.recipe = recipe;
    this.description = description;
  }

  /**
   * Gets technique id.
   *
   * @return the technique id
   */
  public Integer getTechniqueId() {
    return techniqueId;
  }

  /**
   * Sets technique id.
   *
   * @param techniqueId the technique id
   */
  public void setTechniqueId(Integer techniqueId) {
    this.techniqueId = techniqueId;
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
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Techniques{" +
        "techniqueId=" + techniqueId +
        ", recipe=" + recipe +
        ", description='" + description + '\'' +
        '}';
  }
}
