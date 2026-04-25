package com.healthsys.triagemservice.mapper;

import com.healthsys.triagemservice.dto.StatusRequestDTO;
import com.healthsys.triagemservice.dto.StatusResponseDTO;
import com.healthsys.triagemservice.model.Status;

public class StatusMapper {
    public static StatusResponseDTO toDTO(Status status) {
        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
        statusResponseDTO.setId(status.getId());
        statusResponseDTO.setDescricao(status.getDescricao());
        return statusResponseDTO;
    }

    public static Status toModel(StatusRequestDTO statusRequestDTO) {
        Status status = new Status();
        status.setDescricao(statusRequestDTO.getDescricao());
        return status;
    }
}
