package com.findme_spring_boot.h2.dao;

import com.findme_spring_boot.h2.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextDao extends JpaRepository<Text, Long> {
    //Text findById(Long id);
}
