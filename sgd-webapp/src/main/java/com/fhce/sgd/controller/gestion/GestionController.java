package com.fhce.sgd.controller.gestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.primefaces.model.DualListModel;
import org.primefaces.model.FilterMeta;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.RolDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.enums.EnumOperacion;
import com.fhce.sgd.model.enums.EnumTipoAdscripcion;
import com.fhce.sgd.service.GestionService;
import com.fhce.sgd.service.UsuarioService;
import com.fhce.sgd.service.exception.SgdServicesException;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("gestionController")
@ViewScoped
@Slf4j
public class GestionController {

	@Autowired
	private GestionService gestionService;

	@Autowired
	private UsuarioService usuarioService;

	private AreaTematicaDto area;
	private CarreraDto carrera;
	private UnidadAcademicaDto ua;
	private UsuarioDto user;
	private RolDto rol;

	private List<CarreraDto> carreras;
	private List<AreaTematicaDto> areas;
	private List<UnidadAcademicaDto> unidades;
	private List<UsuarioDto> usuarios;
	private List<RolDto> roles;

	private List<AreaTematicaDto> areasFiltradas;
	private List<CarreraDto> carrerasFiltradas;
	private List<UnidadAcademicaDto> unidadesFiltradas;
	private List<UsuarioDto> usuariosFiltrados;
	private List<FilterMeta> filterBy;

	private DualListModel<String> operaciones;
	private boolean edicionRol = false;

	private boolean edicionUsuario = false;

	@PostConstruct
	public void init() {
		try {
			area = new AreaTematicaDto();
			carrera = new CarreraDto();
			ua = new UnidadAcademicaDto();
			user = new UsuarioDto();
			rol = new RolDto();

			carreras = gestionService.getCarreras();
			areas = gestionService.getAreasTematicas();
			unidades = gestionService.getUnidadesAcademicas();
			usuarios = usuarioService.getUsuarios();
			roles = gestionService.getRoles();

			filterBy = new ArrayList<>();

			List<String> opeSource = Stream.of(EnumOperacion.values()).map(EnumOperacion::name)
					.collect(Collectors.toList());
			List<String> opeTarget = new ArrayList<>();

			operaciones = new DualListModel<>(opeSource, opeTarget);
		} catch (SgdServicesException e) {
			log.error("Error en init de GestionController.");
		}
	}

