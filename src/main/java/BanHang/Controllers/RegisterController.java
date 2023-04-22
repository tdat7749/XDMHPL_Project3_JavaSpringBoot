/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BanHang.Services.ICustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import BanHang.DataBinding.RegisterDTO;

/**
 *
 * @author hp
 */
@Controller
public class RegisterController {

    private final ICustomerService _customerService;

    public RegisterController(ICustomerService customerService) {
        _customerService = customerService;
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public String Index(HttpSession session, Model model) {

        model.addAttribute("register", new RegisterDTO());

        if (session.getAttribute("customer") != null) {
            return "redirect:/";
        }

        return "Register/register";
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
    public String Register(HttpSession session, @ModelAttribute("register") @Valid RegisterDTO register,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "Register/register";
        }

        Integer checkRegister = _customerService.Register(register);
        if (checkRegister == 1) {
            model.addAttribute("Success", "Tạo tài khoản thành công !");
            return "Register/register";
        }
        if (checkRegister == 0) {
            model.addAttribute("modelError", "Tên tài khoản đã tồn tại !");
            return "Register/register";
        }
        return "redirect:/dang-nhap";
    }
}
