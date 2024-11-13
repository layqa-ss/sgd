package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.programas.AccionDto;
import com.fhce.sgd.dto.programas.AprobarDto;
import com.fhce.sgd.dto.programas.RevisionDto;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.programas.AccionPrograma;
import com.fhce.sgd.model.programas.AprobarPrograma;
import com.fhce.sgd.model.programas.RevisionPrograma;
import com.fhce.sgd.repository.AccionRepository;
import com.fhce.sgd.repository.AprobacionRepository;
import com.fhce.sgd.repository.RevisionRepository;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccionServiceImpl implements AccionService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProgramaService programaService;

	@Autowired
	private AccionRepository accionRepository;
	
	@Autowired
	private RevisionRepository revisionRepository;
	
	@Autowired
	private AprobacionRepository aprobarRepository;

	@Transactional(rollbackFor = Exception.class)
	public Long saveOrUpdateRevision(RevisionDto rDto) throws SgdServicesException {
		try {
			RevisionPrograma r = new RevisionPrograma();
			if (rDto.getId() != null) {
				r = (RevisionPrograma) accionRepository.findById(rDto.getId()).get();
			}

			r.setUsuario(usuarioService.getUsuario(rDto.getIdUsuario()));
			r.setPrograma(programaService.obtenerProgramaPorId(rDto.getIdPrograma()));
			r.setEstado(rDto.getEstadoPrograma());
			
			r.setAdecDescrSug(rDto.getAdecDescrSug());
			r.setAdecSug(rDto.getAdecSug());
			r.setAprobDirectaSug(rDto.getAprobDirectaSug());
			r.setAreaSug(rDto.getAreaSug());
			r.setBiblioSug(rDto.getBiblioSug());
			r.setCarreraSug(rDto.getCarreraSug());
			r.setContSug(rDto.getContSug());
			r.setCreditosSug(rDto.getCreditosSug());
			r.setDocentesSug(rDto.getDocentesSug());
			r.setDuracionSug(rDto.getDuracionSug());
			r.setEvaluacionSug(rDto.getEvaluacionSug());
			r.setFormatoSug(rDto.getFormatoSug());
			r.setIngresoSug(rDto.getIngresoSug());
			r.setMetodologiaSug(rDto.getMetodologiaSug());
			r.setModalidadSug(rDto.getModalidadSug());
			r.setObjSug(rDto.getObjSug());
			r.setOtrosServSug(rDto.getOtrosServSug());
			r.setRecomSug(rDto.getRecomSug());
			r.setRegimenSug(rDto.getRegimenSug());
			r.setRequisitosCualesSug(rDto.getRequisitosCualesSug());
			r.setRequisitosSug(rDto.getRequisitosSug());
			r.setSemestreSug(rDto.getSemestreSug());
			r.setTareas75obligSug(rDto.getTareas75obligSug());
			r.setUaSug(rDto.getUaSug());
			r.setUcSug(rDto.getUcSug());

			r = accionRepository.save(r);
			return r.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdateAccionService de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateRevision de AccionService: " + e.getMessage(), e);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Long saveOrUpdateAprobacion(AprobarDto aDto) throws SgdServicesException {
		try {
			AprobarPrograma a = new AprobarPrograma();

			a.setUsuario(usuarioService.getUsuario(aDto.getIdUsuario()));
			a.setPrograma(programaService.obtenerProgramaPorId(aDto.getIdPrograma()));
			a.setEstado(aDto.getEstadoPrograma());

			a.setComentarios(aDto.getComentarios());
			a.setDespachoFileName(aDto.getDespachoFileName());
			a.setDespachoData(aDto.getDespachoData());

			a = accionRepository.save(a);
			return a.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdateAprobacion de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateAprobacion de AccionService: " + e.getMessage(), e);
		}
	}

	public List<RevisionDto> getRevisionesPrograma(Long idPrograma) throws SgdServicesException {
		try {
			List<RevisionDto> revisionesDto = new ArrayList<RevisionDto>();
			Iterable<RevisionPrograma> revisionesTodas = revisionRepository.findByProgramaId(idPrograma);
			List<RevisionPrograma> revisionesPrograma = StreamSupport.stream(revisionesTodas.spliterator(), false)
					.filter(r -> (r.getPrograma().getId().equals(idPrograma))).toList();
			for (AccionPrograma r : revisionesPrograma) {
				RevisionDto rDto = obtenerRevisionPorId((RevisionPrograma) r);
				revisionesDto.add(rDto);
			}
			revisionesDto.sort(Comparator.comparing(RevisionDto::getFecha_revision));
			return revisionesDto;
		} catch (Exception e) {
			log.error("Error en getRevisionesPrograma de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en getRevisionesPrograma de AccionService: " + e.getMessage(), e);
		}

	}
	
	public List<RevisionDto> getRevisionesProgramaAbrev(Long idPrograma) throws SgdServicesException {
		try {
			List<RevisionDto> revisionesDto = new ArrayList<RevisionDto>();
			Iterable<RevisionPrograma> revisionesTodas = revisionRepository.findByProgramaId(idPrograma);
			List<RevisionPrograma> revisionesPrograma = StreamSupport.stream(revisionesTodas.spliterator(), false)
					.filter(r -> (r.getPrograma().getId().equals(idPrograma) && r.getEstado() == EnumEstadoPrograma.REVISION_CC_ABR)).toList();
			for (AccionPrograma r : revisionesPrograma) {
				RevisionDto rDto = obtenerRevisionPorId((RevisionPrograma) r);
				revisionesDto.add(rDto);
			}
			revisionesDto.sort(Comparator.comparing(RevisionDto::getFecha_revision));
			return revisionesDto;
		} catch (Exception e) {
			log.error("Error en getRevisionesProgramaAbrev de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en getRevisionesProgramaAbrev de AccionService: " + e.getMessage(), e);
		}

	}
	
	public List<AccionDto> getAccionesPrograma(Long idPrograma) throws SgdServicesException {
		try {
			List<AccionDto> accionesDto = new ArrayList<AccionDto>();
			List<RevisionPrograma> revisiones = revisionRepository.findByProgramaId(idPrograma);
			List<AprobarPrograma> aprobaciones = aprobarRepository.findByProgramaId(idPrograma);
			for (AccionPrograma r : revisiones) {
				RevisionDto rDto = obtenerRevisionPorId((RevisionPrograma) r);
				accionesDto.add(rDto);
			}
			for (AccionPrograma a : aprobaciones) {
				AprobarDto aDto = obtenerAprobacionPorId((AprobarPrograma) a);
				accionesDto.add(aDto);
			}
			accionesDto.sort(Comparator.comparing(AccionDto::getFecha_revision));
			return accionesDto;
		} catch (Exception e) {
			log.error("Error en getAccionesPrograma de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en getAccionesPrograma de AccionService: " + e.getMessage(), e);
		}

	}
	
	public boolean aprobacionBedelias(Long idPrograma) throws SgdServicesException {
		try {
			boolean aprobacion = false;
			List<AprobarPrograma> aprobaciones = aprobarRepository.findByProgramaId(idPrograma);
			for (AccionPrograma a : aprobaciones) {
				if (a.getEstado() == EnumEstadoPrograma.BEDELIAS) {
					aprobacion = true;
					break;
				}
			}
			return aprobacion;
		} catch (Exception e) {
			log.error("Error en aprobacionBedelias de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en aprobacionBedelias de AccionService: " + e.getMessage(), e);
		}

	}
	
	private AprobarDto obtenerAprobacionPorId(AprobarPrograma a) throws SgdServicesException {
		try {
			AprobarDto dto = new AprobarDto();
			dto.setComentarios(a.getComentarios());
			dto.setDespachoData(a.getDespachoData());
			dto.setDespachoFileName(a.getDespachoFileName());
			dto.setEstadoPrograma(a.getEstado());
			dto.setFecha_revision(a.getFecha());
			dto.setId(a.getId());
			dto.setIdPrograma(a.getPrograma().getId());
			dto.setIdUsuario(a.getUsuario().getId());
			dto.setNombre_usuario(a.getUsuario().getFullname());
			return dto;
		} catch (Exception e) {
			log.error("Error en obtenerAprobacionPorId de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerAprobacionPorId de AccionService: " + e.getMessage(), e);
		}
	}
	
	private RevisionDto obtenerRevisionPorId(RevisionPrograma r) throws SgdServicesException {
		try {
			RevisionDto dto = new RevisionDto();
			dto.setAdecDescrSug(r.getAdecDescrSug());
			dto.setAdecSug(r.getAdecSug());
			dto.setAprobDirectaSug(r.getAprobDirectaSug());
			dto.setAreaSug(r.getAreaSug());
			dto.setBiblioSug(r.getBiblioSug());
			dto.setCarreraSug(r.getCarreraSug());
			dto.setContSug(r.getContSug());
			dto.setCreditosSug(r.getCreditosSug());
			dto.setDocentesSug(r.getDocentesSug());
			dto.setDuracionSug(r.getDuracionSug());
			dto.setEvaluacionSug(r.getEvaluacionSug());
			dto.setFecha_revision(r.getFecha());
			dto.setFormatoSug(r.getFormatoSug());
			dto.setId(r.getId());
			dto.setIdPrograma(r.getPrograma().getId());
			dto.setIdUsuario(r.getUsuario().getId());
			dto.setIngresoSug(r.getIngresoSug());
			dto.setMetodologiaSug(r.getMetodologiaSug());
			dto.setModalidadSug(r.getModalidadSug());
			dto.setObjSug(r.getObjSug());
			dto.setOtrosServSug(r.getOtrosServSug());
			dto.setRecomSug(r.getRecomSug());
			dto.setRegimenSug(r.getRegimenSug());
			dto.setRequisitosCualesSug(r.getRequisitosCualesSug());
			dto.setRequisitosSug(r.getRequisitosSug());
			dto.setSemestreSug(r.getSemestreSug());
			dto.setTareas75obligSug(r.getTareas75obligSug());
			dto.setUaSug(r.getUaSug());
			dto.setUcSug(r.getUcSug());
			dto.setNombre_usuario(r.getUsuario().getFullname());
			
			dto.setEstadoPrograma(r.getEstado());
			return dto;
		} catch (Exception e) {
			log.error("Error en obtenerRevisionPorId de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerRevisionPorId de AccionService: " + e.getMessage(), e);
		}
	}

	public RevisionDto obtenerRevisionPorId(Long id) throws SgdServicesException {
		try {
			RevisionPrograma r = (RevisionPrograma) accionRepository.findById(id).orElse(null);
			if(r!=null) {
				return obtenerRevisionPorId(r);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en obtenerRevisionPorId de AccionService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerRevisionPorId de AccionService: " + e.getMessage(), e);
		}
	}
}
