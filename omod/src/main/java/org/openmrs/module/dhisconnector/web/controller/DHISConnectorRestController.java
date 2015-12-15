/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dhisconnector.web.controller;

import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + DHISConnectorRestController.REPORTINGREST_TO_DHIS_NAMESPACE)
public class DHISConnectorRestController extends MainResourceController {

  public static final String REPORTINGREST_TO_DHIS_NAMESPACE = "/reportingresttodhis";

  /**
   * @see org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController#getNamespace()
   */
  @Override
  public String getNamespace() {
    return RestConstants.VERSION_1 + REPORTINGREST_TO_DHIS_NAMESPACE;
  }
}