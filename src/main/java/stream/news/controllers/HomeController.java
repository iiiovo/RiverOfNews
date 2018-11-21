package stream.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import stream.news.models.Post;
import stream.news.services.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    /**
     * With the annotation @Service for the service implementation, it tells the Spring Framework that class will used by the application
     * controller as a service and it will be automatically instantiated and injected in the controllers(through the @Autowired annotation).
     */
    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/", "/home"})
    public String index(Model model) {
        // Get all posts
        List<Post> allPosts = this.postService.findAll();
        // Send results to view model
        model.addAttribute("allPosts", allPosts);
        return "index"; // "Normal use"
    }
}
