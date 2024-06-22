package com.example.DA_JAVA.service;

import com.example.DA_JAVA.model.SamPham;
import com.example.DA_JAVA.repository.SanPhamRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;
    public List<SamPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }
    public Optional<SamPham> getSanphamById(Long id) {
        return sanPhamRepository.findById(id);
    }
    public SamPham addSanpham(SamPham sanpham) {
        return sanPhamRepository.save(sanpham);
    }
    public SamPham updateSanpham(@NotNull SamPham sanpham) {
        SamPham sanPham = sanPhamRepository.findById(sanpham.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        sanpham.getId() + " does not exist."));
        sanPham.setTen(sanpham.getTen());
        sanPham.setGia(sanpham.getGia());
        sanPham.setMota(sanpham.getMota());
        sanPham.setImages(sanpham.getImages());
        sanPham.setTheLoai(sanpham.getTheLoai());

        return sanPhamRepository.save(sanPham);
    }
    public void deleteSanphamById(Long id) {
        if (!sanPhamRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        sanPhamRepository.deleteById(id);
    }
}
