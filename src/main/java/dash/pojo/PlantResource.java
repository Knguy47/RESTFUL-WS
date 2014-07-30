package dash.pojo;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dash.errorhandling.AppException;
import dash.service.PlantService;
/*
 * 
 * Service Class that handles REST request
 * 
 * @author jVera
 */

@Component
@Path("/plants")
public class PlantResource
{
	@Autowired
	private PlantService plantService;
	
//	@GET
//	@Path("{id}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response getPlantById(@PathParam("id") Long id,
//			@QueryParam("detailed") boolean detailed)
//					throws IOException,	AppException
//	{
//		Plant plantById = plantService.getPlantByID(id);
//		return Response
//				.status(200)
//				.entity(new GenericEntity<Plant>(plantById) {
//				},
//				detailed ? new Annotation[] { PlantDetailedView.Factory
//						.get() } : new Annotation[0])
//						.header("Access-Control-Allow-Headers", "X-extra-header")
//						.allow("OPTIONS").build();
//	}
	
	@GET
	@Path("{commonName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPlantByCommonName(
			@PathParam("commonName") String commonName,
			@QueryParam("detailed") boolean detailed) throws AppException
	{
		Plant plantByCommonName = plantService.getPlantByCommonName(commonName);
		return Response
				.status(200)
				.entity(new GenericEntity<Plant>(plantByCommonName) {
				},
				detailed ? new Annotation[] { PlantDetailedView.Factory
						.get() } : new Annotation[0])
						.header("Access-Control-Allow-Headers", "X-extra-header")
						.allow("OPTIONS").build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Plant> getPlants(
			@QueryParam("bloomSeason") Integer bloomSeason,
			@QueryParam("category") Integer category,
			@QueryParam("nativeField") Integer nativeField,
			@QueryParam("waterAmount") Integer waterAmount,
			@QueryParam("sunlightAmount") Integer sunlightAmount)
					throws IOException,	AppException
	{
		List<Plant> groups = plantService.getPlantsByFilter(bloomSeason,
				category, nativeField, waterAmount, sunlightAmount);
		return groups;
	}
}