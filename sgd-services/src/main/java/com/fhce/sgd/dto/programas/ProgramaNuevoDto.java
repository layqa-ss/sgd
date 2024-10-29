package com.fhce.sgd.dto.programas;

import java.util.ArrayList;
import java.util.List;

import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.model.enums.EnumDuracion;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.enums.EnumFormato;
import com.fhce.sgd.model.enums.EnumModalidad;
import com.fhce.sgd.model.enums.EnumRegimen;
import com.fhce.sgd.model.enums.EnumSemestre;

public class ProgramaNuevoDto {

	/* General */
	private Long id;
	private String nombreUC;
	private Integer year;
	private EnumEstadoPrograma estado;

	private List<UnidadAcademicaDto> unidades;
	
	private List<CarreraDto> carreras;

	private EnumDuracion duracion;
	private String duracionOtro;
	private EnumSemestre semestre;
	private boolean ingreso;
	private boolean requisitos;
	private String requisitosCuales;
	private String recomendaciones;
	private boolean recomNoCorresponde;
	private boolean otrosServicios;

	/* Creditos */
	private Integer horasAula = 0;
	private Integer horasTrabajosConAcompa= 0;
	private String otrosConAcompa;
	private Integer horasOtrosConAcompa= 0;
	
	private Integer horasLecturas = 0;
	private Integer horasTareas = 0;
	private Integer horasTrabajosSinAcompa = 0;
	private Integer horasTrabajosFinales = 0;
	private String otrosSinAcompa;
	private Integer horasOtrosSinAcompa = 0;
	
	private Integer horasTotales = 0;
	private Integer creditos = 0;
	
	/* Contenido */
	private EnumFormato formato;
	private EnumRegimen regimen;
	private EnumModalidad modalidad;
	private boolean adecuaciones;
	private String descripcionAdecuaciones;
	private String objetivos;
	private String contenidos;
	private String descrMetodologia;
	private boolean tareas75obligatoria;
	private boolean aprobDirecta;
	private String descrEvaluacion;
	private List<BibliografiaDto> bibliografia;
	
	/* Docentes */
	private List<ProgramaIntegranteDto> integrantes;

	public ProgramaNuevoDto() {
		unidades = new ArrayList<>();
		carreras = new ArrayList<>();
		integrantes = new ArrayList<>();
		bibliografia = new ArrayList<>();
	}

	public String getNombreUC() {
		return nombreUC;
	}

	public void setNombreUC(String nombreUC) {
		this.nombreUC = nombreUC;
	}

