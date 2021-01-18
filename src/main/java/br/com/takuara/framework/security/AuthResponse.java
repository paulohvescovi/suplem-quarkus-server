package br.com.takuara.framework.security;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class AuthResponse {
	
	public String token;
}
