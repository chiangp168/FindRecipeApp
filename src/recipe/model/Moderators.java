package recipe.model;

public class Moderators extends Person {

	protected int numOfRecipesDeleted;
	protected int numOfRecipesApproved;
	

	public Moderators(int userId, String userName, String password, String firstName, String lastName, String email,
            String phone, int numOfRecipesDeleted, int numOfRecipesApproved) {
        super(userId, userName, password, firstName, lastName, email, phone);
        this.numOfRecipesDeleted = numOfRecipesDeleted;
        this.numOfRecipesApproved = numOfRecipesApproved;
    }

    public Moderators(int userId, int numOfRecipesDeleted, int numOfRecipesApproved) {
        super(userId);
        this.numOfRecipesDeleted = numOfRecipesDeleted;
        this.numOfRecipesApproved = numOfRecipesApproved;
    }    
    
	public int getNumOfRecipesDeleted() {
		return numOfRecipesDeleted;
	}

	public void setNumOfRecipesDeleted(int numOfRecipesDeleted) {
		this.numOfRecipesDeleted = numOfRecipesDeleted;
	}

	public int getNumOfRecipesApproved() {
		return numOfRecipesApproved;
	}

	public void setNumOfRecipesApproved(int numOfRecipesApproved) {
		this.numOfRecipesApproved = numOfRecipesApproved;
	}
}

