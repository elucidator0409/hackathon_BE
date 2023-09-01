package com.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByActiveAndMbrId(boolean active, String username);

}
