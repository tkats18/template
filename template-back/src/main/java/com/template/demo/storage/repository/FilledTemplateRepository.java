package com.template.demo.storage.repository;

import com.template.demo.storage.entity.FilledTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilledTemplateRepository extends JpaRepository<FilledTemplate,Integer> {
}
