package ru.itmo.ebay.web.resource;

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.itmo.ebay.ejb.dto.ProductDto;
import ru.itmo.ebay.ejb.exception.EbayServiceException;
import ru.itmo.ebay.ejb.remote.EbayFilterServiceRemote;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/ebay/filter")
@Produces(MediaType.APPLICATION_JSON)
public class EbayFilterResource {
    private static final Logger log = Logger.getLogger(EbayFilterResource.class.getName());

    @EJB
    private EbayFilterServiceRemote ebayFilterService;

    @GET
    @Path("/manufacturer/{manufacturer-id}")
    public Response getProductsByManufacturerId(
            @PathParam("manufacturer-id") int manufacturerId
    ) {
        try {
            log.log(Level.INFO, "REST: Getting products by manufacturer id: {0}", manufacturerId);
            List<ProductDto> products = ebayFilterService.getProductsByManufacturerId(manufacturerId);
            return Response.ok(products).build();
        } catch (EbayServiceException e) {
            log.log(Level.WARNING, "Business error: " + e.getMessage(), e);
            return Response.status(e.getStatusCode()).entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Internal server error")).build();
        }
    }

    @GET
    @Path("/price/{price-from}/{price-to}")
    public Response getProductsByPriceRange(
            @PathParam("price-from") int priceFrom,
            @PathParam("price-to") int priceTo
    ) {
        try {
            log.log(Level.INFO, "REST: Getting products in price range [{0}, {1}]", 
                    new Object[]{priceFrom, priceTo});
            List<ProductDto> products = ebayFilterService.getProductsByPriceRange(priceFrom, priceTo);
            return Response.ok(products).build();
        } catch (EbayServiceException e) {
            log.log(Level.WARNING, "Business error: " + e.getMessage(), e);
            return Response.status(e.getStatusCode()).entity(new ErrorResponse(e.getMessage())).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unexpected error", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Internal server error")).build();
        }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse() {
        }

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

