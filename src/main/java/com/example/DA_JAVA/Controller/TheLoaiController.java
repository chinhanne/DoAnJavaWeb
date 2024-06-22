package com.example.DA_JAVA.Controller;

import com.example.DA_JAVA.model.TheLoai;
import com.example.DA_JAVA.service.TheLoaiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TheLoaiController {
    @Autowired
    private final TheLoaiService theLoaiService;
    @GetMapping("/TheLoai/add")
    public String showAddForm(Model model) {
        model.addAttribute("theloai", new TheLoai());
        return "/TheLoai/add-category";
    }
    @PostMapping("/TheLoai/add")
    public String addTheLoai(@Valid TheLoai theLoai, BindingResult result) {
        if (result.hasErrors()) {
            return "/TheLoai/add-category";
        }
        theLoaiService.addTheloai(theLoai);
        return "redirect:/TheLoai";
    }
    @GetMapping("/TheLoai")
    public String listTheLoai(Model model) {
        List<TheLoai> theLoai = theLoaiService.getAllTheloai();
        model.addAttribute("theloai",  theLoai);
        return "/TheLoai/categories-list";
    }
    @GetMapping("/TheLoai/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TheLoai theLoai = theLoaiService.getTheloaiById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        model.addAttribute("theloai", theLoai);
        return "/TheLoai/update-category";
    }
    @PostMapping("/TheLoai/update/{id}")
    public String updateTheLoai(@PathVariable("id") Long id, @Valid TheLoai theLoai,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            theLoai.setId(id);
            return "/TheLoai/update-category";
        }
       theLoaiService.updateTheloai(theLoai);
        model.addAttribute("theloai", theLoaiService.getAllTheloai());
        return "redirect:/TheLoai";
    }
    @GetMapping("/TheLoai/delete/{id}")
    public String deletetheloai(@PathVariable("id") Long id, Model model) {
        TheLoai theLoai = theLoaiService.getTheloaiById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        theLoaiService.deleteTheloaiById(id);
        model.addAttribute("theloai", theLoaiService.getAllTheloai());
        return "redirect:/TheLoai";
    }
}
