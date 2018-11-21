package stream.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import stream.news.models.Post;
import stream.news.models.User;
import stream.news.services.NotificationService;
import stream.news.services.PostService;
import stream.news.services.UserService;

import javax.validation.Valid;


@Controller
public class PostsController {

    @Autowired
    private PostService postService;
    @Autowired
    private NotificationService notifyService;
    @Autowired
    private UserService userService;

    /**
     * Get post id from parameters and search it in the database
     */
    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        Post post = this.postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);

        return "posts/view";
    }

    /**
     * View for adding a post
     */
    @RequestMapping("/posts/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject(post);
        modelAndView.setViewName("posts/add");
        return modelAndView;
    }

    /**
     * Add a post
     */
    @RequestMapping(value = "/posts/add", method = RequestMethod.POST)
    public ModelAndView add(@Valid Post post, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts/add");
        // Perform validation
        if (post.getTitle().isEmpty()) {
            bindingResult.rejectValue("title", "error.post", "Title field cannot be blank!");
        }
        if (post.getContent().isEmpty()) {
            bindingResult.rejectValue("content", "error.post", "Description field cannot be blank!");
        }
        if (post.getLink().isEmpty()) {
            bindingResult.rejectValue("link", "error.post", "Link field cannot be blank!");
        }
        // Get author
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByUserName(auth.getName());
        if (user == null) {
            bindingResult.rejectValue("author", "error.post", "Author cannot be null!");
        }
        if (!bindingResult.hasErrors()) {
            post.setAuthor(user);
            this.postService.add(post);
            modelAndView.addObject("successMessage", "Article has been added successfully!");
            modelAndView.addObject("post", new Post());
        }
        return modelAndView;
    }

    /**
     * Delete a post
     */
    @RequestMapping("/posts/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Post post = this.postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
        } else {
            this.postService.deleteById(id);
        }
        return "redirect:/posts/";
    }

    /**
     * View for editing a post
     */
    @RequestMapping("/posts/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Post post = this.postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/posts/";
        }
        model.addAttribute("post", post);
        return "posts/edit";
    }

    /**
     * Update a post
     */
    @RequestMapping(value = "/posts/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid Post post, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts/edit");
        // Perform validation
        if (post.getTitle().isEmpty()) {
            bindingResult.rejectValue("title", "error.post", "Title field cannot be blank!");
        }
        if (post.getContent().isEmpty()) {
            bindingResult.rejectValue("content", "error.post", "Description field cannot be blank!");
        }
        if (post.getLink().isEmpty()) {
            bindingResult.rejectValue("link", "error.post", "Link field cannot be blank!");
        }
        // Get author
        User user = this.userService.findById(post.getAuthor().getId());
        if (user == null) {
            bindingResult.rejectValue("author", "error.post", "Author cannot be null!");
        }
        if (!bindingResult.hasErrors()) {
            post.setAuthor(user);
            this.postService.add(post);
            modelAndView.addObject("successMessage", "Article has been updated successfully!");
            modelAndView.addObject("post", post);
        }
        return modelAndView;
    }

    /**
     * Get a list of all posts in the database
     */
    @RequestMapping("/posts")
    public String index(Model model, @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC, value = 5) Pageable pageable) {
        // Get the content of the table
        Page<Post> posts = this.postService.findAll(pageable);

        model.addAttribute("posts", posts);

        return "posts/index";
    }
}
