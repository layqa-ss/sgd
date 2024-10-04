package com.fhce.sgd.controller.programas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.event.ReorderEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UnidadCurricularDto;
import com.fhce.sgd.dto.programas.BibliografiaDto;
import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaIntegranteDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;
import com.fhce.sgd.model.enums.EnumCargo;
import com.fhce.sgd.model.enums.EnumDuracion;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.enums.EnumFormato;
import com.fhce.sgd.model.enums.EnumModalidad;
import com.fhce.sgd.model.enums.EnumRegimen;
import com.fhce.sgd.model.enums.EnumRolDocente;
import com.fhce.sgd.model.enums.EnumSemestre;
import com.fhce.sgd.service.GestionService;
import com.fhce.sgd.service.ProgramaService;
import com.fhce.sgd.util.GeneradorPdf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("pCtrl")
@ViewScoped
public class ProgramasController implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GestionService gestionService;

	@Autowired
	private ProgramaService programaService;

	private List<UnidadAcademicaDto> unidadesAcademicas;

	private List<CarreraDto> carreras;

	private Map<Long, List<AreaTematicaDto>> areasPorCarrera;
	
	private List<ProgramaDto> programasTodos;
	private List<UnidadCurricularDto> unidadesCurriculares;
	
	private ProgramaNuevoDto nuevo;

	private ProgramaIntegranteDto integrante;

	private BibliografiaDto nuevaBibliografia;
	private Integer maxBiblio = 0;

	private List<FilterMeta> filterBy;
	private List<ProgramaDto> programasFiltrados;

	private boolean edicion = false;

	private StreamedContent file;
	
	private EnumRegimen[] itemsRegimen;

//	private Integer activa = 1;
//	
//	private boolean estaEnContenido = false;

	@PostConstruct
	public void init() {
		System.out.println("init programas controller");
		nuevo = new ProgramaNuevoDto();
		unidadesAcademicas = gestionService.getUnidadesAcademicas();
		carreras = gestionService.getCarreras();
		areasPorCarrera = gestionService.getAreasPorCarrera();
		unidadesCurriculares = gestionService.getUnidadesCurriculares();
		programasTodos = programaService.getProgramasAll();

		integrante = new ProgramaIntegranteDto();
		nuevaBibliografia = new BibliografiaDto();
		itemsRegimen = EnumRegimen.values();
		
		filterBy = new ArrayList<>();
	}

//	public void cambiarPestania(Integer p) {
//		activa = p;
//	}
//
//	public void siguiente() {
//		estaEnContenido = true;
//	}
//
//	public void anterior() {
//		activa--;
//		System.out.println(nuevo.getNombreUC());
//	}

	public String agregarPrograma() {
		nuevo = new ProgramaNuevoDto();
		edicion = false;
		maxBiblio = 0;
		return "/pages/programas/programa.jsf?faces-redirect=true";
	}

	public String editarPrograma(Long id) {
		nuevo = programaService.obtenerProgramaPorId(id);
		edicion = true;
		maxBiblio = nuevo.getBibliografia().size();
		return "programa";
	}

	public void crearPdfPrograma(Long id) {
		ProgramaNuevoDto p = programaService.obtenerProgramaPorId(id);
		GeneradorPdf generador = new GeneradorPdf();
		file = generador.generarPdf(p);
	}

	public String guardarPrograma() {
		programaService.saveOrUpdatePrograma(nuevo);
		programasTodos = programaService.getProgramasAll();
		return "ver-programas";
	}
	
	public String borrarPrograma(Long id) {
		programaService.borrarPrograma(id);
		programasTodos = programaService.getProgramasAll();
		return "ver-programas";
	}

	public void agregarDocente() {
		nuevo.getIntegrantes().add(integrante);
		integrante = new ProgramaIntegranteDto();
	}

	public void agregarBibliografia() {
		nuevaBibliografia.setOrden(maxBiblio + 1);
		maxBiblio++;
		nuevo.getBibliografia().add(nuevaBibliografia);
		nuevaBibliografia = new BibliografiaDto();
	}

	public void onRowReorder(ReorderEvent event) {
		Integer order = 0;
		for (BibliografiaDto b : nuevo.getBibliografia()) {
			b.setOrden(order + 1);
			order++;
		}
	}
	
	public void actualizarRegimenOpciones() {
		if (nuevo.getFormato() == EnumFormato.TEORICA) {
			itemsRegimen = new EnumRegimen[] {EnumRegimen.LIBRE};
			nuevo.setRegimen(EnumRegimen.LIBRE);
			nuevo.setTareas75obligatoria(true);
		} else if (nuevo.getFormato() == EnumFormato.PRACTICA) {
			itemsRegimen = new EnumRegimen[] {EnumRegimen.LIBRE, EnumRegimen.OBLIGATORIA_75_DICTADAS};
		} else {
			itemsRegimen = EnumRegimen.values();
		}
	}

	public EnumRolDocente[] getRoles() {
		return EnumRolDocente.values();
	}

	public EnumCargo[] getCargos() {
		return EnumCargo.values();
	}

	public EnumEstadoPrograma[] getEstados() {
		return EnumEstadoPrograma.values();
	}

	public EnumDuracion[] getItemsDuracion() {
		return EnumDuracion.values();
	}

	public EnumFormato[] getItemsFormato() {
		return EnumFormato.values();
	}

	public EnumModalidad[] getItemsModalidad() {
		return EnumModalidad.values();
	}

	public EnumSemestre[] getItemsSemestre() {
		return EnumSemestre.values();
	}

