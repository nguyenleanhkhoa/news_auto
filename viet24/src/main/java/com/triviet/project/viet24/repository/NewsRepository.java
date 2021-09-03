package com.triviet.project.viet24.repository;

import com.triviet.project.viet24.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {

    @Query("Select news from News news")
    Page<News> getAllNews( Pageable pageable);

}
