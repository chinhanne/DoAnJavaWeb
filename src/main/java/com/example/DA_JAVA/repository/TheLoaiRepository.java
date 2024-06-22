package com.example.DA_JAVA.repository;

import com.example.DA_JAVA.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai,Long> {
}
