package com.fhce.sgd.controller.gestion;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.FilterMeta;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.service.GestionService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("gestionController")
@ViewScoped
public class GestionController {

	@Autowired
    private GestionService gestionService;
	
	private AreaTematicaDto area;
	private CarreraDto carrera;
	private UnidadAcademicaDto ua;
	
	private List<CarreraDto> carreras;
	private List<AreaTematicaDto> areas;
	private List<UnidadAcademicaDto> unidades;
	
	private List<AreaTematicaDto> areasFiltradas;
	private List<CarreraDto> carrerasFiltradas;
	private List<UnidadAcademicaDto> unidadesFiltradas;
	private List<FilterMeta> filterBy;
	
	@PostConstruct
    public void init(){
		area = new AreaTematicaDto();
		carrera = new CarreraDto();
		ua = new UnidadAcademicaDto();
		
        carreras = gestionService.getCarreras();
        areas = gestionService.getAreasTematicas();
        unidades = gestionService.getUnidadesAcademicas();
        
        filterBy = new ArrayList<>();
    }

    public String agregarAreaTematica() {
    	gestionService.addAreaTematica(area);
    	areas = gestionService.getAreasTematicas();
    	area = new AreaTematicaDto();
        return "areas-tematicas";
    }
    
    public String borrarAreaTematica(Long id) {
    	gestionService.deleteAreaTematica(id);
    	areas = gestionService.getAreasTematicas();
        return "areas-tematicas";
    }
    
    public String agregarCarrera() {
    	gestionService.addCarrera(carrera);
    	carreras = gestionService.getCarreras();
    	carrera = new CarreraDto();
        return "carreras";
    }
    
    public String borrarCarrera(Long id) {
    	gestionService.deleteCarrera(id);
    	carreras = gestionService.getCarreras();
        return "carreras";
    }
    
    public String agregarUA() {
    	gestionService.addUA(ua);
    	unidades = gestionService.getUnidadesAcademicas();
    	ua = new UnidadAcademicaDto();
    	return "unidades";
    }
    
    public String borrarUA(Long id) {
    	gestionService.deleteUA(id);
    	unidades = gestionService.getUnidadesAcademicas();
    	return "unidades";
    }

	public AreaTematicaDto getArea() {
		return area;
	}

	public void setArea(AreaTematicaDto area) {
		this.area = area;
	}

	public List<CarreraDto> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<CarreraDto> carreras) {
		this.carreras = carreras;
	}

	public List<AreaTematicaDto> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaTematicaDto> areas) {
		this.areas = areas;
	}

	public List<AreaTematicaDto> getAreasFiltradas() {
		return areasFiltradas;
	}

	public void setAreasFiltradas(List<AreaTematicaDto> areasFiltradas) {
		this.areasFiltradas = areasFiltradas;
	}

	public List<CarreraDto> getCarrerasFiltradas() {
		return carrerasFiltradas;
	}

	public void setCarrerasFiltradas(List<CarreraDto> carrerasFiltradas) {
		this.carrerasFiltradas = carrerasFiltradas;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public CarreraDto getCarrera() {
		return carrera;
	}

	public void setCarrera(CarreraDto carrera) {
		this.carrera = carrera;
	}

	public List<UnidadAcademicaDto> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadAcademicaDto> unidades) {
		this.unidades = unidades;
	}

	public UnidadAcademicaDto getUa() {
		return ua;
	}

	public void setUa(UnidadAcademicaDto ua) {
		this.ua = ua;
	}

	public List<UnidadAcademicaDto> getUnidadesFiltradas() {
		return unidadesFiltradas;
	}

	public void setUnidadesFiltradas(List<UnidadAcademicaDto> unidadesFiltradas) {
		this.unidadesFiltradas = unidadesFiltradas;
	}

}