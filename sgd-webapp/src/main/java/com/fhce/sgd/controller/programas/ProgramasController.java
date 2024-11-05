package com.fhce.sgd.controller.programas;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.event.ReorderEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.fhce.sgd.controller.AppController;
import com.fhce.sgd.controller.exception.SgdWebappException;
import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UnidadCurricularDto;
import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.dto.programas.AprobarDto;
import com.fhce.sgd.dto.programas.BibliografiaDto;
import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaIntegranteDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;
import com.fhce.sgd.dto.programas.RevisionDto;
import com.fhce.sgd.model.enums.EnumCargo;
import com.fhce.sgd.model.enums.EnumDuracion;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.enums.EnumFormato;
import com.fhce.sgd.model.enums.EnumModalidad;
import com.fhce.sgd.model.enums.EnumRegimen;
import com.fhce.sgd.model.enums.EnumRolDocente;
import com.fhce.sgd.model.enums.EnumSemestre;
import com.fhce.sgd.service.AccionService;
import com.fhce.sgd.service.GestionService;
import com.fhce.sgd.service.ProgramaService;
import com.fhce.sgd.service.UsuarioService;
import com.fhce.sgd.service.exception.SgdServicesException;
import com.fhce.sgd.util.GeneradorPdf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Named("pCtrl")
@ViewScoped
@Slf4j
public class ProgramasController {

	@Autowired
	private GestionService gestionService;

	@Autowired
	private ProgramaService programaService;

	@Autowired
	private AccionService accionService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AppController appCtrl;

	private List<UnidadAcademicaDto> unidadesAcademicas;

	private List<CarreraDto> carreras;

	private Map<Long, List<AreaTematicaDto>> areasPorCarrera;

	private List<ProgramaDto> programasTodos;
	private List<UnidadCurricularDto> unidadesCurriculares;

	private List<UsuarioDto> usuarios;

	private ProgramaNuevoDto nuevo;
	private ProgramaNuevoDto revisado;
	private RevisionDto revision;
	private List<RevisionDto> revisionesPrograma;
	private AprobarDto aprobacion;
	private Part fileDespacho;

	private ProgramaIntegranteDto integrante;

	private BibliografiaDto nuevaBibliografia;
	private Integer maxBiblio = 0;

	private List<FilterMeta> filterBy;
	private List<ProgramaDto> programasFiltrados;

	private boolean edicion = false;

	private StreamedContent file;

	private EnumRegimen[] itemsRegimen;

	private boolean tareasObligTrueDisabled = false;

//	private Integer activa = 1;
//	
//	private boolean estaEnContenido = false;

	@PostConstruct
	public void init() {
		try {
			nuevo = new ProgramaNuevoDto();
			unidadesAcademicas = gestionService.getUnidadesAcademicas();
			carreras = gestionService.getCarreras();
			areasPorCarrera = gestionService.getAreasPorCarrera();
			unidadesCurriculares = gestionService.getUnidadesCurriculares();
			programasTodos = programaService.getProgramasAll();
			usuarios = usuarioService.getUsuarios();

			integrante = new ProgramaIntegranteDto();
			nuevaBibliografia = new BibliografiaDto();
			itemsRegimen = EnumRegimen.values();

			filterBy = new ArrayList<>();
		} catch (SgdServicesException e) {
			log.error("Error en init de ProgramasController.");
		}
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

	public String enviarCC(Long id) {
		try {
			programaService.cambiarEstado(id, EnumEstadoPrograma.REVISION_CC);
			programasTodos = programaService.getProgramasAll();
		} catch (SgdServicesException e) {
			log.error("Error en enviarCC de ProgramasController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al enviar el programa a revisión de Comisión de Carrera"));
		}
		return "ver-programas";
	}

	public String agregarPrograma() {
		nuevo = new ProgramaNuevoDto();
		edicion = false;
		maxBiblio = 0;
		return "/pages/programas/programa.jsf?faces-redirect=true";
	}