	public List<UnidadAcademicaDto> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadAcademicaDto> unidades) {
		this.unidades = unidades;
	}

	public List<CarreraDto> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<CarreraDto> carreras) {
		this.carreras = carreras;
	}

	public EnumDuracion getDuracion() {
		return duracion;
	}

	public void setDuracion(EnumDuracion duracion) {
		this.duracion = duracion;
	}

	public String getDuracionOtro() {
		return duracionOtro;
	}

	public void setDuracionOtro(String duracionOtro) {
		this.duracionOtro = duracionOtro;
	}

	public boolean isIngreso() {
		return ingreso;
	}

	public void setIngreso(boolean ingreso) {
		this.ingreso = ingreso;
	}

	public boolean isRequisitos() {
		return requisitos;
	}

	public void setRequisitos(boolean requisitos) {
		this.requisitos = requisitos;
	}

	public String getRequisitosCuales() {
		return requisitosCuales;
	}

	public void setRequisitosCuales(String requisitosCuales) {
		this.requisitosCuales = requisitosCuales;
	}

	public String getRecomendaciones() {
		return recomendaciones;
	}

	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}

	public boolean isOtrosServicios() {
		return otrosServicios;
	}

	public void setOtrosServicios(boolean otrosServicios) {
		this.otrosServicios = otrosServicios;
	}

	public Integer getHorasAula() {
		return horasAula;
	}

	public void setHorasAula(Integer horasAula) {
		this.horasAula = horasAula;
	}

	public Integer getHorasTrabajosConAcompa() {
		return horasTrabajosConAcompa;
	}

	public void setHorasTrabajosConAcompa(Integer horasTrabajosConAcompa) {
		this.horasTrabajosConAcompa = horasTrabajosConAcompa;
	}

	public String getOtrosConAcompa() {
		return otrosConAcompa;
	}

	public void setOtrosConAcompa(String otrosConAcompa) {
		this.otrosConAcompa = otrosConAcompa;
	}

	public Integer getHorasOtrosConAcompa() {
		return horasOtrosConAcompa;
	}

	public void setHorasOtrosConAcompa(Integer horasOtrosConAcompa) {
		this.horasOtrosConAcompa = horasOtrosConAcompa;
	}

	public Integer getHorasLecturas() {
		return horasLecturas;
	}

	public void setHorasLecturas(Integer horasLecturas) {
		this.horasLecturas = horasLecturas;
	}

	public Integer getHorasTrabajosSinAcompa() {
		return horasTrabajosSinAcompa;
	}

	public void setHorasTrabajosSinAcompa(Integer horasTrabajosSinAcompa) {
		this.horasTrabajosSinAcompa = horasTrabajosSinAcompa;
	}

	public Integer getHorasTrabajosFinales() {
		return horasTrabajosFinales;
	}

	public void setHorasTrabajosFinales(Integer horasTrabajosFinales) {
		this.horasTrabajosFinales = horasTrabajosFinales;
	}

	public String getOtrosSinAcompa() {
		return otrosSinAcompa;
	}

	public void setOtrosSinAcompa(String otrosSinAcompa) {
		this.otrosSinAcompa = otrosSinAcompa;
	}

	public Integer getHorasOtrosSinAcompa() {
		return horasOtrosSinAcompa;
	}

	public void setHorasOtrosSinAcompa(Integer horasOtrosSinAcompa) {
		this.horasOtrosSinAcompa = horasOtrosSinAcompa;
	}

	public Integer getHorasTotales() {
		return horasTotales;
	}

	public void setHorasTotales(Integer horasTotales) {
		this.horasTotales = horasTotales;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public Integer getHorasTareas() {
		return horasTareas;
	}

	public void setHorasTareas(Integer horasTareas) {
		this.horasTareas = horasTareas;
	}

	public boolean isAdecuaciones() {
		return adecuaciones;
	}

	public void setAdecuaciones(boolean adecuaciones) {
		this.adecuaciones = adecuaciones;
	}

	public String getDescripcionAdecuaciones() {
		return descripcionAdecuaciones;
	}

	public void setDescripcionAdecuaciones(String descripcionAdecuaciones) {
		this.descripcionAdecuaciones = descripcionAdecuaciones;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getContenidos() {
		return contenidos;
	}

	public void setContenidos(String contenidos) {
		this.contenidos = contenidos;
	}

	public String getDescrMetodologia() {
		return descrMetodologia;
	}

	public void setDescrMetodologia(String descrMetodologia) {
		this.descrMetodologia = descrMetodologia;
	}

	public boolean isTareas75obligatoria() {
		return tareas75obligatoria;
	}

	public void setTareas75obligatoria(boolean tareas75obligatoria) {
		this.tareas75obligatoria = tareas75obligatoria;
	}

	public boolean isAprobDirecta() {
		return aprobDirecta;
	}

	public void setAprobDirecta(boolean aprobDirecta) {
		this.aprobDirecta = aprobDirecta;
	}

	public String getDescrEvaluacion() {
		return descrEvaluacion;
	}

	public void setDescrEvaluacion(String descrEvaluacion) {
		this.descrEvaluacion = descrEvaluacion;
	}

	public List<BibliografiaDto> getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(List<BibliografiaDto> bibliografia) {
		this.bibliografia = bibliografia;
	}

	public List<ProgramaIntegranteDto> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<ProgramaIntegranteDto> integrantes) {
		this.integrantes = integrantes;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public EnumSemestre getSemestre() {
		return semestre;
	}

	public void setSemestre(EnumSemestre semestre) {
		this.semestre = semestre;
	}

	public EnumFormato getFormato() {
		return formato;
	}

	public void setFormato(EnumFormato formato) {
		this.formato = formato;
	}

	public EnumRegimen getRegimen() {
		return regimen;
	}

	public void setRegimen(EnumRegimen regimen) {
		this.regimen = regimen;
	}

	public EnumModalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(EnumModalidad modalidad) {
		this.modalidad = modalidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRecomNoCorresponde() {
		return recomNoCorresponde;
	}

	public void setRecomNoCorresponde(boolean recomNoCorresponde) {
		this.recomNoCorresponde = recomNoCorresponde;
	}

	public EnumEstadoPrograma getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoPrograma estado) {
		this.estado = estado;
	}

}
