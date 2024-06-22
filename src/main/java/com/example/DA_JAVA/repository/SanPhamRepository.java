package com.example.DA_JAVA.repository;

import com.example.DA_JAVA.model.SamPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository  extends JpaRepository<SamPham,Long> {
}
