package recipe.tools;

import java.sql.SQLException;

import recipe.dal.*;
import recipe.model.*;

public class Inserter_Emily {
    public static void main(String[] args) throws SQLException {
        PersonDao personDao = PersonDao.getInstance();
        UsersDao usersDao = UsersDao.getInstance();

        //----------Insert Operator------------
        //Person
        Person person = new Person(99999, "Person1", "password1", "PersonFirst1", "PersonLast1", "person1@gmail.com", "123456789");
        personDao.create(person);
        Person person2 = new Person(88888, "Person2", "password2", "PersonFirst2", "PersonLast2", "person2@gmail.com", "223456789");
        personDao.create(person2);

        //User
        Users user = new Users(777777, "User1", "userpassword1", "UserFirst1", "UserLast1", "user1@gmail.com", "223456788");
        usersDao.create(user);
        Users user2 = new Users(666666, "User2", "userpassword2", "UserFirst2", "UserLast2", "user2@gmail.com", "223456777");
        usersDao.create(user2);
        

        //----------Read Operator------------
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

        //---------Update Operator-----------
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

        //---------Delete Operator-----------
        Person personDeleted = personDao.delete(person2);
        System.out.println("Deleted Person 2: " + personDeleted);
        Users userDeleted = usersDao.delete(user2);
        System.out.println("Deleted User 2: " + userDeleted);

    }
    
}
