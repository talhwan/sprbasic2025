package com.thc.sprbasic2025.repository;

import com.thc.sprbasic2025.domain.Boardimg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardimgRepository extends JpaRepository<Boardimg, Long> {
}
