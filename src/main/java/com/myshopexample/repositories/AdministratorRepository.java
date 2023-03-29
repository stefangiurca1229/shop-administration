package com.myshopexample.repositories;

import com.myshopexample.model.admin.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
    Administrator findByName(String adminName);
}
