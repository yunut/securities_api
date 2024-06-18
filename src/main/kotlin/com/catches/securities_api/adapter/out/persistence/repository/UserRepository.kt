package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}