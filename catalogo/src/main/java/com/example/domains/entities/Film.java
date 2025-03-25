package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	@Lob
	private String description;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	private Timestamp lastUpdate;

	private int length;

	@Column(length=1)
	@Pattern(regexp = "^(G|PG|PG-13|R|NC-17)$", message = "El rating debe ser uno de los valores: G, PG, PG-13, R, NC-17")
	private String rating;

	@Column(name="release_year")
	@NotNull(message = "El año de lanzamiento no puede ser nulo")
	private Short releaseYear;

	@Column(name="rental_duration", nullable=false)
	private byte rentalDuration;

	@Column(name="rental_rate", nullable=false, precision=10, scale=2)
	@NotNull(message = "La tarifa de alquiler no puede ser nula")
	@DecimalMin(value = "0.01", message = "La tarifa de alquiler debe ser mayor que 0")
	private BigDecimal rentalRate;

	@Column(name="replacement_cost", nullable=false, precision=10, scale=2)
	@NotNull(message = "El costo de reemplazo no puede ser nulo")
	@DecimalMin(value = "0.01", message = "El costo de reemplazo debe ser mayor que 0")
	private BigDecimal replacementCost;

	@Column(nullable=false, length=128)
	@NotEmpty(message = "El título de la película no puede estar vacío")
	@Size(max = 128, message = "El título no puede exceder los 128 caracteres")
	private String title;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id", nullable=false)
	@NotNull(message = "El idioma no puede ser nulo")
	private Language language;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	private Language languageVO;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FilmActor> filmActors = new ArrayList<>();

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FilmCategory> filmCategories;

	public Film() {
		this.filmActors = new ArrayList<>(); 
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Short getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguageVO() {
		return this.languageVO;
	}

	public void setLanguageVO(Language languageVO) {
		this.languageVO = languageVO;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
	    if (this.filmActors == null) {
	        this.filmActors = new ArrayList<>();
	    }

	    this.filmActors.add(filmActor);
	    filmActor.setFilm(this);
	    return filmActor;
	}


	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setFilm(null);

		return filmActor;
	}
	
	public void addActor(Actor actor) {
		FilmActor filmActor = new FilmActor(this, actor);
		filmActors.add(filmActor);
	}

	public void addActor(int actorId) {
		addActor(new Actor(actorId));
	}
	
	public void removeActor(Actor actor) {
		var filmActor = filmActors.stream().filter(item -> item.getActor().equals(actor)).findFirst();
		if (filmActor.isEmpty())
			return;
		filmActors.remove(filmActor.get());
	}

	public void removeActor(int actorId) {
		removeActor(new Actor(actorId));
	}

	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		 if (filmCategories == null) {
	            this.filmCategories = new ArrayList<>();  
	        } else {
	            this.filmCategories = filmCategories;
	        }
	    }
	
	public FilmCategory addFilmCategory(FilmCategory filmCategory) {
		 if (this.filmCategories == null) {
		        this.filmCategories = new ArrayList<>();
		    }

		    this.filmCategories.add(filmCategory);
		    filmCategory.setFilm(this);  
		    return filmCategory;
		}

	public FilmCategory removeFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().remove(filmCategory);
		filmCategory.setFilm(null);

		return filmCategory;
	}
	
	public void addCategory(Category item) {
		FilmCategory filmCategory = new FilmCategory(this, item);
		filmCategories.add(filmCategory);
	}

	public void addCategory(int id) {
		addCategory(new Category(id));
	}

	public void removeCategory(Category ele) {
		var filmCategory = filmCategories.stream().filter(item -> item.getCategory().equals(ele)).findFirst();
		if (filmCategory.isEmpty())
			return;
		filmCategories.remove(filmCategory.get());
	}

	public void removeCategory(int id) {
		removeCategory(new Category(id));
	}

	public List<Actor> getActors() {
		return this.filmActors.stream().map(item -> item.getActor()).toList();
	}

	public List<Category> getCategories() {
		return this.filmCategories.stream()
                .map(item -> item.getCategory())
                .collect(Collectors.toList());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Film o)
			return filmId == o.filmId;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", rentalDuration=" + rentalDuration + ", rentalRate="
				+ rentalRate + ", replacementCost=" + replacementCost + ", lastUpdate=" + lastUpdate + ", description="
				+ description + ", length=" + length + ", rating=" + rating + ", releaseYear=" + releaseYear
				+ ", language=" + language + ", languageVO=" + languageVO + "]";
	}

	public Film merge(Film target) {
		target.title = title;
		target.description = description;
		target.releaseYear = releaseYear;
		target.language = language;
		target.languageVO = languageVO;
		target.rentalDuration = rentalDuration;
		target.rentalRate = rentalRate;
		target.length = length;
		target.replacementCost = replacementCost;
		target.rating = rating;
		target.getActors().stream().filter(item -> !getActors().contains(item))
				.forEach(item -> target.removeActor(item));
		getActors().stream().filter(item -> !target.getActors().contains(item)).forEach(item -> target.addActor(item));
		target.getCategories().stream().filter(item -> !getCategories().contains(item))
				.forEach(item -> target.removeCategory(item));
		getCategories().stream().filter(item -> !target.getCategories().contains(item))
				.forEach(item -> target.addCategory(item));
		

		target.filmActors.forEach(o -> o.prePersiste());
		target.filmCategories.forEach(o -> o.prePersiste());
		
		return target;
	}
	
	@PostPersist
	@PostUpdate
	public void prePersiste() {
		System.err.println("prePersiste(): Bug Hibernate");
		filmActors.forEach(o -> o.prePersiste());
		filmCategories.forEach(o -> o.prePersiste());
	}

}