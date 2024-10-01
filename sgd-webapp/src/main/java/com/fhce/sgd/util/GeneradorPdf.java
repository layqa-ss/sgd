package com.fhce.sgd.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.programas.BibliografiaDto;
import com.fhce.sgd.dto.programas.ProgramaIntegranteDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;
import com.fhce.sgd.model.enums.EnumDuracion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneradorPdf {

	public StreamedContent generarPdf(ProgramaNuevoDto pr) {

		try {

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			Path path = Paths.get("sgd-webapp/src/main/resources/logo2.png");
			Document document = new Document();
			PdfWriter.getInstance(document, os);

			document.open();

			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.scaleToFit(200, 200);
			document.add(img);

			Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
			Font fontRegular = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
			Font fontRegularSmall = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
			Chunk chunk = new Chunk("Facultad de Humanidades y Ciencias de la Educación", fontBold);
			Paragraph p = new Paragraph(chunk);
			p.setSpacingAfter(15);
			document.add(p);

			Chunk ucLabel = new Chunk("Unidad curricular: ", fontBold);
			Chunk ucValue = new Chunk(pr.getNombreUC(), fontRegular);
			Paragraph pUc = new Paragraph();
			pUc.add(ucLabel);
			pUc.add(ucValue);
			pUc.setSpacingAfter(15);
			document.add(pUc);

			String unidades = "";
			for (UnidadAcademicaDto uaDto : pr.getUnidades()) {
				unidades += uaDto.getNombre() + ", ";
			}
			Chunk uaLabel = new Chunk(pr.getUnidades().size() > 1 ? "Unidades académicas: " : "Unidad académica: ",
					fontBold);
			Chunk uaValue = new Chunk(unidades.substring(0, unidades.length() - 2), fontRegular);
			Paragraph pUa = new Paragraph();
			pUa.add(uaLabel);
			pUa.add(uaValue);
			pUa.setSpacingAfter(15);
			document.add(pUa);

			String carreras = "";
			for (CarreraDto cDto : pr.getCarreras()) {
				carreras += cDto.getNombre() + ", ";
			}
			Chunk carreraLabel = new Chunk(pr.getCarreras().size() > 1 ? "Carreras: " : "Carrera: ", fontBold);
			Chunk carreraValue = new Chunk(carreras.substring(0, carreras.length() - 2), fontRegular);
			Paragraph pCarrera = new Paragraph();
			pCarrera.add(carreraLabel);
			pCarrera.add(carreraValue);
			document.add(pCarrera);
			Paragraph pAclaracionCarrera = new Paragraph();
			pAclaracionCarrera.add(new Chunk(
					"La unidad curricular podrá ser acreditada también en carreras que no hayan sido seleccionadas o incluidas en este programa.",
					fontRegularSmall));
			pAclaracionCarrera.setSpacingAfter(15);
			document.add(pAclaracionCarrera);

			Chunk areaLabel = new Chunk("Area temática por carrera", fontBold);
			Paragraph pArea = new Paragraph();
			pArea.setSpacingAfter(4);
			pArea.add(areaLabel);
			document.add(pArea);

			for (CarreraDto a : pr.getCarreras()) {
				Paragraph pAreaValue = new Paragraph();
				pAreaValue.add(new Chunk(a.getNombre() + " - " + a.getAreaNombre(), fontRegular));
				document.add(pAreaValue);
			}
			document.add(new Paragraph("\n"));

			Chunk duracionLabel = new Chunk("Duración: ", fontBold);
			Chunk duracionValue = new Chunk(
					pr.getDuracion() != EnumDuracion.OTRO ? pr.getDuracion().getLabel() : pr.getDuracionOtro(),
					fontRegular);
			Paragraph pDuracion = new Paragraph();
			pDuracion.add(duracionLabel);
			pDuracion.add(duracionValue);
			pDuracion.setSpacingAfter(15);
			document.add(pDuracion);

			if (pr.getDuracion() != EnumDuracion.ANUAL) {
				Chunk semestreLabel = new Chunk("Semestre: ", fontBold);
				Chunk semestreValue = new Chunk(pr.getSemestre().getLabel(), fontRegular);
				Paragraph pSemestre = new Paragraph();
				pSemestre.add(semestreLabel);
				pSemestre.add(semestreValue);
				pSemestre.setSpacingAfter(15);
				document.add(pSemestre);
			}

			Chunk ingresoLabel = new Chunk("La unidad curricular está recomendada para estudiantes de ingreso: ",
					fontBold);
			Chunk ingresoValue = new Chunk(pr.isIngreso() ? "SI" : "NO", fontRegular);
			Paragraph pIngreso = new Paragraph();
			pIngreso.add(ingresoLabel);
			pIngreso.add(ingresoValue);
			pIngreso.setSpacingAfter(15);
			document.add(pIngreso);

			if (!pr.isIngreso()) {
				Chunk requisitosLabel = new Chunk(
						"Requisitos de inscripción (unidades curriculares previas o cantidad de créditos obtenidos): ",
						fontBold);
				Chunk requisitosValue = new Chunk(pr.isRequisitos() ? "SI" : "NO", fontRegular);
				Paragraph pRequisitos = new Paragraph();
				pRequisitos.add(requisitosLabel);
				pRequisitos.add(requisitosValue);
				pRequisitos.setSpacingAfter(15);
				document.add(pRequisitos);

				if (pr.isRequisitos()) {
					Chunk cualesValue = new Chunk(pr.getRequisitosCuales(), fontRegular);
					Paragraph pCuales = new Paragraph();
					pCuales.add(cualesValue);
					pCuales.setSpacingAfter(15);
					document.add(pCuales);
				}
			}

			if (!pr.isIngreso() && pr.getRecomendaciones() != null) {
				Chunk recomLabel = new Chunk("Conocimientos previos o grados de avance en la carrera recomendados: ",
						fontBold);
				Chunk recomValue = new Chunk(pr.getRecomendaciones(), fontRegular);
				Paragraph pRecom = new Paragraph();
				pRecom.add(recomLabel);
				pRecom.add(recomValue);
				pRecom.setSpacingAfter(15);
				document.add(pRecom);
			}

			Chunk otroServ = new Chunk(
					"Unidad curricular ofertada como electiva para otros servicios universitarios o terciarios: ",
					fontBold);
			Chunk otroServV = new Chunk(pr.isOtrosServicios() ? "SI" : "NO", fontRegular);
			Paragraph pOtroServ = new Paragraph();
			pOtroServ.add(otroServ);
			pOtroServ.add(otroServV);
			pOtroServ.setSpacingAfter(15);
			document.add(pOtroServ);

			Paragraph pDocentes = new Paragraph();
			Chunk docentes = new Chunk("Integrantes del equipo docente", fontBold);
			pDocentes.add(docentes);
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			PdfPCell columnHeader = new PdfPCell(new Phrase("Rol", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Cargo", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Nombre", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Unidad Académica u otra adscripción institucional", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Subunidad académica u otra adscripción institucional", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);

			table.setHeaderRows(1);
			for (ProgramaIntegranteDto i : pr.getIntegrantes()) {
				table.addCell(i.getRol().getLabel());
				table.addCell(i.getCargo().getLabel());
				table.addCell(i.getNombre_docente());
				table.addCell(i.getUnidad_academica());
				table.addCell(i.getSubunidad_academica());
			}
			pDocentes.add(table);
			document.add(pDocentes);

			document.newPage();

			Paragraph pCreditos = new Paragraph();
			Chunk creditos = new Chunk("Créditos y carga horaria del curso", fontBold);
			pCreditos.add(creditos);
			PdfPTable tableCreditos = new PdfPTable(6);
			tableCreditos.setWidthPercentage(100);

			columnHeader = new PdfPCell(new Phrase("", fontBold));
			columnHeader.setColspan(5);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Horas", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Con acompañamiento directo del/la docente", fontBold));
			columnHeader.setColspan(2);
			columnHeader.setRowspan(3);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Horas aula, presenciales o virtuales", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasAula().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Trabajo de campo o prácticas", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasTrabajosConAcompa().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(
					pr.getOtrosConAcompa() != null ? "Otros (" + pr.getOtrosConAcompa() + ")" : "Otros", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasOtrosConAcompa().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(
					new Phrase("Sin acompañamiento directo del/la docente (estudio autónomo)", fontBold));
			columnHeader.setColspan(2);
			columnHeader.setRowspan(5);
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Lecturas y estudio de materiales varios", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasLecturas().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(
					new Phrase("Tareas y actividades varias (individuales, grupales, actividades en EVA)", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasTareas().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Trabajo de campo o prácticas", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasTrabajosSinAcompa().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Trabajos finales", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasTrabajosFinales().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(
					pr.getOtrosSinAcompa() != null ? "Otros (" + pr.getOtrosSinAcompa() + ")" : "Otros", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasOtrosSinAcompa().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Horas totales previstas del curso", fontBold));
			columnHeader.setColspan(2);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getHorasTotales().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("", fontBold));
			columnHeader.setColspan(3);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Créditos", fontBold));
			columnHeader.setColspan(2);
			tableCreditos.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase(pr.getCreditos().toString(), fontRegular));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableCreditos.addCell(columnHeader);

			pCreditos.add(tableCreditos);
			pCreditos.setSpacingAfter(15);
			document.add(pCreditos);

			Chunk objetivos = new Chunk("Objetivos \n", fontBold);
			Chunk objetivosV = new Chunk(pr.getObjetivos(), fontRegular);
			Paragraph pObj = new Paragraph();
			pObj.add(objetivos);
			pObj.add(objetivosV);
			pObj.setSpacingAfter(15);
			document.add(pObj);

			Chunk contenidos = new Chunk("Contenidos \n ", fontBold);
			Chunk contenidosV = new Chunk(pr.getObjetivos(), fontRegular);
			Paragraph pCont = new Paragraph();
			pCont.add(contenidos);
			pCont.add(contenidosV);
			pCont.setSpacingAfter(15);
			document.add(pCont);

			Chunk formato = new Chunk("La Unidad Curricular es: ", fontBold);
			Chunk formatoV = new Chunk(pr.getFormato().getLabel(), fontRegular);
			Paragraph pFormato = new Paragraph();
			pFormato.add(formato);
			pFormato.add(formatoV);
			pFormato.setSpacingAfter(15);
			document.add(pFormato);

			Chunk modalidad = new Chunk("Modalidad: ", fontBold);
			Chunk modalidadV = new Chunk(pr.getModalidad().getLabel(), fontRegular);
			Paragraph pModalidad = new Paragraph();
			pModalidad.add(modalidad);
			pModalidad.add(modalidadV);
			pModalidad.setSpacingAfter(15);
			document.add(pModalidad);

			Chunk metogologia = new Chunk("Descripción de la propuesta metodológica de la unidad curricular: ",
					fontBold);
			Chunk metodologiaV = new Chunk(pr.getDescrMetodologia(), fontRegular);
			Paragraph pMeto = new Paragraph();
			pMeto.add(metogologia);
			pMeto.add(metodologiaV);
			pMeto.setSpacingAfter(15);
			document.add(pMeto);

			Chunk adec = new Chunk(
					"La Unidad curricular prevé adecuaciones metodológicas para estudiantes en situación de privación de libertad: ",
					fontBold);
			Chunk adecV = new Chunk(pr.isAdecuaciones() ? "SI" : "NO", fontRegular);
			Paragraph pAdec = new Paragraph();
			pAdec.add(adec);
			pAdec.add(adecV);
			pAdec.setSpacingAfter(15);
			document.add(pAdec);

			if (pr.isAdecuaciones()) {
				Chunk adecuaciones = new Chunk("Descripción de las adecuaciones metodológicas a realizar: \n",
						fontBold);
				Chunk adecuacionesV = new Chunk(pr.getDescripcionAdecuaciones(), fontRegular);
				Paragraph pAdecuaciones = new Paragraph();
				pAdecuaciones.add(adecuaciones);
				pAdecuaciones.add(adecuacionesV);
				pAdecuaciones.setSpacingAfter(15);
				document.add(pAdecuaciones);
			}

			Chunk regimen = new Chunk("Régimen de asistencia: ", fontBold);
			Chunk regimenV = new Chunk(pr.getRegimen().getLabel(), fontRegular);
			Paragraph pRegimen = new Paragraph();
			pRegimen.add(regimen);
			pRegimen.add(regimenV);
			pRegimen.setSpacingAfter(15);
			document.add(pRegimen);

			Chunk tareas = new Chunk("Realización obligatoria del 75% de las tareas propuestas por el equipo docente: ",
					fontBold);
			Chunk tareasV = new Chunk(pr.isTareas75obligatoria() ? "SI" : "NO", fontRegular);
			Paragraph pTareas = new Paragraph();
			pTareas.add(tareas);
			pTareas.add(tareasV);
			pTareas.setSpacingAfter(15);
			document.add(pTareas);

			Chunk exonera = new Chunk("Permite aprobación directa (exoneración): ", fontBold);
			Chunk exoneraV = new Chunk(pr.isAprobDirecta() ? "SI" : "NO", fontRegular);
			Paragraph pExonera = new Paragraph();
			pExonera.add(exonera);
			pExonera.add(exoneraV);
			pExonera.setSpacingAfter(15);
			document.add(pExonera);

			Chunk evaluacion = new Chunk("Descripción de la forma de evaluación y aprobación: \n", fontBold);
			Chunk evauacionV = new Chunk(pr.getDescrEvaluacion(), fontRegular);
			Paragraph pEvaluacion = new Paragraph();
			pEvaluacion.add(evaluacion);
			pEvaluacion.add(evauacionV);
			pEvaluacion.setSpacingAfter(15);
			document.add(pEvaluacion);

			Paragraph pAclaracionEv1 = new Paragraph();
			pAclaracionEv1.add(new Chunk(
					"El estudiante que no alcanzare la calificación mínima requerida (Aceptable) en una sola de las evaluaciones durante el curso —cualquiera que esta evaluación fuere—, ya sea para su aprobación directa o para ganar el derecho a aprobar mediante una evaluación final, tendrá derecho a la realización de una prueba de recuperación, que sustituirá a la referida instancia de evaluación.",
					fontRegularSmall));
			pAclaracionEv1.setSpacingAfter(15);
			document.add(pAclaracionEv1);

			Paragraph pAclaracionEv2 = new Paragraph();
			pAclaracionEv2.add(new Chunk(
					"Examen libre: para la aprobación de las unidades curriculares de carácter teórico, podrán rendir un examen libre aquellos estudiantes inscriptos al mismo. El examen versará sobre la totalidad del Programa del último curso impartido. Para aprobar la unidad curricular, el estudiante deberá alcanzar una calificación mínima de Aceptable en dicha evaluación.",
					fontRegularSmall));
			pAclaracionEv2.setSpacingAfter(15);
			document.add(pAclaracionEv2);

			Paragraph pBiblio = new Paragraph();
			Chunk biblio = new Chunk("Bibliografía básica", fontBold);
			pDocentes.add(biblio);
			PdfPTable tableBiblio = new PdfPTable(5);
			tableBiblio.setWidthPercentage(100);
			columnHeader = new PdfPCell(new Phrase("#", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableBiblio.addCell(columnHeader);

			columnHeader = new PdfPCell(new Phrase("Material bibliográfico", fontBold));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableBiblio.addCell(columnHeader);

			tableBiblio.setHeaderRows(1);
			for (BibliografiaDto b : pr.getBibliografia()) {
				table.addCell(b.getOrden().toString());
				table.addCell(b.getTitulo());
			}

			pBiblio.add(tableBiblio);
			pBiblio.setSpacingAfter(15);
			document.add(pBiblio);

			Chunk anio = new Chunk("Año " + pr.getYear(), fontBold);
			Paragraph pAnio = new Paragraph();
			pAnio.add(anio);
			pAnio.setAlignment(Element.ALIGN_RIGHT);
			document.add(pAnio);

			document.close();

			InputStream is = new ByteArrayInputStream(os.toByteArray());

			StreamedContent file = DefaultStreamedContent.builder().contentType("application/pdf").name("programa.pdf")
					.stream(() -> is).build();
			return file;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
