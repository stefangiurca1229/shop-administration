package com.myshopexample.security.service;

import com.myshopexample.model.admin.Administrator;
import com.myshopexample.repositories.AdministratorRepository;
import com.myshopexample.security.model.MyCustomerPrincipal;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByName(adminName);
        if(administrator == null){
            throw new UsernameNotFoundException("customer not found!");
        }
        return new MyCustomerPrincipal(administrator);
    }
}