package com.nguyenvanminh.subwayticketsalesuser.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nguyenvanminh.subwayticketsalesuser.model.Cart;

@Controller
@SessionAttributes("cart")
public class CartControlller {

	@SuppressWarnings("unchecked")
	@GetMapping("/cart")
	public String index(ModelMap map, HttpSession session) {
		List<Cart> carts = (List<Cart>) session.getAttribute("cart");
		map.addAttribute("carts", carts);
		return "pages/cart";
	}
}
