package com.example.domains.contracts.services;

import java.util.List;

import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Language;
import com.example.domains.entities.models.LanguageDTO;

public interface LanguagesService extends DomainService<Language, Integer> {
	List<Language> obtenerIdiomas();
	List<Language> idiomasAcabanN(String letra);
	List<LanguageDTO> getByProjection(Class<LanguageDTO> class1);

}
