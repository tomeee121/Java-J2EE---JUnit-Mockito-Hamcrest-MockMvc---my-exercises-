package com.example.test1zaddom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UnitRepH2 extends JpaRepository<Unit, Long> {
}
