package ua.zorii.helsiPet.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.zorii.helsiPet.exceptions.NotEqualPasswordException;
import ua.zorii.helsiPet.exceptions.UserAlreadyExistException;
import ua.zorii.helsiPet.exceptions.UserNotFoundException;
import ua.zorii.helsiPet.exceptions.WrongPasswordException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(Model model, HttpServletRequest req) {
        model.addAttribute("exception", "Користувача не знайдено!");
        model.addAttribute("url", req.getRequestURL());
        return "error";
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public String handleUserAlreadyExistException(Model model, HttpServletRequest req) {
        model.addAttribute("exception", "Користувач вже існує!");
        model.addAttribute("url", req.getRequestURL());
        return "error";
    }

    @ExceptionHandler(WrongPasswordException.class)
    public String handleWrongPasswordException(Model model, HttpServletRequest req) {
        model.addAttribute("exception", "Неправильний псевдонім чи пароль!");
        model.addAttribute("url", req.getRequestURL());
        return "error";
    }

    @ExceptionHandler(NotEqualPasswordException.class)
    public String handleNotEqualPasswordException(Model model, HttpServletRequest req) {
        model.addAttribute("exception", "Паролі не співпадають!");
        model.addAttribute("url", req.getRequestURL());
        return "error";
    }
}
