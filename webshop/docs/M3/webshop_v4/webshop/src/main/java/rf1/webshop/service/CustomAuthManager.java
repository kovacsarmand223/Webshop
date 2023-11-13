//package rf1.webshop.service;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthManager implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // Implement your authentication logic here, e.g., check credentials against the database
//        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
