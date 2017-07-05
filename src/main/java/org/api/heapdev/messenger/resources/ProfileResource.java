package org.api.heapdev.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.api.heapdev.messenger.model.Profile;
import org.api.heapdev.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profileService = new ProfileService();

	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}

	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}

//	@PUT
//	@Path("/{profileName}")
//	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
//		profile.setProfileName(profileName);
//		return profileService.updateProfile(profile);
//
//	}
	
	@PUT
	@Path("/{profileName}")
	public Response updateProfile(@PathParam("profileName") String profileName, @Context UriInfo uriInfo, Profile profile) {
		profile.setProfileName(profileName);
		URI uri = uriInfo.getAbsolutePathBuilder().path(profile.getProfileName()).build();
		return Response.accepted(uri).entity(profileService.updateProfile(profile)).build();
	}

	@POST
	public Response addProfile(Profile profile, @Context UriInfo uriInfo) {
		// return profileService.addProfile(profile);
		URI uri = uriInfo.getAbsolutePathBuilder().path(profile.getProfileName()).build();
		Profile newProfile = profileService.addProfile(profile);
		return Response.created(uri).entity(newProfile).build();
	}

	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.deleteProfile(profileName);
	}

}
