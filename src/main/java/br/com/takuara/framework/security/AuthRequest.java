package br.com.takuara.framework.security;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class  AuthRequest {
	
	public String username;
	public String password;
}
