package com.ibm.DeveloperSkills.controller;

import java.sql.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.DeveloperSkills.domain.Developer;
import com.ibm.DeveloperSkills.service.DeveloperService;
import com.ibm.DeveloperSkills.service.DeveloperServiceImpl;

@Path("/developers")
public class DeveloperController {

	private DeveloperService devservice;
	
	public DeveloperController() {
		this.devservice = new DeveloperServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Developer> getDevelopers(
			@QueryParam("devID") String devID,
			@QueryParam("firstname") String firstname,
			@QueryParam("middlename") String middlename,
			@QueryParam("lastname") String lastname,
			@QueryParam("birthdate") Date birthdate,
			@QueryParam("position") String position) {

		try {
			List<Developer> dev;
			
			if (StringUtils.isAllBlank(firstname, middlename, lastname, position)) {
				dev = devservice.findAll();
			} else {
				dev = devservice.findByName(firstname, lastname);
			}	
			return dev;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	@GET
	@Path("{devID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Developer getDeveloper(@PathParam("devID") String devID) {

		try {
			Long devid = Long.parseLong(devID);
			Developer dev = devservice.find(devid);
			return dev;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDeveloper(Developer dev) {

		try {
			devservice.addDeveloper(dev);
			String result = "Developer Added : " +
			                 dev.getFirstname() + " " 
					       + dev.getMiddlename() + " " 
			               + dev.getLastname() + " "
			               + dev.getBirthdate() + " "
			               + dev.getPosition();
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@DELETE
	@Path("{devID}")
	public Response deleteUser(@PathParam("devID") String devID) {

		try {
			Long devid = Long.parseLong(devID);
			devservice.delDeveloper(devid);
			String result = "Developer Removed";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