//	public boolean isEstaEnContenido() {
//		return estaEnContenido;
//	}
//
//	public void setEstaEnContenido(boolean estaEnContenido) {
//		this.estaEnContenido = estaEnContenido;
//	}

	public EnumRegimen[] getItemsRegimen() {
		return itemsRegimen;
	}

	public void setItemsRegimen(EnumRegimen[] itemsRegimen) {
		this.itemsRegimen = itemsRegimen;
	}

	public ProgramaNuevoDto getNuevo() {
		return nuevo;
	}

	public void setNuevo(ProgramaNuevoDto nuevo) {
		this.nuevo = nuevo;
	}

	public List<CarreraDto> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<CarreraDto> carreras) {
		this.carreras = carreras;
	}

	public Map<Long, List<AreaTematicaDto>> getAreasPorCarrera() {
		return areasPorCarrera;
	}

	public void setAreasPorCarrera(Map<Long, List<AreaTematicaDto>> areasPorCarrera) {
		this.areasPorCarrera = areasPorCarrera;
	}

	public ProgramaIntegranteDto getIntegrante() {
		return integrante;
	}

	public void setIntegrante(ProgramaIntegranteDto integrante) {
		this.integrante = integrante;
	}

	public BibliografiaDto getNuevaBibliografia() {
		return nuevaBibliografia;
	}

	public void setNuevaBibliografia(BibliografiaDto nuevaBibliografia) {
		this.nuevaBibliografia = nuevaBibliografia;
	}

	public List<UnidadAcademicaDto> getUnidadesAcademicas() {
		return unidadesAcademicas;
	}

	public void setUnidadesAcademicas(List<UnidadAcademicaDto> unidadesAcademicas) {
		this.unidadesAcademicas = unidadesAcademicas;
	}

	public List<ProgramaDto> getProgramasTodos() {
		return programasTodos;
	}

	public void setProgramasTodos(List<ProgramaDto> programasTodos) {
		this.programasTodos = programasTodos;
	}

	public List<UnidadCurricularDto> getUnidadesCurriculares() {
		return unidadesCurriculares;
	}

	public void setUnidadesCurriculares(List<UnidadCurricularDto> unidadesCurriculares) {
		this.unidadesCurriculares = unidadesCurriculares;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public List<ProgramaDto> getProgramasFiltrados() {
		return programasFiltrados;
	}

	public void setProgramasFiltrados(List<ProgramaDto> programasFiltrados) {
		this.programasFiltrados = programasFiltrados;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

//	public Integer getActiva() {
//		return activa;
//	}
//
//	public void setActiva(Integer activa) {
//		this.activa = activa;
//	}
	
	

}