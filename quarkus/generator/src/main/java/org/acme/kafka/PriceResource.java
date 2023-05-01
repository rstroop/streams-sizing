package org.acme.kafka;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.reactivestreams.Publisher;

import org.eclipse.microprofile.reactive.messaging.Channel;

/**
 * A simple resource retrieving the "usd-prices" and "eur-prices" records, and
 * making them available from HTTP server sent events (SSE) endpoints.
 */
@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("usd-prices")
    Publisher<String> usdPrices;

    @Inject
    @Channel("eur-prices")
    Publisher<String> eurPrices;

    @GET
    @Path("/usd/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) // denotes that server side events (SSE) will be produced
    public Publisher<String> streamUSD() {
        return usdPrices;
    }

    @GET
    @Path("/eur/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) // denotes that server side events (SSE) will be produced
    public Publisher<String> streamEUR() {
        return eurPrices;
    }
}
