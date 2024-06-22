package com.example.DA_JAVA.service;

import com.example.DA_JAVA.model.TheLoai;
import com.example.DA_JAVA.repository.TheLoaiRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TheLoaiService {
    private final TheLoaiRepository TheLoaiRepository;

    public List<TheLoai> getAllTheloai() {
        return TheLoaiRepository.findAll();
    }

    public Optional<TheLoai> getTheloaiById(Long id) {
        return TheLoaiRepository.findById(id);
    }

    public void addTheloai(TheLoai theLoai) {
        TheLoaiRepository.save(theLoai);
    }

    public void updateTheloai(@NotNull TheLoai theLoai) {
        TheLoai Theloai = TheLoaiRepository.findById(theLoai.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        theLoai.getId() + " does not exist."));
        Theloai.setTenTheLoai(theLoai.getTenTheLoai());
        TheLoaiRepository.save(Theloai);
    }
    public void deleteTheloaiById(Long id) {
        if (!TheLoaiRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        TheLoaiRepository.deleteById(id);
    }
}
