package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.enums.EnumTipoAdscripcion;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;
import com.fhce.sgd.model.usuarios.Rol;
import com.fhce.sgd.model.usuarios.Usuario;
import com.fhce.sgd.repository.RolRepository;
import com.fhce.sgd.repository.UsuarioRepository;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepo;

	@Autowired
	private GestionService gestionService;

	public UsuarioDto getUsuarioDto(Long id) throws SgdServicesException {
		try {
			Optional<Usuario> u = usuarioRepository.findById(id);
			if (u.isPresent()) {
				UsuarioDto userDto = new UsuarioDto(u.get().getId(), u.get().getUsername(), u.get().getPassword(),
						u.get().getCreationDate(), u.get().getFullname(), u.get().getRol().getId());
				if (u.get().getTipoAdscripcion() != null) {
					userDto.setTipoAdscripcion(u.get().getTipoAdscripcion());
					if (userDto.getTipoAdscripcion() == EnumTipoAdscripcion.UA) {
						List<UnidadAcademicaDto> unidadesAcademicas = new ArrayList<>();
						for (UnidadAcademica ua : u.get().getUnidadesAcademicas()) {
							UnidadAcademicaDto uaDto = new UnidadAcademicaDto(ua.getId(), ua.getNombreUA(),
									ua.isHabilitada());
							unidadesAcademicas.add(uaDto);
						}
						userDto.setUnidades(unidadesAcademicas);
					} else if (userDto.getTipoAdscripcion() == EnumTipoAdscripcion.CARRERA) {
						List<CarreraDto> carreras = new ArrayList<>();
						for (Carrera c : u.get().getCarreras()) {
							CarreraDto cDto = new CarreraDto(c.getId(), c.getNombreCarrera(), c.getUa().getId(),
									c.getUa().getNombreUA(), c.isHabilitada());
							carreras.add(cDto);
						}
						userDto.setCarreras(carreras);
					}
				}
				return userDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuarioDto de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en getUsuarioDto de UsuarioService: " + e.getMessage(), e);
		}

	}

	public Usuario getUsuario(Long id) throws SgdServicesException {
		try {
			Optional<Usuario> u = usuarioRepository.findById(id);
			if (u.isPresent()) {
				return u.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en getUsuario de UsuarioService: " + e.getMessage(), e);
		}

	}

	public Usuario getUsuarioByUsername(String username) throws SgdServicesException {
		try {
			Iterable<Usuario> usuarios = usuarioRepository.findAll();
			List<Usuario> user = StreamSupport.stream(usuarios.spliterator(), false)
					.filter(u -> u.getUsername().equals(username)).toList();
			if (!user.isEmpty()) {
				return user.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuarioByUsername de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en getUsuarioByUsername de UsuarioService: " + e.getMessage(), e);
		}
	}

	public Long saveOrUpdateUsuario(UsuarioDto userDto) throws SgdServicesException {
		try {
			Usuario user = new Usuario();
			if (userDto.getId() != null) {
				user = usuarioRepository.findById(userDto.getId()).get();
			}
			user.setFullname(userDto.getFullname());
			user.setCreationDate(userDto.getCreationDate());
			user.setPassword(userDto.getPassword());
			user.setUsername(userDto.getUsername());
			Rol r = rolRepo.findById(userDto.getIdRol()).get();
			user.setRol(r);

			if (userDto.getTipoAdscripcion() != null) {
				user.setTipoAdscripcion(userDto.getTipoAdscripcion());
				if (userDto.getTipoAdscripcion() == EnumTipoAdscripcion.UA) {
					Set<UnidadAcademica> unidadesAcademicas = new HashSet<UnidadAcademica>();
					for (UnidadAcademicaDto uaDto : userDto.getUnidades()) {
						UnidadAcademica ua = gestionService.getUnidadAcademica(uaDto.getId());
						unidadesAcademicas.add(ua);
					}
					user.setUnidadesAcademicas(unidadesAcademicas);
				} else if (userDto.getTipoAdscripcion() == EnumTipoAdscripcion.CARRERA) {
					Set<Carrera> carreras = new HashSet<Carrera>();

					for (CarreraDto cDto : userDto.getCarreras()) {
						Carrera c = gestionService.getCarrera(cDto.getId());
						carreras.add(c);
					}
					user.setCarreras(carreras);
				}
			}
			user = usuarioRepository.save(user);
			return user.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdateUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateUsuario de UsuarioService: " + e.getMessage(), e);
		}

	}

	public List<UsuarioDto> getUsuarios() throws SgdServicesException {
		try {
			Iterable<Usuario> usuariosTodos = usuarioRepository.findAll();
			List<Usuario> usuarios = StreamSupport.stream(usuariosTodos.spliterator(), false).toList();
			if (!usuarios.isEmpty()) {
				List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
				for (Usuario u : usuarios) {
					UsuarioDto uDto = new UsuarioDto(u.getId(), u.getUsername(), u.getPassword(), u.getCreationDate(),
							u.getFullname(), u.getRol().getId());
					usuariosDto.add(uDto);
				}
				return usuariosDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuarios de UsuarioService: " + e.getMessage());
			e.printStackTrace();
			throw new SgdServicesException("Error en getUsuarios de UsuarioService: " + e.getMessage(), e);
		}

	}

	public void deleteUsuario(Long id) throws SgdServicesException {
		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Error en deleteUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en deleteUsuario de UsuarioService: " + e.getMessage(), e);
		}

	}

	public List<Long> obtenerUnidadesUsuario(Long id) throws SgdServicesException {
		try {
			Usuario user = getUsuario(id);
			if (user.getTipoAdscripcion() == EnumTipoAdscripcion.UA) {
				List<Long> uaIds = new ArrayList<Long>();
				for (UnidadAcademica ua : user.getUnidadesAcademicas()) {
					uaIds.add(ua.getId());
				}
				return uaIds;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en obtenerUnidadesUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerUnidadesUsuario de UsuarioService: " + e.getMessage(), e);
		}

	}

	public List<Long> obtenerCarrerasUsuario(Long id) throws SgdServicesException {
		try {
			Usuario user = getUsuario(id);
			if (user.getTipoAdscripcion() == EnumTipoAdscripcion.CARRERA) {
				List<Long> carreraIds = new ArrayList<Long>();
				for (Carrera c : user.getCarreras()) {
					carreraIds.add(c.getId());
				}
				return carreraIds;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en obtenerCarrerasUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerCarrerasUsuario de UsuarioService: " + e.getMessage(), e);
		}
	}

}
