package com.triviet.project.viet24.repository;

import com.triviet.project.viet24.entity.WebSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends JpaRepository<WebSource,Long> {
    @Query("SELECT w from WebSource w ORDER BY w.id DESC")
    Page<WebSource> finalAllWebsite(Pageable pageable);

    @Query("SELECT w from WebSource  w where w.url like ?1")
    WebSource findWebSourceByUrl(String url);

    @Query("select  w from WebSource  w where w.id = ?1")
    WebSource findwebSourceById(long id);
}
