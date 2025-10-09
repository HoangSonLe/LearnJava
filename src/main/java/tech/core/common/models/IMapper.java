package tech.core.common.models;

public interface IMapper<E, D> {
    D toDto(E entity);

    E toEntity(D dto);
}
