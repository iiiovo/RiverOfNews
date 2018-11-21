package stream.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import stream.news.forms.LoginForm;
import stream.news.models.User;
import stream.news.services.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Login form
     */
    @RequestMapping("/users/login")
    public String login(LoginForm loginForm) {
        // User doesn't need to re-enter credentials
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken)) {
            return "users/login";
        } else {
            return "redirect:/";
        }
    }

    /**
     * Registration form
     */
    @RequestMapping("/users/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject(user);
        modelAndView.setViewName("users/register");
        return modelAndView;
    }

    @RequestMapping(value = "users/register", method = RequestMethod.POST)
    public ModelAndView registration(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = this.userService.findByUserName(user.getUserName());
        modelAndView.setViewName("users/register");
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user", "User with the same User Name already exists!");
        }
        if (!bindingResult.hasErrors()) {
            this.userService.add(user);
            modelAndView.addObject("successMessage", "User has been registered successfully!");
            modelAndView.addObject("user", new User());
        }
        return modelAndView;

    }
}
