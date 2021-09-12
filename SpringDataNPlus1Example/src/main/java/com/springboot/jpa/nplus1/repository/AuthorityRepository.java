package com.springboot.jpa.nplus1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.nplus1.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
