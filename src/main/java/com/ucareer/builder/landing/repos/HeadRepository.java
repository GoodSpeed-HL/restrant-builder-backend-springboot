package com.ucarrer.builder.landing.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HeadRepository extends JpaRepository<Head, Long> {

}