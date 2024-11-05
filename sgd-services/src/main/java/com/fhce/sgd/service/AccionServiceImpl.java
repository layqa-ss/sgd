package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.programas.AprobarDto;
import com.fhce.sgd.dto.programas.RevisionDto;
import com.fhce.sgd.model.programas.AccionPrograma;
import com.fhce.sgd.model.programas.AprobarPrograma;
import com.fhce.sgd.model.programas.RevisionPrograma;
import com.fhce.sgd.repository.AccionRepository;
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

	@Transactional(rollbackFor = Exception.class)
	public Long saveOrUpdateRevision(RevisionDto rDto) throws SgdServicesException {
		try {
			RevisionPrograma r = new RevisionPrograma();
			if (rDto.getId() != null) {
				r = (RevisionPrograma) accionRepository.findById(rDto.getId()).get();
			}

			r.setUsuario(usuarioService.getUsuario(rDto.getIdUsuario()));
			r.setPrograma(programaService.obtenerProgramaPorId(rDto.getIdPrograma()));

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
			log.error("Error en saveOrUpdateRevision de RevisionService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateRevision de RevisionService: " + e.getMessage(), e);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Long saveOrUpdateAprobacion(AprobarDto aDto) throws SgdServicesException {
		try {
			AprobarPrograma a = new AprobarPrograma();

			a.setUsuario(usuarioService.getUsuario(aDto.getIdUsuario()));
			a.setPrograma(programaService.obtenerProgramaPorId(aDto.getIdPrograma()));

			a.setComentarios(aDto.getComentarios());
			a.setDespachoFileName(aDto.getDespachoFileName());
			a.setDespachoData(aDto.getDespachoData());

			a = accionRepository.save(a);
			return a.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdateAprobacion de RevisionService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateAprobacion de RevisionService: " + e.getMessage(), e);
		}
	}

	public List<RevisionDto> getRevisionesPrograma(Long idPrograma) throws SgdServicesException {
		try {
			List<RevisionDto> revisionesDto = new ArrayList<RevisionDto>();
			Iterable<AccionPrograma> revisionesTodas = accionRepository.findAll();
			List<AccionPrograma> revisionesPrograma = StreamSupport.stream(revisionesTodas.spliterator(), false)
					.filter(r -> (r instanceof RevisionPrograma && r.getPrograma().getId().equals(idPrograma))).toList();
			for (AccionPrograma r : revisionesPrograma) {
				RevisionDto rDto = obtenerRevisionPorId((RevisionPrograma) r);
				revisionesDto.add(rDto);
			}
			revisionesDto.sort(Comparator.comparing(RevisionDto::getFecha_revision));
			return revisionesDto;
		} catch (Exception e) {
			log.error("Error en getRevisionesPrograma de RevisionService: " + e.getMessage());
			throw new SgdServicesException("Error en getRevisionesPrograma de RevisionService: " + e.getMessage(), e);
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
			return dto;
		} catch (Exception e) {
			log.error("Error en obtenerRevisionPorId de RevisionService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerRevisionPorId de RevisionService: " + e.getMessage(), e);
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
			log.error("Error en obtenerRevisionPorId de RevisionService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerRevisionPorId de RevisionService: " + e.getMessage(), e);
		}
	}
}
