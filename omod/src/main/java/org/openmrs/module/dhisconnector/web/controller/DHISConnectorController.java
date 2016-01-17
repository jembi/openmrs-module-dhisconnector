/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dhisconnector.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for the DHIS Connector Module admin pages
 */
@Controller
public class DHISConnectorController {

	protected final Log log = LogFactory.getLog(getClass());

	public static final String GLOBAL_PROPERTY_URL = "dhisconnector.url";

	public static final String GLOBAL_PROPERTY_USER = "dhisconnector.user";

	public static final String GLOBAL_PROPERTY_PASS = "dhisconnector.pass";

	@RequestMapping(value = "/module/dhisconnector/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}

	@RequestMapping(value = "/module/dhisconnector/createMapping", method = RequestMethod.GET)
	public void createMapping(ModelMap model) {
		model.addAttribute("showLogin", (Context.getAuthenticatedUser() == null) ? true : false);
	}

	@RequestMapping(value = "/module/dhisconnector/configureServer", method = RequestMethod.GET)
	public void configureServer(ModelMap model) {
			String url = Context.getAdministrationService().getGlobalProperty(GLOBAL_PROPERTY_URL);
			String user = Context.getAdministrationService().getGlobalProperty(GLOBAL_PROPERTY_USER);
			String pass = Context.getAdministrationService().getGlobalProperty(GLOBAL_PROPERTY_PASS);
	
			model.addAttribute("url", url);
			model.addAttribute("user", user);
			model.addAttribute("pass", pass);
			model.addAttribute("showLogin", (Context.getAuthenticatedUser() == null) ? true : false);
	}

	@RequestMapping(value = "/module/dhisconnector/configureServer", method = RequestMethod.POST)
	public void saveConfig(ModelMap model, @RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "user", required = true) String user,
			@RequestParam(value = "pass", required = true) String pass, WebRequest req) throws ParseException {

		AdministrationService as = Context.getAdministrationService();
		GlobalProperty urlProperty = as.getGlobalPropertyObject(GLOBAL_PROPERTY_URL);
		GlobalProperty userProperty = as.getGlobalPropertyObject(GLOBAL_PROPERTY_USER);
		GlobalProperty passProperty = as.getGlobalPropertyObject(GLOBAL_PROPERTY_PASS);

		if (Context.getService(DHISConnectorService.class).testDHISServerDetails(url, user, pass)) {
			// Save the properties
			urlProperty.setPropertyValue(url);
			userProperty.setPropertyValue(user);
			passProperty.setPropertyValue(pass);

			as.saveGlobalProperty(urlProperty);
			as.saveGlobalProperty(userProperty);
			as.saveGlobalProperty(passProperty);

			req.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
					Context.getMessageSourceService().getMessage("dhisconnector.saveSuccess"),
					WebRequest.SCOPE_SESSION);

			model.addAttribute("url", url);
			model.addAttribute("user", user);
			model.addAttribute("pass", pass);
		} else {
			req.setAttribute(WebConstants.OPENMRS_ERROR_ATTR,
					Context.getMessageSourceService().getMessage("dhisconnector.saveFailure"),
					WebRequest.SCOPE_SESSION);

			model.addAttribute("url", urlProperty.getPropertyValue());
			model.addAttribute("user", userProperty.getPropertyValue());
			model.addAttribute("pass", passProperty.getPropertyValue());
		}
	}

	@RequestMapping(value = "/module/dhisconnector/runReports", method = RequestMethod.GET)
	public void showRunReports(ModelMap model) {
		model.addAttribute("showLogin", (Context.getAuthenticatedUser() == null) ? true : false);
	}

	@RequestMapping(value = "/module/dhisconnector/runReports", method = RequestMethod.POST)
	public void runReport(ModelMap model, @RequestParam(value = "report", required = true) String reportMappingFilename,
			@RequestParam(value = "location", required = true) Date date,
			@RequestParam(value = "date", required = true) Integer locationId, WebRequest req) throws ParseException {
		DHISConnectorService dcs = Context.getService(DHISConnectorService.class);

		List<PeriodIndicatorReportDefinition> reportsWithMappings = dcs.getReportWithMappings(dcs.getMappings());

		model.addAttribute("reports", reportsWithMappings);
	}

	@RequestMapping(value = "/module/dhisconnector/uploadMapping", method = RequestMethod.GET)
	public void showuploadMapping(ModelMap model) {
		passOnUploadingFeedback(model, "", "");
		model.addAttribute("showLogin", (Context.getAuthenticatedUser() == null) ? true : false);
	}

	@RequestMapping(value = "/module/dhisconnector/uploadMapping", method = RequestMethod.POST)
	public void uploadMapping(ModelMap model,
			@RequestParam(value = "mapping", required = false) MultipartFile mapping) {
		String successMessage = "";
		String failedMessage = "";

		if (!mapping.isEmpty()) {
			String msg = Context.getService(DHISConnectorService.class).uploadMappings(mapping);

			if (msg.startsWith("Successfully")) {
				successMessage = msg;
				failedMessage = "";
			} else {
				failedMessage = msg;
				successMessage = "";
			}
		} else {
			failedMessage = Context.getMessageSourceService().getMessage("dhisconnector.uploadMapping.mustSelectFile");
		}
		passOnUploadingFeedback(model, successMessage, failedMessage);
	}

	private void passOnUploadingFeedback(ModelMap model, String successMessage, String failedMessage) {
		model.put("failureWhileUploading", failedMessage);
		model.put("successWhileUploading", successMessage);
	}

	@RequestMapping(value = "/module/dhisconnector/exportMappings", method = RequestMethod.GET)
	public void exportMapping(ModelMap model) {
		passOnExportedFeedback(model, "", "");
		model.addAttribute("showLogin", (Context.getAuthenticatedUser() == null) ? true : false);
	}

	private void passOnExportedFeedback(ModelMap model, String failureWhileExporting, String successWhileExporting) {
		model.put("failureWhileExporting", failureWhileExporting);
		model.put("successWhileExporting", successWhileExporting);
	}

	@RequestMapping(value = "/module/dhisconnector/exportMappings", method = RequestMethod.POST)
	public void exportMapping(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String[] selectedMappings = request.getParameter("selectedMappings") != null ? request.getParameter("selectedMappings").split("<:::>") : null;
		String msg = "";
		
		if (selectedMappings != null) {
			try {
				String[] exported = Context.getService(DHISConnectorService.class).exportSelectedMappings(selectedMappings);
				msg = exported[0];
				int BUFFER_SIZE = 4096;
				String fullPath = exported[1];//contains path to backedupMappings
				
				if(StringUtils.isNotBlank(msg) && msg.startsWith("Successfully")) {
					if (fullPath != null) {
						File downloadFile = new File(fullPath);
						FileInputStream inputStream;
						inputStream = new FileInputStream(downloadFile);
						String mimeType = "application/octet-stream";
						System.out.println("MIME type: " + mimeType);
						response.setContentType(mimeType);
						response.setContentLength((int) downloadFile.length());
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
						response.setHeader(headerKey, headerValue);
						OutputStream outStream = response.getOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int bytesRead = -1;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outStream.write(buffer, 0, bytesRead);
						}
						inputStream.close();
						outStream.close();
						(new File(fullPath)).delete();
					}
					passOnExportedFeedback(model, "", msg);
				} else {
					passOnExportedFeedback(model, msg, "");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			msg = Context.getMessageSourceService().getMessage("dhisconnector.exportMapping.noMappingsFound");
			passOnExportedFeedback(model, "", msg);
		}
	}
}
