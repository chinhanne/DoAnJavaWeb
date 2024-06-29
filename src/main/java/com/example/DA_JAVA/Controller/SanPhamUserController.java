package com.example.DA_JAVA.Controller;

import com.example.DA_JAVA.model.SamPham;
import com.example.DA_JAVA.service.SanPhamService;
import com.example.DA_JAVA.service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sanphamuser")
public class SanPhamUserController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    public String showSanphamUsertList(Model model) {
        model.addAttribute("sanpham", sanPhamService.getAllSanPham());
        return "/sanphamuser/product-list-user";
    }

    @GetMapping("/details/{id}")
    public String viewSanphamDetail(@PathVariable("id") Long id, Model model) {
        SamPham samPham = sanPhamService.getSanphamById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("sanpham", samPham);
        return "/sanphamuser/details-product";
    }
}
