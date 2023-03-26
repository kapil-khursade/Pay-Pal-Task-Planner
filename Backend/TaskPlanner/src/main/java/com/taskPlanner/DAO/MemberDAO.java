package com.taskPlanner.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Member;

public interface MemberDAO extends JpaRepository<Member, Integer>{

	Optional<Member> findByUsername(String username);

	

}
