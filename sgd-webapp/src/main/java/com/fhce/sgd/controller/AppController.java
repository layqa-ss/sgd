package com.fhce.sgd.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaIntegranteDto;
import com.fhce.sgd.model.enums.EnumConfig;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.enums.EnumOperacion;
import com.fhce.sgd.model.usuarios.CustomUsuarioDetails;
import com.fhce.sgd.service.ConfigService;
import com.fhce.sgd.service.UsuarioService;
import com.fhce.sgd.service.exception.SgdServicesException;

import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("appCtrl")
@Controller
@SessionScope
@Slf4j
public class AppController {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Value("${build.version}")
	private String buildVersion;

	private Long idUsuarioLogueado;
	private List<Long> unidadesUsuario;
	private List<Long> carrerasUsuario;
	private boolean sinAdscripcion = false;

	private boolean puedeDescargarPdfEnProceso = false;
	private boolean puedeDescargarPdfAprobado = false;

	private boolean puedeGestionarUsuarios = false;
	private boolean puedeGestionarUCA = false;
	private boolean puedeConfigurar = false;

	private boolean puedeCrearPrograma = false;
	private boolean puedeEditarPrograma = false;
	private boolean puedeEnviarCC = false;

	private boolean puedeSugerirCC = false;
	private boolean puedeAprobarCC = false;

	private boolean puedeEditarSug = false;

	private boolean puedeAprobarBedelias = false;

	private boolean puedeSugerirCAG = false;
	private boolean puedeAprobarCAG = false;

	private boolean puedeSugerirCG = false;
	private boolean puedeAprobarCG = false;

	private boolean puedeEditarAprobado = false;
	private boolean puedeSugerirCCAbrev = false;
	private boolean puedeAprobarCCAbrev = false;

	private boolean puedeEditarSugAbrev = false;

	private boolean puedeVerProgramas = false;
	private boolean puedeVerCambiosEstado = false;

	private boolean puedeDuplicarPrograma = false;

	@GetMapping("/login")
	public String login(Model model, UsuarioDto userDto) {

		model.addAttribute("user", userDto);
		return "login";
	}

