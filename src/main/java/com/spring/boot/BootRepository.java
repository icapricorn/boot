package com.spring.boot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BootRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(String reader);
}
