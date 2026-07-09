package com.mediturnos.service.interfaces.medical;

import com.mediturnos.dto.medical.request.MedicoRequest;
import com.mediturnos.dto.medical.response.MedicoResponse;

import java.util.List;

public interface MedicoService {

    MedicoResponse crear(MedicoRequest request);

    MedicoResponse actualizar(Long id, MedicoRequest request);

    MedicoResponse obtenerPorId(Long id);

    List<MedicoResponse> obtenerTodos();

    void eliminar(Long id);

}