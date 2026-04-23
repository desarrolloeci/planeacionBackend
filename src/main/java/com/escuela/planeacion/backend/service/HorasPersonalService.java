package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.HorasPersonal;
import com.escuela.planeacion.backend.entity.HorasPersonalId;
import com.escuela.planeacion.backend.repository.HorasPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorasPersonalService {

    @Autowired
    private HorasPersonalRepository horasPersonalRepository;

    public List<HorasPersonal> getTodasHoras() {
        return horasPersonalRepository.findAll();
    }

    public HorasPersonal getHorasPorId(Integer idPersonal, Integer agno) {
        HorasPersonalId key = new HorasPersonalId(idPersonal, agno);
        return horasPersonalRepository.findById(key)
                .orElseThrow(() -> new RuntimeException("Horas no encontradas"));
    }

    public List<HorasPersonal> getHorasPorIdPersonal(Integer idPersonal) {
        return horasPersonalRepository.findById_IdPersonal(idPersonal);
    }

    public HorasPersonal crearHoras(HorasPersonal horasPersonal) {
        return horasPersonalRepository.save(horasPersonal);
    }

    public HorasPersonal actualizarHoras(Integer idPersonal, Integer agno, HorasPersonal horasActualizado) {
        HorasPersonalId key = new HorasPersonalId(idPersonal, agno);
        HorasPersonal horas = horasPersonalRepository.findById(key)
                .orElseThrow(() -> new RuntimeException("Horas no encontradas"));

         System.out.println("🧾 Cuerpo recibido horas: " + horasActualizado.getHoras());
         System.out.println("🧾 Cuerpo recibido cant: " + horasActualizado.getCantPer());

        horas.setHoras(horasActualizado.getHoras());
        horas.setCantPer(horasActualizado.getCantPer());

        return horasPersonalRepository.save(horas);
    }

    public void eliminarHoras(Integer idPersonal, Integer agno) {
        HorasPersonalId key = new HorasPersonalId(idPersonal, agno);
        HorasPersonal horas = horasPersonalRepository.findById(key)
                .orElseThrow(() -> new RuntimeException("Horas no encontradas"));
        horasPersonalRepository.delete(horas);
    }
}
