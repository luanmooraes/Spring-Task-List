package com.luan.dto;

import java.util.List;

public record TaskPageDTO(List<TaskDTO> tasks,long totalElements, int totalPages) {
}
