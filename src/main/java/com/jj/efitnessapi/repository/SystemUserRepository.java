package com.jj.efitnessapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jj.efitnessapi.model.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

	Optional<SystemUser> findByLogin(String login);
}