	public String getUsuarioLogueado() {
		String username = "";
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((CustomUsuarioDetails) principal).getFullname();
				idUsuarioLogueado = ((CustomUsuarioDetails) principal).getId();
				unidadesUsuario = usuarioService.obtenerUnidadesUsuario(idUsuarioLogueado);
				carrerasUsuario = usuarioService.obtenerCarrerasUsuario(idUsuarioLogueado);
				sinAdscripcion = ((CustomUsuarioDetails) principal).isSinAdscripcion();
				cargarPermisos(((CustomUsuarioDetails) principal).getAuthorities());
			} else {
				username = principal.toString();
			}
		} catch (Exception e) {
			log.error("Error al obtener el usuario logueado");
			e.printStackTrace();
		}
		return username;
	}

	public void cargarPermisos(Collection<? extends GrantedAuthority> operaciones)
			throws SgdServicesException, ParseException {
		puedeDescargarPdfEnProceso = operaciones.contains(EnumOperacion.DESCARGAR_EN_PROCESO);
		puedeDescargarPdfAprobado = operaciones.contains(EnumOperacion.DESCARGAR_APROBADO);

		puedeGestionarUsuarios = operaciones.contains(EnumOperacion.GESTION_USUARIOS);
		puedeGestionarUCA = operaciones.contains(EnumOperacion.GESTION_UCA);
		puedeConfigurar = operaciones.contains(EnumOperacion.CONFIGURACIONES);

		puedeCrearPrograma = operaciones.contains(EnumOperacion.CREAR_PROGRAMA);
		puedeEditarPrograma = operaciones.contains(EnumOperacion.EDITAR_PROGRAMA) && checkPeriodoEdicion();
		puedeEnviarCC = operaciones.contains(EnumOperacion.ENVIAR_REVISION_CC);

		puedeSugerirCC = operaciones.contains(EnumOperacion.SUGERENCIAS_REVISION_CC);
		puedeAprobarCC = operaciones.contains(EnumOperacion.APROBAR_REVISION_CC);

		puedeEditarSug = operaciones.contains(EnumOperacion.EDITAR_SEGUN_SUGERENCIAS) && checkPeriodoEdicion();

		puedeAprobarBedelias = operaciones.contains(EnumOperacion.APROBAR_BEDELIAS);

		puedeSugerirCAG = operaciones.contains(EnumOperacion.SUGERENCIAS_REVISION_CAG);
		puedeAprobarCAG = operaciones.contains(EnumOperacion.APROBAR_REVISION_CAG);

		puedeSugerirCG = operaciones.contains(EnumOperacion.SUGERENCIAS_REVISION_CG);
		puedeAprobarCG = operaciones.contains(EnumOperacion.APROBAR_REVISION_CG);

		puedeEditarAprobado = operaciones.contains(EnumOperacion.EDITAR_APROBADO) && checkPeriodoEdicion();
		puedeSugerirCCAbrev = operaciones.contains(EnumOperacion.SUGERENCIAS_REVISION_CC_ABREV);
		puedeAprobarCCAbrev = operaciones.contains(EnumOperacion.APROBAR_REVISION_CC_ABREV);

		puedeEditarSugAbrev = operaciones.contains(EnumOperacion.EDITAR_SEGUN_SUGERENCIAS_ABREV)
				&& checkPeriodoEdicion();

		puedeVerProgramas = operaciones.contains(EnumOperacion.VER_PROGRAMAS);
		puedeVerCambiosEstado = operaciones.contains(EnumOperacion.VER_CAMBIOS_ESTADO);

		puedeDuplicarPrograma = operaciones.contains(EnumOperacion.DUPLICAR_PROGRAMA);

	}

	public boolean checkPeriodoEdicion() throws SgdServicesException, ParseException {
		String dateStrDesde = configService.getConfigDtoByEnum(EnumConfig.EDICION_PROGRAMAS_DESDE).getValue();
		String dateStrHasta = configService.getConfigDtoByEnum(EnumConfig.EDICION_PROGRAMAS_HASTA).getValue();
		Date hoy = new Date();
		Date dateDesde = new SimpleDateFormat("dd/MM/yyyy").parse(dateStrDesde);
		Date dateHasta = new SimpleDateFormat("dd/MM/yyyy").parse(dateStrHasta);
		if (dateDesde.before(hoy) && dateHasta.after(hoy)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean perteneceEquipoDocente(ProgramaDto programa) {
		boolean equipo = false;
		for (ProgramaIntegranteDto i : programa.getIntegrantes()) {
			if (i.getIdUsuario() == idUsuarioLogueado) {
				equipo = true;
				break;
			}
		}
		return equipo;
	}

	public boolean checkUA(ProgramaDto programa) {
		boolean checkUnidades = false;
		if (unidadesUsuario != null) {
			for (UnidadAcademicaDto uaDto : programa.getUnidades()) {
				if (unidadesUsuario.contains(uaDto.getId())) {
					return true;
				}
			}

			// Check si alguna carrera del programa pertenece a una unidad del usuario
			for (CarreraDto cDto : programa.getCarreras()) {
				if (unidadesUsuario.contains(cDto.getUaId())) {
					return true;
				}
			}
		}
		return checkUnidades;
	}

	public boolean checkCarrera(ProgramaDto programa) {
		boolean checkCarreras = false;
		if (carrerasUsuario != null) {
			for (CarreraDto cDto : programa.getCarreras()) {
				if (carrerasUsuario.contains(cDto.getId())) {
					return true;
				}
			}
		}
		return checkCarreras;
	}

	public boolean puedeEditarPrograma(ProgramaDto programa) {
		boolean puedeEditar = (programa.getEstado() == EnumEstadoPrograma.CREADO && puedeEditarPrograma)
				|| (programa.getEstado() == EnumEstadoPrograma.SUGERENCIAS && puedeEditarSug);
		boolean autoria = programa.getIdUsuario() == idUsuarioLogueado;
		return puedeEditar && (autoria || perteneceEquipoDocente(programa) || sinAdscripcion);
	}

	public boolean puedeDescargarPrograma(EnumEstadoPrograma estado) {
		return (estado == EnumEstadoPrograma.APROBADO && puedeDescargarPdfAprobado)
				|| (estado != EnumEstadoPrograma.APROBADO && puedeDescargarPdfEnProceso);
	}

	public boolean puedeEnviarCC(ProgramaDto programa) {
		boolean puedeEnviar = programa.getEstado() == EnumEstadoPrograma.CREADO && puedeEnviarCC;
		boolean autoria = programa.getIdUsuario() == idUsuarioLogueado;
		return puedeEnviar && (autoria || perteneceEquipoDocente(programa) || sinAdscripcion);
	}

	public boolean puedeHacerRevision(ProgramaDto programa) {
		EnumEstadoPrograma estado = programa.getEstado();
		boolean puedeRevisar = (estado == EnumEstadoPrograma.REVISION_CC && puedeSugerirCC)
				|| (estado == EnumEstadoPrograma.REVISION_CAG && puedeSugerirCAG)
				|| (estado == EnumEstadoPrograma.REVISION_CG && puedeSugerirCG)
				|| (estado == EnumEstadoPrograma.REVISION_CC_ABR && puedeSugerirCCAbrev);
		return puedeRevisar && (checkUA(programa) || checkCarrera(programa) || sinAdscripcion);
	}

	public boolean puedeAprobar(ProgramaDto programa) {
		EnumEstadoPrograma estado = programa.getEstado();
		boolean puedeAprobar = (estado == EnumEstadoPrograma.REVISION_CC && puedeAprobarCC)
				|| (estado == EnumEstadoPrograma.REVISION_CAG && puedeAprobarCAG)
				|| (estado == EnumEstadoPrograma.REVISION_CG && puedeAprobarCG)
				|| (estado == EnumEstadoPrograma.REVISION_CC_ABR && puedeAprobarCCAbrev);
		boolean bedelias = (estado == EnumEstadoPrograma.BEDELIAS && puedeAprobarBedelias);
		return bedelias || (puedeAprobar && (checkUA(programa) || checkCarrera(programa) || sinAdscripcion));
	}

	public boolean puedeEditarAprobado(ProgramaDto programa) {
		EnumEstadoPrograma estado = programa.getEstado();
		boolean autoria = programa.getIdUsuario() == idUsuarioLogueado;
		boolean puedeEditarApr = (estado == EnumEstadoPrograma.APROBADO && puedeEditarAprobado)
				|| (estado == EnumEstadoPrograma.SUGERENCIAS_ABR && puedeEditarSugAbrev);
		return puedeEditarApr && (autoria || perteneceEquipoDocente(programa) || sinAdscripcion);
	}

	public boolean puedeDuplicarPrograma(ProgramaDto programa) {
		boolean autoria = programa.getIdUsuario() == idUsuarioLogueado;
		return (programa.getEstado() == EnumEstadoPrograma.APROBADO && puedeDuplicarPrograma)
				&& (autoria || perteneceEquipoDocente(programa) || sinAdscripcion);
	}

	public boolean puedeVerHistorial(ProgramaDto programa) {
		return puedeVerCambiosEstado && (checkUA(programa) || checkCarrera(programa) || sinAdscripcion);
	}

	public boolean isPuedeEnviarCC() {
		return puedeEnviarCC;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public boolean isPuedeDescargarPdfEnProceso() {
		return puedeDescargarPdfEnProceso;
	}

	public boolean isPuedeDescargarPdfAprobado() {
		return puedeDescargarPdfAprobado;
	}

	public boolean isPuedeGestionarUsuarios() {
		return puedeGestionarUsuarios;
	}

	public boolean isPuedeGestionarUCA() {
		return puedeGestionarUCA;
	}

	public boolean isPuedeConfigurar() {
		return puedeConfigurar;
	}

	public boolean isPuedeCrearPrograma() {
		return puedeCrearPrograma;
	}

	public boolean isPuedeEditarPrograma() {
		return puedeEditarPrograma;
	}

	public boolean isPuedeSugerirCC() {
		return puedeSugerirCC;
	}

	public boolean isPuedeAprobarCC() {
		return puedeAprobarCC;
	}

	public boolean isPuedeEditarSug() {
		return puedeEditarSug;
	}

	public boolean isPuedeAprobarBedelias() {
		return puedeAprobarBedelias;
	}

	public boolean isPuedeSugerirCAG() {
		return puedeSugerirCAG;
	}

	public boolean isPuedeAprobarCAG() {
		return puedeAprobarCAG;
	}

	public boolean isPuedeSugerirCG() {
		return puedeSugerirCG;
	}

	public boolean isPuedeAprobarCG() {
		return puedeAprobarCG;
	}

	public boolean isPuedeEditarAprobado() {
		return puedeEditarAprobado;
	}

	public boolean isPuedeSugerirCCAbrev() {
		return puedeSugerirCCAbrev;
	}

	public boolean isPuedeAprobarCCAbrev() {
		return puedeAprobarCCAbrev;
	}

	public boolean isPuedeEditarSugAbrev() {
		return puedeEditarSugAbrev;
	}

	public boolean isPuedeVerProgramas() {
		return puedeVerProgramas;
	}

	public boolean isPuedeVerCambiosEstado() {
		return puedeVerCambiosEstado;
	}

	public Long getIdUsuarioLogueado() {
		return idUsuarioLogueado;
	}

	public boolean isSinAdscripcion() {
		return sinAdscripcion;
	}

}