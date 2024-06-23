package com.example.DA_JAVA.Controller;

import com.example.DA_JAVA.model.SamPham;
import com.example.DA_JAVA.service.SanPhamService;
import com.example.DA_JAVA.service.TheLoaiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    public String showSanphamtList(Model model) {
        model.addAttribute("sanpham", sanPhamService.getAllSanPham());
        return "/sanpham/product-list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sanpham", new SamPham());
        model.addAttribute("theloai", theLoaiService.getAllTheloai());
        return "/sanpham/add-product";
    }
    @PostMapping("/add")
    public String addSanpham(@Valid SamPham samPham, BindingResult result, @RequestParam("image") MultipartFile imagesFile) {
        if (result.hasErrors()) {
            return "/sanpham/add-product";
        }if(!imagesFile.isEmpty()){
            try{
                String imageName=saveImageStatic(imagesFile);
                samPham.setImages("/images/" +imageName);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        sanPhamService.addSanpham(samPham);
        return "redirect:/sanpham";
    }

    private String saveImageStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path);
        return fileName;
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SamPham samPham =sanPhamService.getSanphamById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("sanpham", samPham);
        model.addAttribute("theloai", theLoaiService.getAllTheloai());
        return "/sanpham/update-product";
    }
    @PostMapping("/update/{id}")
    public String updateSanpham(@PathVariable Long id, @Valid SamPham sanpham, BindingResult result, @RequestParam("image")MultipartFile imagesFile) {
        if (result.hasErrors()) {
            sanpham.setId(id);
            return "/sanpham/update-product";

        }if(!imagesFile.isEmpty()) {
            try {
                String imageName = saveImageStatic(imagesFile);
                sanpham.setImages("/images/" + imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sanPhamService.updateSanpham(sanpham);
        return "redirect:/sanpham";
    }
    @GetMapping("/delete/{id}")
    public String deleteSanpham(@PathVariable Long id) {
        sanPhamService.deleteSanphamById(id);
        return "redirect:/sanpham";
    }

    @GetMapping("/detail/{id}")
    public String viewSanphamDetail(@PathVariable Long id, Model model) {
        SamPham samPham = sanPhamService.getSanphamById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("sanpham", samPham);
        return "/sanpham/detail-product";
    }
}
