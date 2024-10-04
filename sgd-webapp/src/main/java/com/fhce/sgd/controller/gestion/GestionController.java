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
import com.fhce.sgd.service.GestionService;
import com.fhce.sgd.service.UsuarioService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("gestionController")
@ViewScoped
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

	@PostConstruct
	public void init() {
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

	public String agregarUsuario() {
		usuarioService.save(user);
		usuarios = usuarioService.getUsuarios();
		user = new UsuarioDto();
		return "usuarios";
	}

	public String borrarUsuario(Long id) {
		usuarioService.deleteUsuario(id);
		usuarios = usuarioService.getUsuarios();
		return "usuarios";
	}

	public String guardarRol() {
		EnumOperacion[] o = operaciones.getTarget().stream().map(EnumOperacion::valueOf).toArray(EnumOperacion[]::new);
		rol.setOperaciones(o);
		gestionService.addRol(rol);
		roles = gestionService.getRoles();
		rol = new RolDto();
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
	}

	public String borrarRol(Long id) {
		gestionService.deleteRol(id);
		roles = gestionService.getRoles();
		return "ver-roles";
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

}