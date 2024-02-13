package com.dreamcompany.reviewapplication.userdetailservice.controller;

import com.dreamcompany.reviewapplication.userdetailservice.model.Review;
import com.dreamcompany.reviewapplication.userdetailservice.model.User;
import com.dreamcompany.reviewapplication.userdetailservice.repository.UserRepo;
import com.dreamcompany.reviewapplication.userdetailservice.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class UserDetailController {

   @Autowired
    UserDetailService userDetailService;

    @GetMapping("/userdetailpage")
    public String indexpagemethod(Model model)
    {
        model.addAttribute("userdetails",new User());
        return "index";

    }

    @PostMapping("/continue")
    public String continuemethod(@ModelAttribute("userdetails") User userdetails, Model model) {

        userDetailService.saveUserDetail(userdetails);
        model.addAttribute("username",userdetails.getName());
        model.addAttribute("list",userDetailService.getAllProducts());
        return "mainpage";
    }

    @GetMapping("/writereview")
    public String writereview(Model model,@RequestParam(value="id") int id) {
        Review review = userDetailService.writereview(id);
        model.addAttribute("review", review);
        model.addAttribute("productId", id);
        return "writereviewpage";

    }

    @PostMapping("/postreview")
    public String reviewdesc(@ModelAttribute("review") Review review)
    {
        userDetailService.saveReview(review);
        return "reviewsavedsuccessful";
    }

    @GetMapping("/readreview")
    public String readReview(Model model,@RequestParam(value="id") int id) {
         List<Review> reviews = userDetailService.readReviews(id);
        model.addAttribute("reviews",reviews);
        return "readreviewpage";

    }

}
