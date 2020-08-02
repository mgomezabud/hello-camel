package com.zabud.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Peticion {
    private String tipo;
	private String nit;
	private String fecha;
	private String regimen;
    private String token;
    
    @Override
    public String toString() {
      return tipo + "/" + nit + "/" + fecha + "/" + regimen
          + "/" + token;
    }
    
    
}
