package tech.core.common.models;


import java.io.Serializable;

public record ValueResponse<T>(T value) implements Serializable {

}
