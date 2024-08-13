package com.tenfar.ddd.common.handler;

import com.tenfar.ddd.common.response.Meta;

/**
 * MetaHandler interface for handling metadata for various response types.
 */
public interface MetaHandler {
    /**
     * Handles the metadata for the given body.
     *
     * @param meta the Meta object to populate
     * @param body the object to process
     */
    void handle(Meta meta, Object body);

    /**
     * Returns the class type that this handler processes.
     *
     * @return the class type handled by this handler
     */
    Class<?> getHandledType();
}