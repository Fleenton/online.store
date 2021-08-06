package com.example.online_store.controller;

import com.example.online_store.entity.CartItem;
import com.example.online_store.entity.Product;
import com.example.online_store.repository.CartRepository;
import com.example.online_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;

    @RequestMapping("/cart")
    public String getCartProduct(Model model) {
        List<CartItem> cartItems = cartRepo.findAll();
        return viewCart(model, cartItems, 1, "name", "asc");
    }

    @RequestMapping("/cart/{pageNum}")
    public String viewCart(Model model,
                           List<CartItem> cart,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir) {
        List<Product> productList = new ArrayList<>();
        for (CartItem cartItem : cart) {
            Product product = productRepo.findById(cartItem.getProductId()).get();
            product.setNoId(cartItem.getId());
            productList.add(product);
        }

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", 1);
        model.addAttribute("totalItems", productList.size());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("productList", productList);

        return "product/ordering/cart";
    }

    @RequestMapping("/add_to_cart/{id}")
    public String addProductToCart(@PathVariable(name = "id") Long id) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(id);
        cartRepo.save(cartItem);
        return "redirect:/viewing/{id}";
    }

    @RequestMapping("/delete_cart_id/{id}")
    public String deleteProductToCart(@PathVariable(name = "id") Long id) {
        cartRepo.deleteById(id);
        return "redirect:/cart";
    }

    @RequestMapping("/buy_products")
    public String buyProducts() {
        cartRepo.deleteAll();
        return "redirect:/products_list";
    }
}
