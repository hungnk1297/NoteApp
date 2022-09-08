package com.vmo.hungnk.noteapp.controller;

import com.vmo.hungnk.noteapp.model.request.UserRequestDTO;
import com.vmo.hungnk.noteapp.model.response.UserResponseDTO;
import com.vmo.hungnk.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.vmo.hungnk.noteapp.constant.Constant.ModelAttributeConstant.*;
import static com.vmo.hungnk.noteapp.constant.Constant.TemplateConstant.*;
import static com.vmo.hungnk.noteapp.util.MvcUtil.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String routingUser() {
        if (loggedIn()) {
            return REDIRECT + HOME;
        } else
            return INDEX;
    }

    // Register
    @GetMapping(path = "/register")
    public String getRegister(Model model) {
        model.addAttribute("userRequestDTO", new UserRequestDTO());
        return USER_REGISTER;
    }

    @PostMapping(path = "/register")
    public String createEmployee(Model model,
                                 @ModelAttribute(name = "userRequestDTO") UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        if (userResponseDTO != null) {
            model.addAttribute(SUCCESS_REGISTER_USER, true);
            return USER_LOGIN;
        } else {
            model.addAttribute(SUCCESS_REGISTER_USER, null);
            return USER_REGISTER;
        }
    }

    //  Log in
    @GetMapping(path = "/login")
    public String getLogin() {
        if (loggedIn()) {
            return REDIRECT + HOME;
        }
        return USER_LOGIN;
    }

    //  Log in
    @GetMapping(path = "/logout")
    public String logout() {
        clearSession();
        return INDEX;
    }

    @PostMapping(path = "/login")
    public String login(@ModelAttribute("employeeCreateRequestDTO") UserRequestDTO requestDTO) {
        if (loggedIn()) {
            return REDIRECT + HOME;
        } else {
            UserResponseDTO loginUser = userService.validateLogin(requestDTO);
            if (loginUser != null) {
                HttpSession session = getSession();
                session.setAttribute(LOGGED_IN_USER_ID, loginUser.getUserID());
                session.setAttribute(LOGGED_IN_USER_NAME, loginUser.getUsername());
                return REDIRECT + HOME;
            } else {
                return USER_LOGIN;
            }
        }
    }


}
