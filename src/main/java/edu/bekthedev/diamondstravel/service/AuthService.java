package edu.bekthedev.diamondstravel.service;

import edu.bekthedev.diamondstravel.model.User;
import edu.bekthedev.diamondstravel.model.Users;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AuthService {
    private final String XML_PATH = "src/main/resources/users.xml";

    // read
    public Users readUsers() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Users) unmarshaller.unmarshal(new File(XML_PATH));
    }

    // write
    public void writeUsers(Users users) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(users, new File(XML_PATH));
    }

    //checks username and password
    public boolean authenticate(String username, String password) {
        try {
            Users users = readUsers();
            return users.getUsers().stream()
                    .anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
        } catch (Exception e) {
            return false;
        }
    }

    //add new user
    public boolean register(User newUser) {
        try {
            Users users = readUsers();
            boolean exists = users.getUsers().stream()
                    .anyMatch(u -> u.getUsername().equals(newUser.getUsername()));
            if (exists) return false;

            users.getUsers().add(newUser);
            writeUsers(users);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}