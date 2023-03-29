package com.myshopexample.service.security;
import com.myshopexample.model.admin.Administrator;
import com.myshopexample.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PrincipalProvider {
    @Autowired
    private AdministratorRepository administratorRepository;
    public Administrator getPrincipalProvider(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return administratorRepository.findByName(username);
    }
}