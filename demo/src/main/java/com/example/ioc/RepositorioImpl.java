package com.example.ioc;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorioImpl implements Repositorio{
	public RepositorioImpl(Configuracion config) {
		
	}
	
	public void guardar() {
		System.err.println("Saving");
	}

}
