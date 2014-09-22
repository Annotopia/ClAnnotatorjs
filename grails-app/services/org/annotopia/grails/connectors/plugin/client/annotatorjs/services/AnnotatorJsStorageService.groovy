/*
 * Copyright 2014 Massachusetts General Hospital
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.annotopia.grails.connectors.plugin.client.annotatorjs.services

import com.hp.hpl.jena.query.Dataset
import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.sparql.core.DatasetImpl

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class AnnotatorJsStorageService {

	def openAnnotationStorageService;
	
	def grailsApplication = new org.codehaus.groovy.grails.commons.DefaultGrailsApplication()
	
	public Dataset save(String apiKey, Long startTime, Model model) {
		// Saves the annotation through services
		Dataset savedAnnotation;
		try {
			Dataset inMemoryDataset = new DatasetImpl(model);
			//inMemoryDataset.addNamedModel(apiKey, model);
			savedAnnotation = openAnnotationStorageService.saveAnnotationDataset(apiKey, startTime, false, inMemoryDataset);
			log.error 'before: ' + savedAnnotation;
			return savedAnnotation;
		} catch(Exception exception) {
			log.error(exception);
			/*
			log.error("[" + apiKey + "] " + exception.getMessage());
			render(status: exception.status, text: exception.text, contentType: exception.contentType, encoding: exception.encoding);
			*/
			return;
		}
	}
}
