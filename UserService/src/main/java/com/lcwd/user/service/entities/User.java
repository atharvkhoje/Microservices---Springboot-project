package com.lcwd.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Micro_Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	
	@Id
	private String userId;
	private String name;
	private String email;
	private String about;
	
	@Transient
	private List<Ratings> ratings = new ArrayList<>();
	
	
	
}
