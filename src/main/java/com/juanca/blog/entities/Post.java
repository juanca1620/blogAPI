package com.juanca.blog.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.internal.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private Integer post_id;
	 
	@Column(length = 100,nullable = false)
	private String title;
	
	@Column(length = 2000,nullable = false)
	private String content;
	
	@Column(length = 200,nullable = false)
	private String imageName;
	
	@Column(nullable = false)
	private LocalDateTime addedDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
