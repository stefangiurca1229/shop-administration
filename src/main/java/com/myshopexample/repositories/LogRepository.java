package com.myshopexample.repositories;

import com.myshopexample.model.logs.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Logs,Long> {
}
