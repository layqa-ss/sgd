package com.fhce.sgd.model.programas;

import java.util.HashSet;
import java.util.Set;

import com.fhce.sgd.model.enums.EnumDuracion;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.enums.EnumFormato;
import com.fhce.sgd.model.enums.EnumModalidad;
import com.fhce.sgd.model.enums.EnumRegimen;
import com.fhce.sgd.model.enums.EnumSemestre;
import com.fhce.sgd.model.gestion.UnidadCurricular;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "programas")
public class Programa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_uc", nullable=false)
	private UnidadCurricular uc;
	
	private EnumEstadoPrograma estado;
	
	private Integer year;
	
	/* General */
	private EnumDuracion duracion;
	private String duracionOtro;
	private EnumSemestre semestre;
	private boolean ingreso;
	private boolean requisitos;
	private String requisitosCuales;
	private String recomendaciones;
	private boolean recomNoCorresponde;
	private boolean otrosServicios;
	
	/* Docentes */
	@OneToMany(mappedBy="programa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<ProgramaIntegrante> integrantes;
	
	/* Creditos */
	private Integer horasAula;
	private Integer horasTrabajosConAcompa;
	private String otrosConAcompa;
	private Integer horasOtrosConAcompa;
	
	private Integer horasLecturas;
	private Integer horasTareas;
	private Integer horasTrabajosSinAcompa;
	private Integer horasTrabajosFinales;
	private String otrosSinAcompa;
	private Integer horasOtrosSinAcompa;
	
	private Integer horasTotales;
	private Integer creditos;
	
	/* Contenido */
	private EnumFormato formato;
	private EnumRegimen regimen;
	private EnumModalidad modalidad;
	private boolean adecuaciones;
	private String descripcionAdecuaciones;
	@Lob
	@Column(length=4096)
	private String objetivos;
	@Lob
	@Column(length=4096)
	private String contenidos;
	@Lob
	@Column(length=4096)
	private String descrMetodologia;
	private boolean tareas75obligatoria;
	private boolean aprobDirecta;
	@Lob
	@Column(length=4096)
	private String descrEvaluacion;
	
	@OneToMany(mappedBy="programa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Bibliografia> bibliografia;
	
	@OneToOne(mappedBy="programa", cascade=CascadeType.ALL)
	private MarcoAcademico ma;
	
	public Programa() {
		integrantes = new HashSet<>();
		bibliografia = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnidadCurricular getUc() {
		return uc;
	}

	public void setUc(UnidadCurricular uc) {
		this.uc = uc;
	}

	public EnumDuracion getDuracion() {
		return duracion;
	}

	public void setDuracion(EnumDuracion duracion) {
		this.duracion = duracion;
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

	public Set<ProgramaIntegrante> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(Set<ProgramaIntegrante> integrantes) {
		this.integrantes = integrantes;
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

	public Integer getHorasTareas() {
		return horasTareas;
	}

	public void setHorasTareas(Integer horasTareas) {
		this.horasTareas = horasTareas;
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

	public Set<Bibliografia> getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(Set<Bibliografia> bibliografia) {
		this.bibliografia = bibliografia;
	}

	public EnumEstadoPrograma getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoPrograma estado) {
		this.estado = estado;
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

	public String getDuracionOtro() {
		return duracionOtro;
	}

	public void setDuracionOtro(String duracionOtro) {
		this.duracionOtro = duracionOtro;
	}

	public MarcoAcademico getMa() {
		return ma;
	}

	public void setMa(MarcoAcademico ma) {
		this.ma = ma;
	}

	public boolean isRecomNoCorresponde() {
		return recomNoCorresponde;
	}

	public void setRecomNoCorresponde(boolean recomNoCorresponde) {
		this.recomNoCorresponde = recomNoCorresponde;
	}

}
