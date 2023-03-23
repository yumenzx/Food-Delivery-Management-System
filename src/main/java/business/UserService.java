package business;

import data.Serializator;
import presentation.User;
import presentation.administratorGUI.Administrator;
import presentation.clientGUI.Client;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * clasa principala pentru administrarea utilizatorilor
 */
@SuppressWarnings("unchecked")
public class UserService {

    private static final HashMap<String, User> userList;

    static {
        userList = (HashMap<String, User>) Serializator.deserialize(Serializator.USERS);
    }

    /**
     * metoda de inregistrare a unui nou utilizator
     * @param u utilizatorul propriu zis
     * @return true daca utilizatorul este aduagat cu succes sau false daca un alt utilizaotr cu acelasi nume este inregistrat
     */
    public static boolean registerUser(User u) {
        if (userList.get(u.getUsername()) == null) {
            userList.put(u.getUsername(), u);
            Serializator.serialize(userList, Serializator.USERS);
            return true;
        }
        return false;
    }

    /**
     * metoda de cautare a unui user in
     * @param u userul supus cauatarii
     * @return -1-> parola gresita, 0-> nu exista, 1-> admin, 2-> angajat, 3-> client
     */
    public static int findUser(User u) {
        User user = userList.get(u.getUsername());
        if(user == null)
            return 0;
        if (user.getPassword().equals(u.getPassword())) {
            if (user instanceof Administrator)
                return 1;
            else if (user instanceof Client)
                return 3;
            return 2;
        }
        return -1;
    }

    /**
     * returneaza utilizatorul cu id-ul dat ca parametru
     * @param id id-ul utilizatorului
     * @return utilizatorul propriu zis
     */
    public static User findById(int id) {
        return userList.values().stream().filter(u -> u.getId() == id).collect(Collectors.toList()).get(0);
    }

}
