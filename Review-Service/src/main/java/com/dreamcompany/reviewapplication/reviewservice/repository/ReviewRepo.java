package com.dreamcompany.reviewapplication.reviewservice.repository;


import com.dreamcompany.reviewapplication.reviewservice.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends CrudRepository<Review,Integer> {
    List<Review> findReviewByproduct_id(Integer pid);
}
