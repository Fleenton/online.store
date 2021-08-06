package com.example.online_store.controller;

import com.example.online_store.entity.Category;
import com.example.online_store.entity.Product;
import com.example.online_store.repository.CategoryRepository;
import com.example.online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryRepository categoryRepo;

    @RequestMapping("/products_list")
    public String viewProductPage(Model model) {
        return viewPage(model, 1, "name", "asc");
    }

    @RequestMapping("/new_product")
    public String newProductPage(Model model) {
        List<Category> categoryList = categoryRepo.findAll();
        model.addAttribute("categoryList", categoryList);
        Product product = new Product();
        model.addAttribute("product", product);

        return "product/new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);

        return "redirect:/products_list";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editProductPage(@PathVariable(name = "id") int id, Model model) {
        ModelAndView mav = new ModelAndView("product/edit_product");
        List<Category> categoryList = categoryRepo.findAll();
        model.addAttribute("categoryList", categoryList);
        Product product = service.getById(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);

        return "redirect:/products_list";
    }

    @RequestMapping("/viewing/{id}")
    public String getProductDescription(Model model, @PathVariable(name = "id") int id) {
        Product product = service.getById(id);
        model.addAttribute("product", product);

        return "product/viewing";
    }

    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir) {
        Page<Product> page = service.listAll(pageNum, sortField, sortDir);
        List<Product> productList = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("productList", productList);

        return "product/products_list";
    }

    @RequestMapping("/search")
    public String viewSearchPage(Model model,
                                 @Param("keyword") String keyword) {
        List<Product> productList = service.listAll(keyword);
        model.addAttribute("productList", productList);
        model.addAttribute("keyword", keyword);
        if(keyword == null) {
            return "redirect:header";
        }

        return "product/products_list";
    }
}