	public String agregarAreaTematica() {
		try {
			gestionService.addAreaTematica(area);
			areas = gestionService.getAreasTematicas();
			area = new AreaTematicaDto();
			return "/pages/gestion/areas-tematicas.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en agregarAreaTematica de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al agregar el área temática"));
			return "areas-tematicas";
		}
	}

	public String borrarAreaTematica(Long id) {
		try {
			gestionService.deleteAreaTematica(id);
			areas = gestionService.getAreasTematicas();
		} catch (SgdServicesException e) {
			log.error("Error en borrarAreaTematica de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No se ha podido borrar el área temática"));
		}
		return "areas-tematicas";
	}

	public String agregarCarrera() {
		try {
			gestionService.addCarrera(carrera);
			carreras = gestionService.getCarreras();
			carrera = new CarreraDto();
			return "/pages/gestion/carreras.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en agregarCarrera de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al agregar la carrera"));
			return "carreras";
		}
		
	}

	public String borrarCarrera(Long id) {
		try {
			gestionService.deleteCarrera(id);
			carreras = gestionService.getCarreras();
		} catch (SgdServicesException e) {
			log.error("Error en borrarCarrera de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido borrar la carrera"));
		}
		return "carreras";
	}

	public String agregarUA() {
		try {
			gestionService.addUA(ua);
			unidades = gestionService.getUnidadesAcademicas();
			ua = new UnidadAcademicaDto();
			return "/pages/gestion/unidades.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en agregarUA de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al agregar la unidad académica"));
			return "unidades";
		}
	}

	public String borrarUA(Long id) {
		try {
			gestionService.deleteUA(id);
			unidades = gestionService.getUnidadesAcademicas();
		} catch (SgdServicesException e) {
			log.error("Error en borrarUA de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No se ha podido borrar la unidad académica"));
		}
		return "unidades";
	}

	public String nuevoUsuario() {
		user = new UsuarioDto();
		edicionUsuario = false;
		return "/pages/gestion/usuario.jsf?faces-redirect=true";
	}

	public String agregarUsuario() {
		try {
			usuarioService.saveOrUpdateUsuario(user);
			usuarios = usuarioService.getUsuarios();
			user = new UsuarioDto();
			edicionUsuario = false;
		} catch (SgdServicesException e) {
			log.error("Error en agregarUsuario de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al agregar el usuario"));
		}
		return "usuarios";
	}

	public String borrarUsuario(Long id) {
		try {
			usuarioService.deleteUsuario(id);
			usuarios = usuarioService.getUsuarios();
		} catch (SgdServicesException e) {
			log.error("Error en borrarUsuario de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido borrar el usuario"));
		}
		return "usuarios";
	}

	public String editarUsuario(Long id) {
		try {
			edicionUsuario = true;
			user = usuarioService.getUsuarioDto(id);
			return "/pages/gestion/usuario.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en editarUsuario de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido obtener el usuario"));
			return "usuarios";
		}
	}

	public String guardarRol() {
		try {
			EnumOperacion[] o = operaciones.getTarget().stream().map(EnumOperacion::valueOf)
					.toArray(EnumOperacion[]::new);
			rol.setOperaciones(o);
			gestionService.addRol(rol);
			roles = gestionService.getRoles();
			rol = new RolDto();
		} catch (SgdServicesException e) {
			log.error("Error en guardarRol de GestionController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ha ocurrido un error al agregar el rol"));
		}
		return "ver-roles";
	}

	public String nuevoRol() {
		rol = new RolDto();
		edicionRol = false;
		List<String> opeSource = Stream.of(EnumOperacion.values()).map(EnumOperacion::name)
				.collect(Collectors.toList());
		operaciones.setSource(opeSource);
		operaciones.setTarget(new ArrayList<>());
		return "/pages/gestion/rol.jsf?faces-redirect=true";
	}

	public String editarRol(Long id) {
		try {
			rol = gestionService.getRol(id);
			edicionRol = true;

			EnumOperacion[] opeTodas = EnumOperacion.values();
			List<EnumOperacion> source = new ArrayList<>();
			List<EnumOperacion> target = Arrays.stream(rol.getOperaciones()).toList();
			for (int i = 0; i < opeTodas.length; i++) {
				if (!target.contains(opeTodas[i])) {
					source.add(opeTodas[i]);
				}
			}
			List<String> opeSource = source.stream().map(EnumOperacion::name).collect(Collectors.toList());
			operaciones.setSource(opeSource);
			operaciones.setTarget(Arrays.stream(rol.getOperaciones()).map(EnumOperacion::name).toList());

			return "/pages/gestion/rol.jsf?faces-redirect=true";
		} catch (SgdServicesException e) {
			log.error("Error en editarRol de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido obtener el rol"));
			return "ver-roles";
		}
		
	}

	public String borrarRol(Long id) {
		try {
			gestionService.deleteRol(id);
			roles = gestionService.getRoles();
		} catch (SgdServicesException e) {
			log.error("Error en borrarRol de GestionController");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se ha podido borrar el rol"));
		}
		return "ver-roles";
	}
	
	public EnumTipoAdscripcion[] getItemsTipoAdscripcion() {
		return EnumTipoAdscripcion.values();
	}

	public String obtenerLabelEnum(String enumO) {
		return EnumOperacion.valueOf(enumO).getLabel();
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

	public UsuarioDto getUser() {
		return user;
	}

	public void setUser(UsuarioDto user) {
		this.user = user;
	}

	public List<UsuarioDto> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDto> usuarios) {
		this.usuarios = usuarios;
	}

	public List<UsuarioDto> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<UsuarioDto> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public RolDto getRol() {
		return rol;
	}

	public void setRol(RolDto rol) {
		this.rol = rol;
	}

	public DualListModel<String> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(DualListModel<String> operaciones) {
		this.operaciones = operaciones;
	}

	public boolean isEdicionRol() {
		return edicionRol;
	}

	public void setEdicionRol(boolean edicionRol) {
		this.edicionRol = edicionRol;
	}

	public List<RolDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDto> roles) {
		this.roles = roles;
	}

	public boolean isEdicionUsuario() {
		return edicionUsuario;
	}

	public void setEdicionUsuario(boolean edicionUsuario) {
		this.edicionUsuario = edicionUsuario;
	}

}