	public String editarPrograma(Long id) {
		try {
			nuevo = programaService.obtenerProgramaDtoPorId(id);
			edicion = true;
			maxBiblio = nuevo.getBibliografia().size();
			revisionesPrograma = accionService.getRevisionesPrograma(id);
			return "programa";
		} catch (SgdServicesException e) {
			log.error("Error en editarPrograma de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido obtener el programa"));
			return "ver-programas";
		}
	}
	
	public String aprobarPrograma() {
		try {
			aprobacion = new AprobarDto();
			aprobacion.setIdUsuario(appCtrl.getUsuarioLogueadoId());
			aprobacion.setIdPrograma(revisado.getId());
			return "/pages/programas/aprobar.jsf?faces-redirect=true";
		} catch (Exception e) {
			log.error("Error en aprobarPrograma de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido obtener el programa"));
			return "revision";
		}
	}
	
	public String guardarAprobacion() {
		try {
			accionService.saveOrUpdateAprobacion(aprobacion);
			EnumEstadoPrograma nuevoEstado;
			switch (revisado.getEstado()) {
			case REVISION_CC:
				if (pasoBedelias(revisado.getId()))
					nuevoEstado = EnumEstadoPrograma.REVISION_CAG;
				else
					nuevoEstado = EnumEstadoPrograma.BEDELIAS;
				break;
			case BEDELIAS:
				nuevoEstado = EnumEstadoPrograma.REVISION_CAG;
				break;
			case REVISION_CAG:
				nuevoEstado = EnumEstadoPrograma.REVISION_CG;
				break;
			case REVISION_CG:
				nuevoEstado = EnumEstadoPrograma.APROBADO;
				break;
			case REVISION_CC_ABR:
				nuevoEstado = EnumEstadoPrograma.APROBADO;
				break;
			default:
				nuevoEstado = EnumEstadoPrograma.CREADO;
			}
			programaService.cambiarEstado(revision.getIdPrograma(), nuevoEstado);
			programasTodos = programaService.getProgramasAll();
			return "/pages/programas/ver-programas.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en guardarRevision de ProgramasController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al guardar la revisión"));
			return "revision";
		}
	}
	
	private boolean pasoBedelias(Long idPrograma) {
		// Falta implementar
		return true;
	}
	
	public void upload() {
		try {
			String fileName = Paths.get(fileDespacho.getSubmittedFileName()).getFileName().toString(); 
			byte[] data = FileCopyUtils.copyToByteArray(fileDespacho.getInputStream());
			aprobacion.setDespachoFileName(fileName);
		    aprobacion.setDespachoData(data);
		} catch (IOException e) {
			log.error("Error en upload de ProgramasController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se pudo cargar el archivo"));
		}
	}


	public String revisarPrograma(Long id) {
		try {
			revisado = programaService.obtenerProgramaDtoPorId(id);
			revision = new RevisionDto();
			revision.setIdUsuario(appCtrl.getUsuarioLogueadoId());
			revision.setIdPrograma(revisado.getId());
			return "/pages/programas/revision.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en revisarPrograma de ProgramasController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido obtener el programa"));
			return "ver-programas";
		}
	}

	public String guardarRevision() {
		try {
			accionService.saveOrUpdateRevision(revision);
			EnumEstadoPrograma nuevoEstado;
			switch (revisado.getEstado()) {
			case REVISION_CC:
				nuevoEstado = EnumEstadoPrograma.SUGERENCIAS;
				break;
			case BEDELIAS:
				nuevoEstado = EnumEstadoPrograma.REVISION_CAG;
				break;
			case REVISION_CAG:
				nuevoEstado = EnumEstadoPrograma.REVISION_CC;
				break;
			case REVISION_CG:
				nuevoEstado = EnumEstadoPrograma.REVISION_CAG;
				break;
			case REVISION_CC_ABR:
				nuevoEstado = EnumEstadoPrograma.SUGERENCIAS_ABR;
				break;
			default:
				nuevoEstado = EnumEstadoPrograma.CREADO;
			}
			programaService.cambiarEstado(revision.getIdPrograma(), nuevoEstado);
			programasTodos = programaService.getProgramasAll();
			return "/pages/programas/ver-programas.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en guardarRevision de ProgramasController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al guardar la revisión"));
			return "revision";
		}
	}

	public void crearPdfPrograma(Long id) {
		try {
			ProgramaNuevoDto p = programaService.obtenerProgramaDtoPorId(id);
			GeneradorPdf generador = new GeneradorPdf();
			file = generador.generarPdf(p);
		} catch (SgdServicesException e) {
			log.error("Error en crearPdfPrograma de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al generar el pdf"));
		} catch (SgdWebappException e) {
			log.error("Error en crearPdfPrograma de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al generar el pdf"));
		}
	}

	public String guardarPrograma() {
		try {
			if (validarPrograma()) {
				programaService.saveOrUpdatePrograma(nuevo);
				if (nuevo.getEstado() == EnumEstadoPrograma.SUGERENCIAS) {
					programaService.cambiarEstado(nuevo.getId(), EnumEstadoPrograma.REVISION_CC);
				} else if (nuevo.getEstado() == EnumEstadoPrograma.SUGERENCIAS_ABR) {
					programaService.cambiarEstado(nuevo.getId(), EnumEstadoPrograma.REVISION_CC_ABR);
				}
				programasTodos = programaService.getProgramasAll();
				return "/pages/programas/ver-programas.jsf?faces-redirect=true";
			} else {
				return "programa";
			}

		} catch (SgdServicesException e) {
			log.error("Error en guardarPrograma de ProgramasController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al guardar el programa"));
			return "programa";
		}

	}

	public boolean validarPrograma() {
		boolean valido = true;
		/* Docentes */
		if (nuevo.getIntegrantes().stream()
				.filter(i -> (i.getRol() == EnumRolDocente.RESPONSABLE || i.getRol() == EnumRolDocente.ENCARGADO))
				.collect(Collectors.toList()).size() < 2) {
			valido = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					"Debe agregar al menos un docente con rol \"Responsable\" y un docente con rol \"Encargado\""));
		}

		/* Creditos */
		int sumaHoras = nuevo.getHorasAula() + nuevo.getHorasLecturas() + nuevo.getHorasOtrosConAcompa()
				+ nuevo.getHorasOtrosSinAcompa() + nuevo.getHorasTareas() + nuevo.getHorasTrabajosConAcompa()
				+ nuevo.getHorasTrabajosFinales() + nuevo.getHorasTrabajosSinAcompa();
		if (sumaHoras != nuevo.getHorasTotales()) {
			valido = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suma de horas no es correcta"));
		}
		if (nuevo.getCreditos() == 0) {
			valido = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La cantidad de créditos debe ser mayor a 0"));
		}
		return valido;
	}

	public String borrarPrograma(Long id) {
		try {
			programaService.borrarPrograma(id);
			programasTodos = programaService.getProgramasAll();
		} catch (SgdServicesException e) {
			log.error("Error en borrarPrograma de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido borrar el programa"));
		}
		return "ver-programas";
	}

	public void borrarDocente(ProgramaIntegranteDto pi) {
		try {
			nuevo.getIntegrantes().remove(pi);
		} catch (Exception e) {
			log.error("Error en borrarDocente de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido borrar el docente"));
		}
	}

	public void agregarDocente() {
		boolean error = false;
		if (integrante.getRol() == null) {
			FacesContext.getCurrentInstance().addMessage("form:rol-docente", new FacesMessage("Debe seleccionar rol"));
			error = true;
		}
		if (integrante.getCargo() == null) {
			FacesContext.getCurrentInstance().addMessage("form:cargo-docente",
					new FacesMessage("Debe seleccionar cargo"));
			error = true;
		} else {
			if (integrante.getCargo() == EnumCargo.OTRO
					&& (integrante.getCargoOtro() == null || integrante.getCargoOtro().isBlank())) {
				FacesContext.getCurrentInstance().addMessage("form:otro-docente",
						new FacesMessage("Debe ingresar cargo"));
				error = true;
			}
		}
		if (integrante.getIdUsuario() == null) {
			FacesContext.getCurrentInstance().addMessage("form:id-docente", new FacesMessage("Debe ingresar nombre"));
			error = true;
		}
		if (!error) {
			integrante.setNombre_docente(usuarios.stream().filter(u -> u.getId() == integrante.getIdUsuario()).toList()
					.get(0).getFullname());
			nuevo.getIntegrantes().add(integrante);
			integrante = new ProgramaIntegranteDto();
		}
	}

	public void agregarBibliografia() {
		boolean error = false;
		if (nuevaBibliografia.getTitulo() == null) {
			FacesContext.getCurrentInstance().addMessage("form:titulo-biblio",
					new FacesMessage("Debe ingresar el título del material bibliográfico"));
			error = true;
		}
		if (nuevo.getBibliografia().size() > 100) {
			FacesContext.getCurrentInstance().addMessage("form:titulo-biblio",
					new FacesMessage("No se pueden ingresar más de 100 materiales bibliográficos"));
			error = true;
		}
		if (!error) {
			nuevaBibliografia.setOrden(maxBiblio + 1);
			maxBiblio++;
			nuevo.getBibliografia().add(nuevaBibliografia);
			nuevaBibliografia = new BibliografiaDto();
		}
	}

	public void borrarBibliografia(BibliografiaDto b) {
		try {
			nuevo.getBibliografia().remove(b);
			maxBiblio--;
			onRowReorder(null);
		} catch (Exception e) {
			log.error("Error en borrarBibliografia de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No se ha podido borrar la bibliografia"));
		}
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
			itemsRegimen = new EnumRegimen[] { EnumRegimen.LIBRE };
			nuevo.setRegimen(EnumRegimen.LIBRE);
		} else if (nuevo.getFormato() == EnumFormato.PRACTICA) {
			itemsRegimen = new EnumRegimen[] { EnumRegimen.LIBRE, EnumRegimen.OBLIGATORIA_75_DICTADAS };
		} else {
			itemsRegimen = EnumRegimen.values();
		}
		if (nuevo.getRegimen() != null && nuevo.getRegimen() == EnumRegimen.LIBRE) {
			nuevo.setTareas75obligatoria(true);
			tareasObligTrueDisabled = true;
		} else {
			nuevo.setTareas75obligatoria(false);
			tareasObligTrueDisabled = false;
		}
	}

	public void actualizarTareasOblig() {
		if (nuevo.getRegimen() == EnumRegimen.LIBRE) {
			nuevo.setTareas75obligatoria(true);
			tareasObligTrueDisabled = true;
		} else {
			nuevo.setTareas75obligatoria(false);
			tareasObligTrueDisabled = false;
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

	public ProgramaNuevoDto getRevisado() {
		return revisado;
	}

	public void setRevisado(ProgramaNuevoDto revisado) {
		this.revisado = revisado;
	}

	public boolean isTareasObligTrueDisabled() {
		return tareasObligTrueDisabled;
	}

	public void setTareasObligTrueDisabled(boolean tareasObligTrueDisabled) {
		this.tareasObligTrueDisabled = tareasObligTrueDisabled;
	}

	public RevisionDto getRevision() {
		return revision;
	}

	public void setRevision(RevisionDto revision) {
		this.revision = revision;
	}

	public List<UsuarioDto> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDto> usuarios) {
		this.usuarios = usuarios;
	}

	public List<RevisionDto> getRevisionesPrograma() {
		return revisionesPrograma;
	}

	public void setRevisionesPrograma(List<RevisionDto> revisionesPrograma) {
		this.revisionesPrograma = revisionesPrograma;
	}

	public AprobarDto getAprobacion() {
		return aprobacion;
	}

	public void setAprobacion(AprobarDto aprobacion) {
		this.aprobacion = aprobacion;
	}

	public Part getFileDespacho() {
		return fileDespacho;
	}

	public void setFileDespacho(Part fileDespacho) {
		this.fileDespacho = fileDespacho;
	}

//	public Integer getActiva() {
//		return activa;
//	}
//
//	public void setActiva(Integer activa) {
//		this.activa = activa;
//	}

}