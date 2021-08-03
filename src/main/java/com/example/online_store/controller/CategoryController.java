package com.example.online_store.controller;

import com.example.online_store.entity.Category;
import com.example.online_store.entity.Product;
import com.example.online_store.repository.CategoryRepository;
import com.example.online_store.repository.ProductRepository;
import com.example.online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping(value = "/appliances")
    public String getAppliances(Model model) {
        return sortAndPagAppliances(model, 1, "name", "asc");
    }

    @GetMapping("/smartphones")
    public String getSmartphones(Model model) {
        return sortAndPagSmartphones(model, 1, "name", "asc");
    }

    @GetMapping("/computers")
    public String getComputers(Model model) {
        return sortAndPagComputers(model, 1, "name", "asc");
    }

    @GetMapping("/household_products")
    public String getHouseholdProducts(Model model) {
        return sortAndPagHouseholdProducts(model, 1, "name", "asc");
    }

    @GetMapping("/sporting_products")
    public String getSportingProducts(Model model) {
        return sortAndPagSportingProducts(model, 1, "name", "asc");
    }

    @RequestMapping("/appliances_page/{pageNum}")
    public String sortAndPagAppliances(Model model,
                                       @PathVariable(name = "pageNum") int pageNum,
                                       @Param("sortField") String sortField,
                                       @Param("sortDir") String sortDir) {
        Page<Product> page = service.listAll(pageNum, sortField, sortDir);
        List<Product> productList = page.getContent();
        List<Product> products = new ArrayList<>();
        Category category = categoryRepo.findById(1);
        for (Product prod : productList) {
            if (prod.getCategory().equals(category)) {
                products.add(prod);
            }
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", products.size());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);

        return "product/categories/appliances";
    }

    @RequestMapping("/smartphones_page/{pageNum}")
    public String sortAndPagSmartphones(Model model,
                                        @PathVariable(name = "pageNum") int pageNum,
                                        @Param("sortField") String sortField,
                                        @Param("sortDir") String sortDir) {
        Page<Product> page = service.listAll(pageNum, sortField, sortDir);
        List<Product> productList = page.getContent();
        List<Product> products = new ArrayList<>();
        Category category = categoryRepo.findById(2);
        for (Product prod : productList) {
            if (prod.getCategory().equals(category)) {
                products.add(prod);
            }
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", products.size());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);

        return "product/categories/smartphones";
    }

    @RequestMapping("/computers_page/{pageNum}")
    public String sortAndPagComputers(Model model,
                                      @PathVariable(name = "pageNum") int pageNum,
                                      @Param("sortField") String sortField,
                                      @Param("sortDir") String sortDir) {
        Page<Product> page = service.listAll(pageNum, sortField, sortDir);
        List<Product> productList = page.getContent();
        List<Product> products = new ArrayList<>();
        Category category = categoryRepo.findById(3);
        for (Product prod : productList) {
            if (prod.getCategory().equals(category)) {
                products.add(prod);
            }
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", products.size());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);

        return "product/categories/computers";
    }

    @RequestMapping("/household_products_page/{pageNum}")
    public String sortAndPagHouseholdProducts(Model model,
                                              @PathVariable(name = "pageNum") int pageNum,
                                              @Param("sortField") String sortField,
                                              @Param("sortDir") String sortDir) {
        Page<Product> page = service.listAll(pageNum, sortField, sortDir);
        List<Product> productList = page.getContent();
        List<Product> products = new ArrayList<>();
        Category category = categoryRepo.findById(4);
        for (Product prod : productList) {
            if (prod.getCategory().equals(category)) {
                products.add(prod);
            }
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", products.size());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);

        return "product/categories/household_products";
    }

    @RequestMapping("/sporting_products_page/{pageNum}")
    public String sortAndPagSportingProducts(Model model,
                                             @PathVariable(name = "pageNum") int pageNum,
                                             @Param("sortField") String sortField,
                                             @Param("sortDir") String sortDir) {
        Page<Product> page = service.listAll(pageNum, sortField, sortDir);
        List<Product> productList = page.getContent();
        List<Product> products = new ArrayList<>();
        Category category = categoryRepo.findById(5);
        for (Product prod : productList) {
            if (prod.getCategory().equals(category)) {
                products.add(prod);
            }
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", products.size());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);

        return "product/categories/sporting_products";
    }
}
