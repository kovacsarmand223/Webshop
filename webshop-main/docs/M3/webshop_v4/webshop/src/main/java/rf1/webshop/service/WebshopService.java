package rf1.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rf1.webshop.dao.UserDAO;
import rf1.webshop.model.UserModel;


@Service
public class WebshopService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userDAO.getUserByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserModel(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.isPermission());
    }
}