package com.triviet.project.viet24.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.triviet.project.viet24.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	@Query("SELECT  c FROM Category c ORDER BY c.id ASC")
    Page<Category> findAllCategory( Pageable pageable);



    @Query(nativeQuery = true, value = "SELECT c.* " +
            "FROM news n , category c " +
            "where n.category_id = c.id " +
//            "and  c.display = ?1 " +
            "GROUP BY c.id, c.category_name")
    List<Category> findAllCategoryByStatus(int status);

    @Query("SELECT  c FROM Category c where c.id = ?1 ORDER BY c.id ASC")
   Category findCategoryById(long id);

    @Query("SELECT  c FROM Category c where c.name = ?1 ORDER BY c.id ASC")
    Category findCategoryByName(String name);

    @Query(nativeQuery = true,value = "SELECT c.* " +
            "FROM category c " +
            "where  c.display = ?1 " +
            "ORDER By c.id DESC ")
    List<Category> findCategoryByDisplay(int display);

    @Query(nativeQuery = true, value = "SELECT c.* " +
            "FROM news n , category c " +
            "where n.category_id = c.id " +
            "and c.name like %?1 " +
            "GROUP BY c.id ")
    List<Category> findCategoryIdByNews(String category);

  @Query(
      nativeQuery = true,
      value =
          " SELECT d.id, d.name, "
              + " COALESCE(a.display,0) as display,"
              + " COALESCE(b.notdisplay,0) as notdisplay, "
              + " CASE When c.sum is not null then c.sum else 0 END AS sum ,"
              + " d.status"
              + " from (("
              + " (SELECT cate1.id, cate1.category_name, cate1.name,COALESCE(count(*),0) as display, cate1.display as status"
              + " FROM news n1,category cate1"
              + " where n1.category_id = cate1.id  and n1.image Is not null and n1.status != 0"
              + " GROUP BY cate1.id,cate1.name) as a"
              + " left JOIN"
              + " (SELECT cate2.id,cate2.category_name, cate2.name,COALESCE(count(*),0) as notdisplay,cate2.display as status"
              + " FROM news n2,category cate2"
              + " where n2.category_id = cate2.id  and (n2.image is null or n2.status = 0)"
              + " GROUP BY cate2.id,cate2.name) as b on a.name = b.name)"
              + " LEFT JOIN "
              + " (SELECT cate3.id, cate3.name,cate3.category_name, COALESCE(count(*),0) as sum"
              + " FROM news n3,category cate3 "
              + " WHERE n3.category_id = cate3.id"
              + " GROUP BY cate3.id,cate3.name ) as c on a.name = c.name )"
              + " RIGHT JOIN ("
              + " SELECT cate4.id, cate4.name,cate4.category_name , cate4.display as status"
              + " FROM news n4,category cate4 "
              + " Where cate4.display = 1"
              + " GROUP BY cate4.id,cate4.name"
              + " ) as d"
              + " ON a.name = d.name"
              + " group by d.id, d.name,display, notdisplay , c.sum")
  List<?> showQuantityNewsOfCategory(int display);

}
