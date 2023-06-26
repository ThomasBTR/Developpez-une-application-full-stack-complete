package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

}
