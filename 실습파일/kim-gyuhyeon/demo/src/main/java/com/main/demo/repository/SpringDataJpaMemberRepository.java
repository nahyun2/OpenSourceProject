package com.main.demo.repository;

import com.main.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}