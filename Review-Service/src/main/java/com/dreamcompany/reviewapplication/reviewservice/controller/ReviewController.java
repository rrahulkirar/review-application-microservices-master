package com.dreamcompany.reviewapplication.reviewservice.controller;

import com.dreamcompany.reviewapplication.reviewservice.model.Product;
import com.dreamcompany.reviewapplication.reviewservice.model.Review;
import com.dreamcompany.reviewapplication.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/writereview/{pid}")
    public Review writeReviewMethod(@PathVariable("pid") int id)
    {
         Review obj = reviewService.writeReview(id);
         return obj;

    }

    @PostMapping("/savedreview")
    public void saveReviewDb(@RequestBody Review review)
    {
        reviewService.saveRev(review);
    }

    @GetMapping("/getreview/{pid}")
    public List<Review> getReviewsForProduct(@PathVariable("pid") Integer pid) {
        return reviewService.getReviewsForProduct(pid);
    }
}
