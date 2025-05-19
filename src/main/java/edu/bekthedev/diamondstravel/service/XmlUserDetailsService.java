
package edu.bekthedev.diamondstravel.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Service
public class XmlUserDetailsService implements UserDetailsService {


    //user credentials
    private final String USERS_XML = "src/main/resources/users.xml";

    // Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            File xmlFile = new File(USERS_XML);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            NodeList users = doc.getElementsByTagName("user");

            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);
                String userName = user.getElementsByTagName("username").item(0).getTextContent();
                String password = user.getElementsByTagName("password").item(0).getTextContent();


                if (userName.equals(username)) {
                    return User.withUsername(userName)
                            .password("{noop}" + password)
                            .roles("USER")
                            .build();
                }
            }

        } catch (Exception e) {
            throw new UsernameNotFoundException("Could not load users.xml", e);
        }

        throw new UsernameNotFoundException("User not found");
    }
}