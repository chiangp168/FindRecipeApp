package recipe.model;

public class Users extends Person {

    public Users(int userId, String userName, String password, String firstName, String lastName, String email,
            String phone) {
        super(userId, userName, password, firstName, lastName, email, phone);
    }

    public Users(int userId) {
        super(userId);
    }    
}

