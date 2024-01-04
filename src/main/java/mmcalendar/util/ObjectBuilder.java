package mmcalendar.util;

/**
 * Base interface for all object builders.
 *
 * @param <T> the type that will be built.
 */
public interface ObjectBuilder<T> {
    T build();
}
