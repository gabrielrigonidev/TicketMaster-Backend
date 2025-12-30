package tech.insider.controller.dto;

public record PaginationDTO(int page,
                            int pageSize,
                            int totalPages,
                            long totalItems) {
}
